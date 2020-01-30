# MFI challenge

## What's need to be done

Metwork is a framework developed at Météo-France for building production ready
IoT and meteorological web application. (https://github.com/metwork-framework).

Your task is to help the MFI software team evaluate Metwork by providing ready
to use docker images. What we expect from you is:

* a Dockerfile that will create a docker image from CentOS 7.0 including the
latest metwork stable mfext, mfdata, mfserv components from the official
repository.
* docker-compose.yml file(s) which will spawn a Jenkins environment and a
docker registry. Up to you to split or have only one docker-compose.yml.
* a Jenkinsfile describing a CI pipeline which rebuilds the image when the
master branch of your repo is updated. Basically if I make a change to the
Dockerfile I want the image to be rebuilt automagically and pushed to the
docker registry. Of course this should run on the Jenkins env spawned using the
compose file you provided.
* a README.md explaining how to use your "creation"

If you feel like it, you can try to do the following extra optional tasks:

* create a mfdata plugin which converts any .png file into .jpg file. I will
test your plugin directly in a container.

The result of the test should be provided to MFI using a public Github
repository.

## Requirements

* Linux system
* docker-compose version 1.25.0 or upper
* docker version 19.03.5-ce or upper
* Ports exposed by Jenkins (8080, 50000) should be available on the host

## Instructions

1. Modify (or not) the variables defined in `jenkins/.env`:
  * `JENKINS_USERNAME`: username to use to connect to Jenkins
  * `JENKINS_PASSWORD`: password to use to connect to Jenkins
  * `JENKINS_MASTER_EXECUTORS`: number of master executors to create
  * `JENKINS_ROOT_URL`: Jenkins root URL
  * `JENKINS_JOB_NAME`: name of the job to create
  * `JENKINS_JOB_REPO_URL`: URL of the repository that the job will be polling
  from

2. Run `docker-compose up --build` to deploy Jenkins and the registry
3. Jenkins is available at [http://localhost:8080/](http://localhost:8080/)
4. Wait for the automatically created job to complete
5. The `jb/metwork:latest` image can now be downloaded from the freshly
deployed registry. This registry (insecure) exposes port 5000/tcp
6. A pre-installed plugin called `convert_png_to_jpeg` can be easily tested
within a container based on the `jb/metwork:latest` image

## Comments

- Security issues have not been taken into account.
- Jenkins pipeline use `pollSCM` because Jenkins is considered not accessible
from Github.
- Jenkins uses the Docker daemon and the network stack of its host. This is the
simplest/fastest way to be able to push to the registry from Jenkins. Using a
dind image would have been more sexy!
- Less Jenkins plugins could have be installed. This can be configured by
deleting corresponding lines in the `jenkins/plugins.txt` file.
- Fixed CentOS/Alpine packages versions should have been specified in the
Dockerfiles to have more control over what is being installed (i.e to be able
build the same thing at any time). However this is not possible by staying
behind the official repositories as they are not keeping old packages versions.
This would have been possible behind a mirror configured to keep packages in
its cache.
