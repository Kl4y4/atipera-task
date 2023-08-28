package com.atipera.task;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootTest
class TaskApplicationTests {

	@Test
	public void notGivenUsername_whenEndpointIsCalled_then400IsReceived() throws URISyntaxException, IOException, InterruptedException {

		HttpRequest httpRequest = HttpRequest.newBuilder()
				.uri(new URI("http://localhost:8080/repos"))
				.header("accept", "application/json")
				.GET()
				.build();

		HttpClient httpClient = HttpClient.newBuilder().build();

		HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

		assert response.statusCode() == 400;

	}

	@Test
	public void givenWrongHeader_whenEndpointIsCalled_then406IsReceived() throws URISyntaxException, IOException, InterruptedException {

		HttpRequest httpRequest = HttpRequest.newBuilder()
				.uri(new URI("http://localhost:8080/repos?username=kl4y4"))
				.header("accept", "application/xml")
				.GET()
				.build();

		HttpClient httpClient = HttpClient.newBuilder().build();

		HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

		System.out.print(response.statusCode());

		assert response.statusCode() == 406;

	}

	@Test
	public void givenRightParams_whenEndpointIsCalled_then200IsReceived() throws URISyntaxException, IOException, InterruptedException {

		HttpRequest httpRequest = HttpRequest.newBuilder()
				.uri(new URI("http://localhost:8080/repos?username=kl4y4"))
				.header("accept", "application/json")
				.GET()
				.build();

		HttpClient httpClient = HttpClient.newBuilder().build();

		HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

		System.out.print(response.statusCode());

		assert response.statusCode() == 200;

	}

}
