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
                "jobName": "${env.JOB_NAME}",
                "nodeName": "${env.NODE_NAME}",
                "buildId": "${env.BUILD_ID}",
                "buildUrl": "${env.BUILD_URL}",
                "startTime": ${currentBuild.timeInMillis},
                "duration": ${currentBuild.duration},
                "result": "${buildResult}",
                "params": {
                    "BRANCH": "${params.BRANCH}"
                }
            }"""
        )
    } else {
        echo 'joyentBuildNotify: warning: env.BUILD_NOTIFICATION_URL is not set'
    }
}
