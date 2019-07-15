git clone $GITHUB_URL && cd $GIT_REPO_NAME && Xvfb :99 & && export DISPLAY=:99 && mvn clean verify -Dwebdriver.base.url=$WEB_DRIVER_URL
