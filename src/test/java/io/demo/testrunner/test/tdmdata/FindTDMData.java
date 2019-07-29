package io.demo.testrunner.test.tdmdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FindTDMData {
		
	ClassPathResource resource = new ClassPathResource("application.properties");
	Properties p = new Properties();
	InputStream inputStream = null;
				
	public String fetchTestData(String use, String get, String from, String where) throws Exception{

		String foundData = "";
		String findControllerFilters = "";
		ArrayList<String> filterConditions = new ArrayList<String>();
		ArrayList<String> elementNames = new ArrayList<String>();
		ArrayList<String> elementConditions = new ArrayList<String>();
		ArrayList<String> elementValues = new ArrayList<String>();
		ArrayList<String> fieldIds = new ArrayList<String>();
		String dataModelId = "";
		String environmentId = "";

		try {
			
			inputStream = resource.getInputStream();
			p.load(inputStream);
			
			//Get Environment ID for -> "use"
			String enviControllerOutput = this.invokeTDMAPI("/TDMDataReservationService/api/ca/v1/environments/", "?", "GET", false, "", false, true);
			JsonObject envFieldsObject = new Gson().fromJson(enviControllerOutput, JsonObject.class);
			JsonArray envFieldsArray = new Gson().fromJson(envFieldsObject.get("elements"), JsonArray.class);
					
				
				for (JsonElement element : envFieldsArray) {
					
					JsonObject elementObject = element.getAsJsonObject();
				

					if (elementObject.get("name").toString().replace("\"", "").equals(use)) {
						environmentId=elementObject.get("id").toString();
					}

				}
				
			//System.out.println(environmentId);
			
			//Get Model ID for -> "from"
			String model_controllerOutput = this.invokeTDMAPI("/TDMDataReservationService/api/ca/v1/testDataModels", "?", "GET", false, "", false, true);
			JsonObject model_FieldsObject = new Gson().fromJson(model_controllerOutput, JsonObject.class);
			JsonArray model_FieldsArray = new Gson().fromJson(model_FieldsObject.get("testDataModelsList"), JsonArray.class);
			
			
				
				for (JsonElement model : model_FieldsArray) {
					
					JsonObject modelObject = model.getAsJsonObject();
				

					if (modelObject.get("name").toString().replace("\"", "").equals(from)) {
						dataModelId=modelObject.get("id").toString();
					}

				}
			
				
			//System.out.println(dataModelId);
			
			// Split -> "where" clause
			filterConditions = new ArrayList<String>(Arrays.asList(where.split("AND")));

			for (String filterCondition : filterConditions) {

				filterCondition = filterCondition.trim();
				elementNames.add(filterCondition.split(" ")[0].trim());
				elementConditions.add(filterCondition.split(" ")[1].trim());
				elementValues.add(filterCondition.split(" ")[2].trim());

			}
			
			
			//Get FIle IDs for the Split -> "where" clause
			String modelcontrollerOutput=this.invokeTDMAPI("/TDMDataReservationService/api/ca/v1/testDataModels/"+dataModelId, "/filters?", "GET", false, "", false, true);
			
			JsonArray modelFieldsArray = new Gson().fromJson(modelcontrollerOutput, JsonArray.class);

			for (String e : elementNames) {
				for (JsonElement modelField : modelFieldsArray) {
					JsonObject modelFieldObject = modelField.getAsJsonObject();

				

					if (modelFieldObject.get("displayName").toString().replace("\"", "").equals(e)) {
						fieldIds.add(modelFieldObject.get("fieldId").toString());
					}

				}

			}
			
			//System.out.println(fieldIds);
			
			//Validation if the "where" is correct. Throw error if not.
			if (elementNames.size() != fieldIds.size()) {
				throw new Exception(
						"One or more of the Field Names supplied as 'where' clause is incorrect. Refer to TDM.");
			}
            
			//Build Filters for Find Controller
			for (int i = 0; i < fieldIds.size(); i++) {

				findControllerFilters = findControllerFilters + "{\"fieldId\": " + fieldIds.get(i) + ",\"operator\": \""
						+ elementConditions.get(i) + "\",\"values\": [\"" + elementValues.get(i) + "\"]}";
				if (i < fieldIds.size() - 1) {
					findControllerFilters = findControllerFilters + ",";
				}
			}
			
			String findControllerPayload = "{    \"environmentId\": " + environmentId + ",     \"filters\": ["
					+ findControllerFilters
					+ "],    \"includeReservedRecords\": false,     \"showReservedRecords\": false,     \"startAfterValues\": {}   }";
			
			// Invoke TDM FIND CONTROLLER
			String findcontrollerOutput =this.invokeTDMAPI("/TDMDataReservationService/api/ca/v1/testDataModels/"+dataModelId, "/actions/find?", "POST", true, findControllerPayload, false, true);
			JsonObject foundRecordsObject = new Gson().fromJson(findcontrollerOutput.toString(), JsonObject.class);
			JsonArray foundRecordsArray = new Gson().fromJson(foundRecordsObject.get("records").toString(),
					JsonArray.class);

			if (foundRecordsArray.size() == 0) {
				throw new Exception("No Data found for the specified filter criteria.");

			}

			foundRecordsObject = new Gson().fromJson(foundRecordsArray.get(0).toString(), JsonObject.class);
			foundRecordsObject = new Gson().fromJson(foundRecordsObject.get("columnValues").toString(),
					JsonObject.class);
			foundData = foundRecordsObject.get(get).toString();
			
			
			
			

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} 

		return foundData.replace("\"", "");
	}
	
	//Generic method to invoke TDM APIs
	public String invokeTDMAPI(String ControllerBaseUrl, String ControllerBaseUrl_1, String requestType, boolean isThereAPayload, String payload, boolean isBasicAuth, boolean isProjectVersionRequired ) {
		
		String apiResponse="";
		URL Controllerurl ;
		String bearerToken;
		
		
		try {
			inputStream = resource.getInputStream();
			p.load(inputStream);
			
			if(isProjectVersionRequired) {
				Controllerurl = new URL(p.getProperty("tdm.url")+ ControllerBaseUrl + ControllerBaseUrl_1 + "projectId=" + p.getProperty("tdm.project.id") + "&" + "versionId=" + p.getProperty("tdm.version.id")
						);
			}
			else
			{
				Controllerurl = new URL(p.getProperty("tdm.url")+ ControllerBaseUrl + ControllerBaseUrl_1
						);
			}
			
			
			HttpURLConnection Controllerconn = (HttpURLConnection) Controllerurl.openConnection();
			Controllerconn.setRequestMethod(requestType);
			Controllerconn.setRequestProperty("Accept", "application/json");
			
			
			
			if(isBasicAuth) {
				System.out.println(Controllerurl);
				Controllerconn.setRequestProperty("Authorization", "Basic " + p.getProperty("tdm.basic.token"));
			}
			else {
				
				
				bearerToken = this.invokeTDMAPI("/TestDataManager/user/login","","POST",false,"",true,false );
				JsonObject authObject = new Gson().fromJson(bearerToken, JsonObject.class);
				bearerToken=authObject.get("token").toString().replace("\"", "");
				
				Controllerconn.setRequestProperty("Authorization", "Bearer " + bearerToken);
			}
			
			if(isThereAPayload) {
				
				Controllerconn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				Controllerconn.setDoOutput(true);
				OutputStream os = Controllerconn.getOutputStream();
				os.write(payload.getBytes());
				os.flush();
				os.close();
				
			}

			if (Controllerconn.getResponseCode() != 200) {
				throw new RuntimeException(
						ControllerBaseUrl + ControllerBaseUrl_1 + " Failed : HTTP error code : " + Controllerconn.getResponseCode());
			}
			
			BufferedReader ControllerOutputReader = new BufferedReader(
					new InputStreamReader((Controllerconn.getInputStream())));

			String authControllerOutput;

			while ((authControllerOutput = ControllerOutputReader.readLine()) != null) {

				apiResponse = apiResponse+authControllerOutput;

			}
			
			
			Controllerconn.disconnect();
		}
		
		catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} 
		
		return apiResponse;
		
		
	}

}
