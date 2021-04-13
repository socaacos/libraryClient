
package com.example.library.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.BookControllerApi;
import org.openapitools.client.model.Author;
import org.openapitools.client.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.library.dtos.AuthorDto;
import com.example.library.dtos.BookDto;
import com.example.library.exceptions.BookNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("books")
@Slf4j
public class BookController {
	
	@Autowired
	BookControllerApi bookApi;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	ModelMapper modelMapper;
	/*
	@GetMapping
	public void getEmployee() throws RestClientException, IOException {

		List<ServiceInstance> instances=discoveryClient.getInstances("EMPLOYEE-ZUUL-SERVICE");
		ServiceInstance serviceInstance=instances.get(0);
		System.out.println(serviceInstance.getPort());
		
		String baseUrl=serviceInstance.getUri().toString();
		
		baseUrl = baseUrl + "/producer/books";
		
		System.out.println(baseUrl);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response=null;
		try{
		response=restTemplate.exchange(baseUrl,
				HttpMethod.GET, getHeaders(),String.class);
		}catch (Exception ex)
		{
			System.out.println(ex);
		}
		System.out.println(response.getBody());
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}*/
	
	@GetMapping()
	@ResponseBody
	public List<BookDto> getBooks(@RequestParam(required = false) AuthorDto authorDto, @RequestParam(required = false) String title, @RequestParam(required = false) Integer page) {
		
		if (authorDto == null && title == null)
		{
			List<Book> books;
			try {
				books = bookApi.getBooks(page == null?1:page);
				List<BookDto> bookDtos = new ArrayList<BookDto>();
				for (Object book : books) {
					System.out.println(book.getClass());
					System.out.println(book);
					bookDtos.add(modelMapper.map(book, BookDto.class));
				}
							
				return bookDtos;
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				throw new BookNotFoundException();			}
			
		}
		Author author = modelMapper.map(authorDto, Author.class);
		List<Book> books;
		try {
			books = bookApi.booksByAuthor(author, title, page == null?1:page);
			List<BookDto> bookDtos = new ArrayList<BookDto>();
			for (Book book : books) {
				bookDtos.add(modelMapper.map(book, BookDto.class));
			}
						
			return bookDtos;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			throw new BookNotFoundException();		}
		
	}
	
	@GetMapping("/search")
	@ResponseBody
	public List<BookDto> searchBooks(@RequestParam String title, @RequestParam(required = false) Integer page) {
		List<Book> books;
		try {
			books = bookApi.searchBooks(title, page == null?1:page);
			List<BookDto> bookDtos = new ArrayList<BookDto>();
			for (Book book : books) {
				bookDtos.add(modelMapper.map(book, BookDto.class));
			}
						
			return bookDtos;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			throw new BookNotFoundException();		}
		
		
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public BookDto getBookById(@PathVariable int id) {
		Book book;
		try {
			book = bookApi.getBookById(id);
			BookDto bookDto = modelMapper.map(book, BookDto.class);
			return bookDto;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			throw new BookNotFoundException();		}
		
	}

	
	@PostMapping
	@ResponseBody
	public BookDto createBook(@RequestBody BookDto newBookDto, @RequestHeader("Authorization") String auth) {
		System.out.println(auth);
		Book book = modelMapper.map(newBookDto, Book.class);
		Book newBook;
		try {
			bookApi.getApiClient().addDefaultHeader("Authorization", auth);
			newBook = bookApi.createBook(book);
			return modelMapper.map(newBook, BookDto.class);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			throw new BookNotFoundException();		}
	    
	}
	
	@DeleteMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
    public String deleteBook(@PathVariable int id, @RequestHeader("Authorization") String auth) {
		try {
			bookApi.getApiClient().addDefaultHeader("Authorization", auth);
			bookApi.deleteBook(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			throw new BookNotFoundException();		}
		return "Successfuly deleted.";
		
    }	
	
	@PutMapping(path="/{id}", produces = "application/json")
	@ResponseBody
	public BookDto updateBook(@PathVariable int id, @RequestBody BookDto newBookDto, @RequestHeader("Authorization") String auth) {
		Book book = modelMapper.map(newBookDto, Book.class);		
		Book newBook;
		try {
			bookApi.getApiClient().addDefaultHeader("Authorization", auth);
			newBook = bookApi.updateBook(id, book);
			return modelMapper.map(newBook, BookDto.class);	
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			throw new BookNotFoundException();		}
		
	    
	}

}
