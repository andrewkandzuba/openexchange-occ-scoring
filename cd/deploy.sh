#!/usr/bin/env bash

echo "OSSRH_JIRA_USERNAME="$OSSRH_JIRA_USERNAME
echo "OSSRH_JIRA_PASSWORD="$OSSRH_JIRA_PASSWORD
echo "Deploy for all branches"

gpg --fast-import /root/.gpg/gpg.key
cp /jar/cli-*.jar cli/target/
mvn deploy -P sign,build-extras,docker -DskipTests=true --settings cd/mvnsettings.xml