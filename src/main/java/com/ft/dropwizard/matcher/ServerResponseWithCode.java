package com.ft.dropwizard.matcher;

import com.sun.jersey.api.client.UniformInterfaceException;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerResponseWithCode extends TypeSafeMatcher<UniformInterfaceException> {

	private int code;

	private ServerResponseWithCode(int responseCode) {
		this.code = responseCode;
	}

	@Override
	protected boolean matchesSafely(UniformInterfaceException exception) {
		return exception.getResponse().getStatus() == code;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("Response with code: " + code);
	}

	@Factory
	public static Matcher<? super UniformInterfaceException> errorCode(int errorCode) {
		return new ServerResponseWithCode(errorCode);
	}

	@Factory
	public static Matcher<? super UniformInterfaceException> responseCode(int responseCode) {
		return new ServerResponseWithCode(responseCode);
	}
}
