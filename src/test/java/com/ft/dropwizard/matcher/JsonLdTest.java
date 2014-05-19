package com.ft.dropwizard.matcher;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonLdTest {

	private static final String VALID_JSON = "{\n" +
			"    \"firstName\": \"John\",\n" +
			"    \"lastName\": \"Smith\",\n" +
			"    \"isAlive\": true,\n" +
			"    \"age\": 25,\n" +
			"    \"height_cm\": 167.64,\n" +
			"    \"address\": {\n" +
			"        \"streetAddress\": \"21 2nd Street\",\n" +
			"        \"city\": \"New York\",\n" +
			"        \"state\": \"NY\",\n" +
			"        \"postalCode\": \"10021-3100\"\n" +
			"    },\n" +
			"    \"phoneNumbers\": [\n" +
			"        { \"type\": \"home\", \"number\": \"212 555-1234\" },\n" +
			"        { \"type\": \"fax\",  \"number\": \"646 555-4567\" }\n" +
			"    ]\n" +
			"}";

	private static final String VALID_JSON_LD = "{\n" +
			"  \"@context\": {\n" +
			"    \"name\": \"http://xmlns.com/foaf/0.1/name\",\n" +
			"    \"homepage\": {\n" +
			"      \"@id\": \"http://xmlns.com/foaf/0.1/workplaceHomepage\",\n" +
			"      \"@type\": \"@id\"\n" +
			"    },\n" +
			"    \"Person\": \"http://xmlns.com/foaf/0.1/Person\"\n" +
			"  },\n" +
			"  \"@id\": \"http://me.markus-lanthaler.com\",\n" +
			"  \"@type\": \"Person\",\n" +
			"  \"name\": \"Markus Lanthaler\",\n" +
			"  \"homepage\": \"http://www.tugraz.at/\"\n" +
			"}";

	private static final String INVALID_JSON_LD = "{\n" +
			"  \"@context\": {\n" +
			"    \"name\": \"http://xmlns.com/foaf/0.1/name\",\n" +
			"    \"homepage\": {\n" +
			"      \"@id\": \"http://xmlns.com/foaf/0.1/workplaceHomepage\",\n" +
			"      \"@type\": \"@id\"\n" +
			"    }\n" + // Missing comma.
			"    \"Person\": \"http://xmlns.com/foaf/0.1/Person\"\n" +
			"  },\n" +
			"  \"@id\": \"http://me.markus-lanthaler.com\",\n" +
			"  \"@type\": \"Person\",\n" +
			"  \"name\": \"Markus Lanthaler\",\n" +
			"  \"homepage\": \"http://www.tugraz.at/\"\n" +
			"}";

	@Test
	public void testValidJson() {
		assertThat(VALID_JSON, is(not(JsonLd.validJsonLd())));
	}

	@Test
	public void testValidJsonLd() {
		assertThat(VALID_JSON_LD, is(JsonLd.validJsonLd()));
	}

	@Test
	public void testInvalidJsonLd() {
		assertThat(INVALID_JSON_LD, is(not(JsonLd.validJsonLd())));
	}
}
