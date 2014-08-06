package com.ft.dropwizard.matcher;

import com.ft.platform.dropwizard.AdvancedResult;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class AdvancedHealthCheckResult extends TypeSafeMatcher<AdvancedResult> {

	private static final boolean HEALTHY = true;
	private static final boolean UNHEALTHY = false;
	private static final String HEALTHY_DESCRIPTION = "healthy";
	private static final String UNHEALTHY_DESCRIPTION = "unhealthy";

	private boolean healthy;
	private String description;
	private String errorMessage;
	private String errorDetail;

	private AdvancedHealthCheckResult(boolean healthy, String description) {
		this(healthy, description, null);
	}

	private AdvancedHealthCheckResult(boolean healthy, String description, String errorMessage) {
		this(healthy, description, errorMessage, null);
	}

	private AdvancedHealthCheckResult(boolean healthy, String description, String errorMessage, String errorDetail) {
		this.healthy = healthy;
		this.description = description;
		this.errorMessage = errorMessage;
		this.errorDetail = errorDetail;
	}

	@Override
	protected boolean matchesSafely(AdvancedResult result) {
		boolean correctResult = result != null && result.asResult().isHealthy()== healthy;
		String message = result == null ? null : result.asResult().getMessage();
		if (errorMessage == null) {
			return correctResult;
		} else if(errorDetail == null) {
			return correctResult && message.contains(errorMessage);
		} else {
			return correctResult && message.contains(errorMessage) && message.contains(errorDetail);
		}
	}

	@Override
	public void describeTo(Description description) {
		if (errorMessage == null) {
			description.appendText(this.description);
		} else {
			description.appendText(String.format("%s with error message containing [%s]", this.description, errorMessage));
			if(errorDetail != null) {
				description.appendText(String.format(", and the detail: [%s]", errorDetail));
			}
		}
	}

	@Factory
	public static Matcher<AdvancedResult> healthy() {
		return new AdvancedHealthCheckResult(HEALTHY, HEALTHY_DESCRIPTION);
	}

	@Factory
	public static Matcher<AdvancedResult> unhealthy(String errorMessage) {
		return new AdvancedHealthCheckResult(UNHEALTHY, UNHEALTHY_DESCRIPTION, errorMessage);
	}

	@Factory
	public static Matcher<AdvancedResult> unhealthy(String errorMessage, String errorDetail) {
		return new AdvancedHealthCheckResult(UNHEALTHY, UNHEALTHY_DESCRIPTION, errorMessage, errorDetail);
	}
}


