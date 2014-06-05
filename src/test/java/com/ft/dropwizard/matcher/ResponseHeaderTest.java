package com.ft.dropwizard.matcher;

import static com.ft.dropwizard.matcher.ResponseHeaders.containsHeader;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.http.protocol.HTTP;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.sun.jersey.core.header.OutBoundHeaders;



public class ResponseHeaderTest {
	
	
	private static final String CONTENT_TYPE_VALUE = "application/json; charset=UTF-8; qs=1";
	private static final List<String> EXPECTED_VALUE = ImmutableList.of(CONTENT_TYPE_VALUE);
	private static MultivaluedMap<String, Object> headers;
	
	@Before
	public void setup() {
		headers = new OutBoundHeaders();
		headers.add(HTTP.CONTENT_TYPE, CONTENT_TYPE_VALUE);
	}
	
	@Test
	public void headerExists() {
		assertThat(headers, containsHeader(HTTP.CONTENT_TYPE));
	}
	
	@Test
	public void headerExistsAndValueMatches() {
		assertThat(headers, containsHeader(HTTP.CONTENT_TYPE).withValue(EXPECTED_VALUE));
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
