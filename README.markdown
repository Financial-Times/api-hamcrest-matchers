# API Hamcrest Matchers

This library provides Hamcrest Matchers useful when testing various APIs.


## JSON

> import static com.ft.dropwizard.matcher.Json.validJson;
>
> assertThat(json, is(validJson()));


## JSON-LD

> import static com.ft.dropwizard.matcher.JsonLd.validJsonLd;
>
> assertThat(jsonLd, is(validJsonLd()));


## JSON-LD

> import static com.ft.dropwizard.matcher.JsonLd.validJsonLd;
>
> assertThat(jsonLd, is(validJsonLd()));


## Dropwizard health check

> import static com.ft.dropwizard.matcher.HealthCheckResult.healthy;
> import static com.ft.dropwizard.matcher.HealthCheckResult.unhealthy;
>
> assertThat(healthCheck.check(), is(healthy()));
> assertThat(healthCheck.check(), is(unhealthy("Unexpected status")));