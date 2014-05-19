package com.ft.dropwizard.matcher;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONException;
import org.json.JSONObject;

public class Json extends TypeSafeMatcher<String> {

	private Json() {
	}

	@Override
	protected boolean matchesSafely(String string) {
		try {
			new JSONObject(string);
			return true;
		} catch(JSONException ex) {
			return false;
		}
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("valid JSON");
	}

	@Factory
	public static Matcher<String> validJson() {
		return new Json();
	}
}