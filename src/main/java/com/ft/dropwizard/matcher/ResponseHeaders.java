package com.ft.dropwizard.matcher;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ResponseHeaders extends TypeSafeMatcher<MultivaluedMap<String, Object>> {

	private String headerName;
	private Object expectedValue;

	public ResponseHeaders(String headerName, Object expectedValue) {
		this.headerName = headerName;
		this.expectedValue = expectedValue;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("Should contain headerName=" + headerName + " expectedValue=" + expectedValue); 
	}

	@Override
	protected boolean matchesSafely(MultivaluedMap<String, Object> headers) {
		// TODO - what if either are null?
		if (headers.containsKey(headerName)) {
			if (expectedValue == null) {
				return true;
			}
			return expectedValue.equals(headers.get(headerName));
		} else {
			return false;
		}
	}
	
	public ResponseHeaders withValue(Object expectedValue) {
		this.expectedValue = expectedValue;
		return this;
	}
	
	@Factory
	public static ResponseHeaders containsHeaderWithValue(String headerName, Object headerValue) {
		return new ResponseHeaders(headerName, headerValue);
	}
	
	@Factory
	public static ResponseHeaders containsHeader(String headerName) {
		return new ResponseHeaders(headerName, null);
	}

}
