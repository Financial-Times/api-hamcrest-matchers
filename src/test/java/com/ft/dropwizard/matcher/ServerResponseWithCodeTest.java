package com.ft.dropwizard.matcher;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.core.header.InBoundHeaders;
import com.sun.jersey.spi.MessageBodyWorkers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ServerResponseWithCodeTest {

	@Mock
	private InBoundHeaders headers;
	@Mock
	private MessageBodyWorkers workers;

	private InputStream entity;

	@Before
	public void setup() {
		entity = new ByteArrayInputStream("Test".getBytes(StandardCharsets.UTF_8));
	}

	@Test
	public void shouldReturnValidErrorCode() {
		ClientResponse response = new ClientResponse(503, headers, entity, workers);
		UniformInterfaceException error503 = new UniformInterfaceException(response);
		assertThat(error503, is(ServerResponseWithCode.errorCode(503)));
	}

	@Test
	public void shouldReturnValidResponseCode() {
		ClientResponse response = new ClientResponse(204, headers, entity, workers);
		UniformInterfaceException result204 = new UniformInterfaceException(response);
		assertThat(result204, is(ServerResponseWithCode.responseCode(204)));
	}

}
