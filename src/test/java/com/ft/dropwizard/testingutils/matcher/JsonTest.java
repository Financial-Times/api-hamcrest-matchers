package com.ft.dropwizard.testingutils.matcher;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonTest {

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

	private static final String INVALID_JSON = "{\n" +
			"    \"firstName\": \"John\",\n" +
			"    \"lastName\": \"Smith\",\n" +
			"    \"isAlive\": true,\n" +
			"    \"age\": 25\n" + // Comma missing.
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

	@Test
	public void testValidJson() {
		assertThat(VALID_JSON, is(Json.validJson()));
	}

	@Test
	public void testInvalidJson() {
		assertThat(INVALID_JSON, is(not(Json.validJson())));
	}
	
}
