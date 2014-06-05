package com.ft.dropwizard.matcher;

import static com.ft.dropwizard.matcher.ResponseHeaders.containsHeader;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.http.protocol.HTTP;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.core.header.InBoundHeaders;



public class ResponseHeadersTest {
	
	
	private static final String CONTENT_TYPE_VALUE = "application/json; charset=UTF-8; qs=1";
	private static MultivaluedMap<String, String> headers;
	
	@Before
	public void setup() {
		headers = new InBoundHeaders();
		headers.add(HTTP.CONTENT_TYPE, CONTENT_TYPE_VALUE);
	}
	
	@Test
	public void headerExists() {
		assertThat(headers, containsHeader(HTTP.CONTENT_TYPE));
	}
	
	@Test
	public void headerExistsAndValueMatches() {
		assertThat(headers, containsHeader(HTTP.CONTENT_TYPE).withValue(CONTENT_TYPE_VALUE));
	}
	
	@Test
	public void headerExistsAndValueDoesNotMatch() {
		assertThat(headers, not(containsHeader(HTTP.CONTENT_TYPE).withValue("Other Content Type")));
	}
	
	@Test
	public void headerDoesNotExist() {
		assertThat(headers, not(containsHeader(HTTP.USER_AGENT)));
	}
	
	@Test
	public void headerDoesNotExistAnyValueSuppliedIsIgnored() {
		assertThat(headers, not(containsHeader(HTTP.USER_AGENT).withValue("Other Content Type")));
	}

}
