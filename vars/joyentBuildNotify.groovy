#!/usr/bin/env groovy

/**
 * POST build details to `JOYENT_BUILD_NOTIFY_URL`.
 */
def call() {
    // Build result of null means success.
    def buildResult = currentBuild.result ?: 'SUCCESS'

    // XXX update _URL envvar name
    // XXX choose final field names
    // XXX enumerate params possible?
    if (env.BUILD_NOTIFICATION_URL) {
        httpRequest(
            url: "${env.BUILD_NOTIFICATION_URL}",
            consoleLogResponseBody: true,
            contentType: 'APPLICATION_JSON',
            httpMode: 'POST',
            responseHandle: 'NONE',
            timeout: 10,
            requestBody: """{
                "params": {
                    "BRANCH": "${params.BRANCH}"
                },
                "currentBuild": {
                    "startTime": ${currentBuild.timeInMillis},
                    "duration": ${currentBuild.duration},
                    "result": "${buildResult}",
                    "changeSets": ${currentBuild.changeSets}
                },
                "JOB_NAME": "${env.JOB_NAME}",
                "NODE_NAME": "${env.NODE_NAME}",
                "BUILD_ID": "${env.BUILD_ID}",
                "BUILD_URL": "${env.BUILD_URL}"
            }"""
        )
    } else {
        echo 'joyentBuildNotify: warning: env.BUILD_NOTIFICATION_URL is not set'
    }
}
