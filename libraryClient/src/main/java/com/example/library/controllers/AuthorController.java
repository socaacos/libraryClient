package com.example.library.controllers;

import java.util.List;

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
import com.example.library.services.AuthorService;

@RestController
@RequestMapping("authors")

public class AuthorController {
	
	@Autowired
	AuthorService authorService;

	@GetMapping()
	@ResponseBody
	public List<AuthorDto> getAuthors() {
		
		List<AuthorDto> authors = authorService.getAll();
		
						
			return authors;
	}
	
	@GetMapping("/search")
	@ResponseBody
	public List<AuthorDto> searchAuthors(@RequestParam String name) {
		List<AuthorDto> authors =  authorService.searchByName(name);
		return authors;
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public AuthorDto getAuthorById(@PathVariable int id) {
		AuthorDto author = authorService.getById(id);
		return author;
	}
	
	@PostMapping
	@ResponseBody
	public AuthorDto createAuthor(@RequestBody AuthorDto newAuthorDto) {
		AuthorDto newAuthor = authorService.create(newAuthorDto);
	    return newAuthor;
	}
	
	@DeleteMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
    public String deleteAuthor(@PathVariable int id) {
		authorService.delete(id);
		return "Successfuly deleted.";
		
    }	
	
	@PutMapping(path="/{id}", produces = "application/json")
	@ResponseBody
	public AuthorDto updateAuthor(@PathVariable int id, @RequestBody AuthorDto newAuthorDto) {
		AuthorDto newAuthor = authorService.update(id, newAuthorDto);		
		return newAuthor;	
	    
	}

}
