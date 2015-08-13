import groovyx.net.http.RESTClient
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT

import spock.lang.Specification

class TestGreetingService extends Specification {
    def "Calling web service returns greeting"() {
        when: "I call the greeting web service with the route 'mike'"
            def endpoint = new RESTClient("http://GreetingEndpoint:3000")
            def response = endpoint.get(path: '/hello/mike')

        then: "I would expect the service to return a greeting as a JSON object"
            response.data.Greeting == "Hello"
            response.data.Name == "mike"
    }

    def "Calling web service returns only greeting when no name passed"() {
        when: "When I call the greeting web service with no route "
            def endpoint = new RESTClient("http://GreetingEndpoint:3000")
            def response = endpoint.get(path: '/hello')

        then: "I would expect the service to return a greeting as a JSON object"
            response.data.Greeting == "Hello"
            response.data.containsKey("Name") == false
    }

    def "Calling web service returns only 404 for invalid route"() {
        when: "When I call the greeting web service with the route 'mike/hi' "
            def endpoint = new HTTPBuilder()
            def responseStatus = endpoint.request("http://GreetingEndpoint:3000", GET, TEXT) { req ->
                uri.path = '/hello/mike/hi'

                response.failure = { resp ->
                    resp.status
                }

                response.success = { resp ->
                    resp.status
                }
            }

        then: "I would expect the service to return a 404"
            responseStatus == 404
    }
}
