package com.example.library.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.AuthorControllerApi;
import org.openapitools.client.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dtos.AuthorDto;
import com.example.library.exceptions.BookNotFoundException;


@RestController
@RequestMapping("authors")

public class AuthorController {
	
	@Autowired
	AuthorControllerApi authorApi;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping()
	@ResponseBody
	public List<AuthorDto> getAuthors() {
		
		List<Author> authors;
		try {
			authors = authorApi.getAuthors();
			List<AuthorDto> authorDtos = new ArrayList<AuthorDto>();
			for (Author author : authors) {
				authorDtos.add(modelMapper.map(author, AuthorDto.class));
			}
							
				return authorDtos;
		} catch (ApiException e) {
			throw new BookNotFoundException();
		}
		
	}
	
	@GetMapping("/search")
	@ResponseBody
	public List<AuthorDto> searchAuthors(@RequestParam String name) {
		List<Author> authors;
		try {
			authors = authorApi.searchAuthors(name);
			List<AuthorDto> authorDtos = new ArrayList<AuthorDto>();
			for (Author author: authors) {
				authorDtos.add(modelMapper.map(author, AuthorDto.class));
			}
						
			return authorDtos;
		} catch (ApiException e) {
		
			throw new BookNotFoundException();
		}
		
		
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public AuthorDto getAuthorById(@PathVariable int id) {
		Author author;
		try {
			author = authorApi.getAuthorById(id);
			AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
			return authorDto;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			throw new BookNotFoundException();		}
		}
	
	@PostMapping
	@ResponseBody
	public AuthorDto createAuthor(@RequestBody AuthorDto newAuthorDto) {
		Author author = modelMapper.map(newAuthorDto, Author.class);
		Author newAuthor;
		try {
			newAuthor = authorApi.createAuthor(author);
			return modelMapper.map(newAuthor, AuthorDto.class);
		} catch (ApiException e) {
			throw new BookNotFoundException();		}
	    
	}
	
	@DeleteMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
    public String deleteAuthor(@PathVariable int id) {
		try {
			authorApi.deleteAuthor(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			throw new BookNotFoundException();		}
		return "Successfuly deleted.";
		
    }	
	
	@PutMapping(path="/{id}", produces = "application/json")
	@ResponseBody
	public AuthorDto updateAuthor(@PathVariable int id, @RequestBody AuthorDto newAuthorDto) {
		Author author = modelMapper.map(newAuthorDto, Author.class);		
		try {
			Author newAuthor = authorApi.updateAuthor(id, author);
			System.out.println(newAuthor);
			return modelMapper.map(newAuthor, AuthorDto.class);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			throw new BookNotFoundException();		}
		
	    
	}

}
