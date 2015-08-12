# automation-test-ws

Use with Docker compose to test `dangermike64/automated-webservice`

First, ensure that the `dangermike64/automated-webservice` container is running:

> `docker run -i -t --rm --name=GreetingService dangermike64/automation-webserver`

Next, run this container to exexute the tests against the container:

> `docker run -it --link=GreetingService:GreetingEndpoint dangermike64/automation-test-ws gradle test`

No news is good news. If the container doesn't report an error then all tests pass.

You can view the test reports by copying the files out of the container (and use them as a build artefact):

> `docker cp <containerId>:/tests/build/reports/tests /host/path/target`

You could always mount a volume and copy the files out of the container as an alternative, maybe ?
