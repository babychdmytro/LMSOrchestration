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

import com.gcit.lms.entity.Author;

@RestController
public class AuthorService {

	@Autowired
	RestTemplate restTemplate;
	

	//Reading all authors
	@RequestMapping(value = "/lms/authors", method = RequestMethod.GET)
	public Author[] getAllAuthors(HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Author[]> responseEntity = restTemplate.getForEntity("http://localhost:8081/authors", Author[].class);
			Author[] authors = responseEntity.getBody();
			return authors;
		} catch (RestClientException e) {
			response.sendError(404, "Invalid URL");
			return null;
		}

	}
	
	
	//Reading author by Id
	@RequestMapping(value = "/lms/authors/{authorId}", method = RequestMethod.GET)
	public Author getAuthorById(@PathVariable int authorId, HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Author> responseEntity = restTemplate.getForEntity("http://localhost:8081/authors/" + authorId , Author.class);
			Author author = responseEntity.getBody();
			return author;
		} catch (RestClientException e) {
			
			response.sendError(404, "Invalid id, author does not exist in the database.");
			return null;
		}
	}
	
	//Saving author
	@RequestMapping(value = "/lms/author", method = RequestMethod.POST)
	public HttpHeaders saveAuthor(@RequestBody Author author, HttpServletResponse response) throws IOException {
		try {
			ResponseEntity<Author> responseEntity = restTemplate.postForEntity("http://localhost:8081/author/", author, Author.class);
			response.setStatus(201);
			return responseEntity.getHeaders();
		} catch (RestClientException e) {
			response.sendError(400, "Invalid request caused by invalid body parameters.");
			return null;
		}
	}
	
	//Deleting author
	@RequestMapping(value = "/lms/authors/{authorId}", method = RequestMethod.DELETE)
	public void deleteAuthor(@PathVariable Integer authorId , HttpServletResponse response) throws IOException {
		try {
			restTemplate.delete("http://localhost:8081/authors/" + authorId);
			response.setStatus(204);
		} catch (RestClientException e) {
			response.sendError(404, "Invalid id, author does not exist in database.");
		}
		
	}
	
	//Updating author
	@RequestMapping(value = "/lms/authors/{authorId}", method = RequestMethod.PUT, consumes = {"application/xml", "application/json"})
	public HttpHeaders updateAuthor(@RequestBody Author author, @PathVariable int authorId, @RequestHeader HttpHeaders headers, HttpServletResponse response) throws IOException {
		try {
			HttpEntity<Author> requestUpdate = new HttpEntity<>(author, headers);
			ResponseEntity<Author> responseEntity = restTemplate.exchange("http://localhost:8081/authors/" + authorId, HttpMethod.PUT, requestUpdate, Author.class);
			return responseEntity.getHeaders();
		} catch (RestClientException e) {
			response.sendError(400, "Invalid request caused by invalid body parameters.");		
			return null;
		}
	}
	
}
