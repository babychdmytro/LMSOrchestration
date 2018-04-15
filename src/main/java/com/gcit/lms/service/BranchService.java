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

import com.gcit.lms.entity.Branch;

@RestController
public class BranchService {

	@Autowired
	RestTemplate restTemplate;
	

	//Reading all branches
	@RequestMapping(value = "/lms/branches", method = RequestMethod.GET)
	public Branch[] getAllBranches(HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Branch[]> responseEntity = restTemplate.getForEntity("http://localhost:8082/branches", Branch[].class);
			Branch[] branches = responseEntity.getBody();
			return branches;
		} catch (RestClientException e) {
			response.sendError(404, "Invalid URL");
			return null;
		}

	}
	
	
	//Reading branch by Id
	@RequestMapping(value = "/lms/branches/{branchId}", method = RequestMethod.GET)
	public Branch getBranchById(@PathVariable int branchId, HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Branch> responseEntity = restTemplate.getForEntity("http://localhost:8082/branches/" + branchId , Branch.class);
			Branch branch = responseEntity.getBody();
			return branch;
		} catch (RestClientException e) {
			response.sendError(404, "Invalid id, branch does not exist in the database.");
			return null;
		}
	}
	
	//Saving branch
	@RequestMapping(value = "/lms/branch", method = RequestMethod.POST)
	public HttpHeaders saveBranch(@RequestBody Branch branch, HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Branch> responseEntity = restTemplate.postForEntity("http://localhost:8082/branch/", branch, Branch.class);
			response.setStatus(201);
			return responseEntity.getHeaders();
		} catch (RestClientException e) {
			response.sendError(400, "Invalid request caused by invalid body parameters.");
			return null;
		}
	}
	
	//Deleting branch
	@RequestMapping(value = "/lms/branches/{branchId}", method = RequestMethod.DELETE)
	public void deleteBranch(@PathVariable Integer branchId , HttpServletResponse response) throws IOException {
		try {
			restTemplate.delete("http://localhost:8082/branches/" + branchId);
			response.setStatus(204);
		} catch (RestClientException e) {
			response.sendError(404, "Invalid id, branch does not exist in database.");
		}
		
	}
	
	//Updating branch
	@RequestMapping(value = "/lms/branches/{branchId}", method = RequestMethod.PUT, consumes = {"application/xml", "application/json"})
	public HttpHeaders updateBranch(@RequestBody Branch branch, @PathVariable int branchId, @RequestHeader HttpHeaders headers, HttpServletResponse response) throws IOException {
		try {
			HttpEntity<Branch> requestUpdate = new HttpEntity<>(branch, headers);
			ResponseEntity<Branch> responseEntity = restTemplate.exchange("http://localhost:8082/branches/" + branchId, HttpMethod.PUT, requestUpdate, Branch.class);
			return responseEntity.getHeaders();
		} catch (RestClientException e) {
			response.sendError(400, "Invalid request caused by invalid body parameters.");		
			return null;
		}
	}
	
}
