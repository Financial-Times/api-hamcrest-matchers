package com.ft.dropwizard.matcher;

import com.ft.platform.dropwizard.AdvancedHealthCheck;
import com.ft.platform.dropwizard.AdvancedResult;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdvancedHealthCheckResultTest {

	private AdvancedHealthCheck healthCheck = new TestAdvancedHealthCheck();

	@Test
	public void healthyResultShouldMatchHealthy() {
		assertThat(AdvancedHealthCheckResult.healthy().matches(AdvancedResult.healthy()), is(true));
	}

	@Test
	public void unhealthyResultShouldMatchUnhealthy() {
		assertThat(AdvancedHealthCheckResult.unhealthy("does not exist").matches(AdvancedResult.error(healthCheck, "does not exist")),
				is(true));
	}

	@Test
	public void unhealthyResultShouldNotMatchUnhealthyWithWrongMessage() {
		assertThat(AdvancedHealthCheckResult.unhealthy("does not exist").matches(AdvancedResult.error(healthCheck, "not healthy")),
				is(false));
	}

	@Test
	public void unhealthyResultShouldNotMatchUnhealthyWithWrongDetailedMessage() {
		assertThat(AdvancedHealthCheckResult.unhealthy("does not exist", "filenotfound").matches(AdvancedResult.error(healthCheck, "does not exist")),
				is(false));
	}

	@Test
	public void unhealthyResultShouldMatchUnhealthyWithCorrectDetailedMessage() {
		assertThat(AdvancedHealthCheckResult.unhealthy("does not exist", "filenotfound").matches(
				AdvancedResult.error(healthCheck, "does not exist, filenotfound")), is(true));
	}

	class TestAdvancedHealthCheck extends AdvancedHealthCheck {

		protected TestAdvancedHealthCheck() {
			super("TestAdvancedHealthCheck");
		}

		@Override
		protected AdvancedResult checkAdvanced() throws Exception {
			return AdvancedResult.healthy();
		}

		@Override
		protected int severity() {
			return 3;
		}

		@Override
		protected String businessImpact() {
			return "none";
		}

		@Override
		protected String technicalSummary() {
			return "testing...";
		}

		@Override
		protected String panicGuideUrl() {
			return "";
		}
	}
}
