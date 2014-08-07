# API Hamcrest Matchers

This library provides Hamcrest Matchers useful when testing various APIs.


## JSON

    import static com.ft.dropwizard.matcher.Json.validJson;

    assertThat(json, is(validJson()));


## JSON-LD

    import static com.ft.dropwizard.matcher.JsonLd.validJsonLd;

    assertThat(jsonLd, is(validJsonLd()));


## Dropwizard health check

    import static com.ft.dropwizard.matcher.HealthCheckResult.healthy;
    import static com.ft.dropwizard.matcher.HealthCheckResult.unhealthy;

    assertThat(healthCheck.check(), is(healthy()));

    assertThat(healthCheck.check(), is(unhealthy("Unexpected status")));


## Advanced Dropwizard health check

	import static com.ft.dropwizard.matcher.AdvancedHealthCheckResult.healthy;
	import static com.ft.dropwizard.matcher.AdvancedHealthCheckResult.unhealthy;

    assertThat(healthCheck.check(), is(healthy()));

    assertThat(healthCheck.check(), is(unhealthy("Unexpected status")));


## Server response error

    import static com.ft.dropwizard.matcher.ServerResponseWithCode.errorCode;
 
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
 
    expectedException.expect(errorCode(503));

## Response headers

    import static com.ft.dropwizard.matcher.ResponseHeaders.containsHeader;
 
    MultivaluedMap<String, Object> headers = response.getHeaders(); // where response is a Jersey client response
    assertThat(headers, containsHeader(HTTP.CONTENT_TYPE);
    assertThat(headers, containsHeader(HTTP.CONTENT_TYPE).withValue("application/json; charset=UTF-8; qs=1"));
    
