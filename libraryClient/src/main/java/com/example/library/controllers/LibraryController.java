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

import com.example.library.dtos.LibraryDto;
import com.example.library.services.LibraryService;

@RestController
@RequestMapping("libraries")

public class LibraryController {
	
	@Autowired
	LibraryService libraryService;
	
	@GetMapping()
	@ResponseBody
	public List<LibraryDto> getAll()
	{
		List<LibraryDto> libraries = libraryService.getAll();						
		return libraries;	
	}
	
	@GetMapping("/search")
	@ResponseBody
	public List<LibraryDto> searchLibraries(@RequestParam String libraryName) {
		List<LibraryDto> libraries=  libraryService.getByName(libraryName);		
		return libraries;
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public LibraryDto getById(@PathVariable int id)
	{
		LibraryDto library= libraryService.getById(id);
		return library;
	}
	
	@PostMapping
	@ResponseBody
	public LibraryDto createLibrary(@RequestBody LibraryDto newLibraryDto) {
		LibraryDto newLibrary= libraryService.create(newLibraryDto);
	    return newLibrary;
	}
	
	@DeleteMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
    public String deleteCity(@PathVariable int id) {
		libraryService.delete(id);
		return "Successfuly deleted.";
		
    }	
	
	@PutMapping(path="/{id}", produces = "application/json")
	@ResponseBody
	public LibraryDto updateLibrary(@PathVariable int id, @RequestBody LibraryDto newLibraryDto) {
		LibraryDto newLibrary = libraryService.update(id, newLibraryDto);		
		return newLibrary;	
	    
	}

}
