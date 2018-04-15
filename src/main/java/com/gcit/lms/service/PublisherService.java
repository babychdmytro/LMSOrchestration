package com.gcit.lms.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gcit.lms.entity.Publisher;

@RestController
public class PublisherService {

	@Autowired
	RestTemplate restTemplate;
	

	//Reading all publishers
	@RequestMapping(value = "/lms/publishers", method = RequestMethod.GET)
	public Publisher[] getAllPublishers(HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Publisher[]> responseEntity = restTemplate.getForEntity("http://localhost:8081/publishers", Publisher[].class);
			Publisher[] publishers = responseEntity.getBody();
			return publishers;
		} catch (RestClientException e) {
			response.sendError(404, "Invalid URL");
			return null;
		}

	}
	
	
	//Reading publisher by Id
	@RequestMapping(value = "/lms/publishers/{publisherId}", method = RequestMethod.GET)
	public Publisher getPublisherById(@PathVariable int publisherId, HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Publisher> responseEntity = restTemplate.getForEntity("http://localhost:8081/publishers/" + publisherId , Publisher.class);
			Publisher publisher = responseEntity.getBody();
			return publisher;
		} catch (RestClientException e) {
			response.sendError(404, "Invalid id, publisher does not exist in the database.");
			return null;
		}
	}
	
	//Saving publisher
	@RequestMapping(value = "/lms/publisher", method = RequestMethod.POST)
	public HttpHeaders savePublisher(@RequestBody Publisher publisher, HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Publisher> responseEntity = restTemplate.postForEntity("http://localhost:8081/publisher/", publisher, Publisher.class);
			response.setStatus(201);
			return responseEntity.getHeaders();
		} catch (RestClientException e) {
			response.sendError(400, "Invalid request caused by invalid body parameters.");
			return null;
		}
	}
	
	//Deleting publisher
	@RequestMapping(value = "/lms/publishers/{publisherId}", method = RequestMethod.DELETE)
	public void deletePublisher(@PathVariable Integer publisherId , HttpServletResponse response) throws IOException {
		try {
			restTemplate.delete("http://localhost:8081/publishers/" + publisherId);
			response.setStatus(204);
		} catch (RestClientException e) {
			response.sendError(404, "Invalid id, publisher does not exist in database.");
		}
		
	}
	
	//Updating publisher
	@RequestMapping(value = "/lms/publishers/{publisherId}", method = RequestMethod.PUT, consumes = {"application/xml", "application/json"})
	public HttpHeaders updatePublisher(@RequestBody Publisher publisher, @PathVariable int publisherId, @RequestHeader HttpHeaders headers, HttpServletResponse response) throws IOException {
		try {
			HttpEntity<Publisher> requestUpdate = new HttpEntity<>(publisher, headers);
			ResponseEntity<Publisher> responseEntity = restTemplate.exchange("http://localhost:8081/publishers/" + publisherId, HttpMethod.PUT, requestUpdate, Publisher.class);
			return responseEntity.getHeaders();
		} catch (RestClientException e) {
			response.sendError(400, "Invalid request caused by invalid body parameters.");		
			return null;
		}
	}
	
}
