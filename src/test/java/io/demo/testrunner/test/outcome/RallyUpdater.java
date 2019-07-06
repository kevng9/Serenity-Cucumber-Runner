package io.demo.testrunner.test.outcome;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;

import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.response.CreateResponse;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.model.DataTable;
import net.thucydides.core.model.Story;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.model.TestTag;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepFailure;
import net.thucydides.core.steps.StepListener;

public class RallyUpdater implements StepListener {

	
	@Step
	@Override
	public void testFinished(TestOutcome result) {

		Set<TestTag> tagsList = result.getTags();
		java.util.Iterator<TestTag> tagsItrator = tagsList.iterator();
		ClassPathResource resource = new ClassPathResource("application.properties");
		Properties p = new Properties();
		InputStream inputStream = null;
		String rallyTestId = null;
		JsonObject newTestCaseResult = new JsonObject();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {

			inputStream = resource.getInputStream();
			p.load(inputStream);
			
			RallyRestApi restApi = new RallyRestApi(new URI(p.getProperty("rally.host")), p.getProperty("rally.apikey"));
			restApi.setWsapiVersion(p.getProperty("rally.waspi.version"));
			
			
			//Loop through Tags to get the Tag having the Rally Test Case Id
			while (tagsItrator.hasNext()) {
				TestTag tagAndName = tagsItrator.next();
				String tagType = tagAndName.getType();
				if (tagType.contains(p.getProperty("rally.tag.prefix"))) {

					String tagName = tagAndName.getName();
					String rallyTestIdSplitter[] = tagName.split("/");
					rallyTestId = rallyTestIdSplitter[rallyTestIdSplitter.length - 1];
				}

			}
			if (result.isSuccess()) {
				newTestCaseResult.addProperty("Verdict", "Pass");
			} else {
				newTestCaseResult.addProperty("Verdict", "Fail");
			}

			newTestCaseResult.addProperty("Date", simpleDateFormat.format(new Date())+"T00:00:00.000Z");
			newTestCaseResult.addProperty("Notes", "Updated from Selenium Cucumber Runner");
			newTestCaseResult.addProperty("Build", p.getProperty("rally.build.no"));
			newTestCaseResult.addProperty("TestCase", rallyTestId);
			newTestCaseResult.addProperty("Workspace", p.getProperty("rally.workspace"));

			CreateRequest createRequest = new CreateRequest("testcaseresult", newTestCaseResult);
			CreateResponse createResponse = restApi.create(createRequest);

		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void testSuiteStarted(Class<?> storyClass) {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSuiteStarted(Story story) {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSuiteFinished() {
		// TODO Auto-generated method stub

	}

	@Step
	@Override
	public void testStarted(String description) {

	}

	@Override
	public void testStarted(String description, String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void testRetried() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stepStarted(ExecutedStepDescription description) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedStepStarted(ExecutedStepDescription description) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stepFailed(StepFailure failure) {
		// TODO Auto-generated method stub

	}

	@Override
	public void lastStepFailed(StepFailure failure) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stepIgnored() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stepPending() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stepPending(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stepFinished() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testFailed(TestOutcome testOutcome, Throwable cause) {
		// TODO Auto-generated method stub

	}

	@Override
	public void testIgnored() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testSkipped() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testPending() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testIsManual() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyScreenChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void useExamplesFrom(DataTable table) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNewExamplesFrom(DataTable table) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exampleStarted(Map<String, String> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exampleFinished() {
		// TODO Auto-generated method stub

	}

	@Override
	public void assumptionViolated(String message) {
		// TODO Auto-generated method stub

	}

	@Step
	@Override
	public void testRunFinished() {
		// TODO Auto-generated method stub

	}

}
