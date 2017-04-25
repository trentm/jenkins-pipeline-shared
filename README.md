# jenkins-pipeline-shared

Shared library for Joyent Jenkins Pipeline usage.


# API

## joyentBuildNotify

A Jenkins Pipeline step to notify on completion of a Joyent build.

The Jenkins configuration must include a `JOYENT_BUILD_NOTIFY_URL` environment
variable. `joyentBuildNotify` will POST build details to that endpoint.


# Acknowledgements

This is based heavily on <https://github.com/bitwiseman/jenkins-pipeline-shared>
and adapted for use in Joyent's internal engineering Jenkins.

See the two introductory blog posts:

- <https://jenkins.io/blog/2016/07/18/pipline-notifications/>
- <https://jenkins.io/blog/2017/02/15/declarative-notifications/>
