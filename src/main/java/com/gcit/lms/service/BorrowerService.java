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

import com.gcit.lms.entity.Borrower;

@RestController
public class BorrowerService {
	@Autowired
	RestTemplate restTemplate;
	

	//Reading all borrowers
	@RequestMapping(value = "/lms/borrowers", method = RequestMethod.GET)
	public Borrower[] getAllBorrowers(HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Borrower[]> responseEntity = restTemplate.getForEntity("http://localhost:8082/borrowers", Borrower[].class);
			Borrower[] borrowers = responseEntity.getBody();
			return borrowers;
		} catch (RestClientException e) {
			response.sendError(404, "Invalid URL");
			return null;
		}

	}
	
	
	//Reading borrower by cardNo
	@RequestMapping(value = "/lms/borrowers/{cardNo}", method = RequestMethod.GET)
	public Borrower getBorrowerByCardNo(@PathVariable int cardNo, HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Borrower> responseEntity = restTemplate.getForEntity("http://localhost:8082/borrowers/" + cardNo , Borrower.class);
			Borrower borrower = responseEntity.getBody();
			return borrower;
		} catch (RestClientException e) {
			response.sendError(404, "Invalid id, borrower does not exist in the database.");
			return null;
		}
	}
	
	//Saving borrower
	@RequestMapping(value = "/lms/borrower", method = RequestMethod.POST)
	public HttpHeaders saveBorrower(@RequestBody Borrower borrower, HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Borrower> responseEntity = restTemplate.postForEntity("http://localhost:8082/borrower/", borrower, Borrower.class);
			response.setStatus(201);
			return responseEntity.getHeaders();
		} catch (RestClientException e) {
			response.sendError(400, "Invalid request caused by invalid body parameters.");
			return null;
		}
	}
	
	//Deleting borrower
	@RequestMapping(value = "/lms/borrowers/{cardNo}", method = RequestMethod.DELETE)
	public void deleteBorrower(@PathVariable int cardNo , HttpServletResponse response) throws IOException {
		try {
			restTemplate.delete("http://localhost:8082/borrowers/" + cardNo);
			response.setStatus(204);
		} catch (RestClientException e) {
			response.sendError(404, "Invalid cardNo, borrower does not exist in database.");
		}
		
	}
	
	//Updating borrower
	@RequestMapping(value = "/lms/borrowers/{cardNo}", method = RequestMethod.PUT, consumes = {"application/xml", "application/json"})
	public HttpHeaders updateBorrower(@RequestBody Borrower borrower, @PathVariable int cardNo, @RequestHeader HttpHeaders headers, HttpServletResponse response) throws IOException {
		try {
			HttpEntity<Borrower> requestUpdate = new HttpEntity<>(borrower, headers);
			ResponseEntity<Borrower> responseEntity = restTemplate.exchange("http://localhost:8082/borrowers/" + cardNo, HttpMethod.PUT, requestUpdate, Borrower.class);
			return responseEntity.getHeaders();
		} catch (RestClientException e) {
			response.sendError(400, "Invalid request caused by invalid body parameters.");		
			return null;
		}
	}
}
