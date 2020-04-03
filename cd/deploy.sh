#!/usr/bin/env bash

echo "_OSSRH_JIRA_USERNAME="$_OSSRH_JIRA_USERNAME
echo "_OSSRH_JIRA_PASSWORD="$_OSSRH_JIRA_PASSWORD_SECRET
echo "_DOCKER_USERNAME="$_DOCKER_USERNAME
echo "_DOCKER_PASSWORD_SECRET="$_DOCKER_PASSWORD_SECRET

echo "Deploy for all branches"

gpg --fast-import /root/.gpg/gpg.key
cp /jar/cli-*.jar cli/target/
mvn deploy -P sign,build-extras,docker -DskipTests=true --settings cd/mvnsettings.xml