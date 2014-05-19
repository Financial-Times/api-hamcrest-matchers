package com.ft.dropwizard.matcher;

import com.ft.dropwizard.matcher.HealthCheckResult;
import com.yammer.metrics.core.HealthCheck;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HealthCheckResultTest {

	@Test
	public void healthyResultShouldMatchHealthy() {
		assertThat(HealthCheckResult.healthy().matches(HealthCheck.Result.healthy()), is(true));
	}

	@Test
	public void unhealthyResultShouldMatchUnhealthy() {
		assertThat(HealthCheckResult.unhealthy("does not exist").matches(HealthCheck.Result.unhealthy("does not exist")),
				is(true));
	}

	@Test
	public void unhealthyResultShouldNotMatchUnhealthyWithWrongMessage() {
		assertThat(HealthCheckResult.unhealthy("does not exist").matches(HealthCheck.Result.unhealthy("not healthy")),
				is(false));
	}

	@Test
	public void unhealthyResultShouldNotMatchUnhealthyWithWrongDetailedMessage() {
		assertThat(HealthCheckResult.unhealthy("does not exist", "filenotfound").matches(HealthCheck.Result.unhealthy("does not exist")),
				is(false));
	}

	@Test
	public void unhealthyResultShouldMatchUnhealthyWithCorrectDetailedMessage() {
		assertThat(HealthCheckResult.unhealthy("does not exist", "filenotfound").matches(
				HealthCheck.Result.unhealthy("does not exist, filenotfound")), is(true));
	}

}
