package com.ft.dropwizard.matcher;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

public class ResponseHeaders extends TypeSafeMatcher<MultivaluedMap<String, String>> {

	private String headerName;
	private String expectedValue;

	public ResponseHeaders(String headerName, String expectedValue) {
		this.headerName = headerName;
		this.expectedValue = expectedValue;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("Should contain headerName=" + headerName + " expectedValue=" + expectedValue); 
	}

	@Override
	protected boolean matchesSafely(MultivaluedMap<String, String> headers) {
		// TODO - what if either are null?
		if (headers.containsKey(headerName)) {
			if (expectedValue == null) {
				return true;
			}
			List<String> values = headers.get(headerName);
			if (values != null) {
				return values.contains(expectedValue);
			}
			return false;
		} else {
			return false;
		}
	}
	
	public ResponseHeaders withValue(String expectedValue) {
		this.expectedValue = expectedValue;
		return this;
	}
	
	@Factory
	public static ResponseHeaders containsHeaderWithValue(String headerName, String headerValue) {
		return new ResponseHeaders(headerName, headerValue);
	}
	
	@Factory
	public static ResponseHeaders containsHeader(String headerName) {
		return new ResponseHeaders(headerName, null);
	}

}
