package com.ft.dropwizard.matcher;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.rio.Rio;

import java.io.IOException;
import java.io.StringReader;

public class JsonLd extends TypeSafeMatcher<String> {

	private JsonLd() {
	}

	@Override
	protected boolean matchesSafely(String string) {
		try {
			return Rio.parse(new StringReader(string), "", RDFFormat.JSONLD).contexts().size() > 0;
		} catch(RDFParseException | IOException e) {
			return false;
		}
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("valid JSON-LD");
	}

	@Factory
	public static Matcher<String> validJsonLd() {
		return new JsonLd();
	}
}