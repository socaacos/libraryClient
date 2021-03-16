package com.example.library.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.LibraryControllerApi;
import org.openapitools.client.model.Library;
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

import com.example.library.dtos.CityDto;
import com.example.library.dtos.LibraryDto;

@RestController
@RequestMapping("libraries")

public class LibraryController {
	
	@Autowired
	LibraryControllerApi libraryApi;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping()
	@ResponseBody
	public List<LibraryDto> getAll()
	{
		List<Library> libraries;
		try {
			libraries = libraryApi.getAll();
			List<LibraryDto> libraryDtos = new ArrayList<LibraryDto>();
			for (Library library : libraries) {
				libraryDtos.add(modelMapper.map(library, LibraryDto.class));
			}
							
				return libraryDtos;	
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@GetMapping("/search")
	@ResponseBody
	public List<LibraryDto> searchLibraries(@RequestParam String libraryName) {
		List<Library> libraries;
		try {
			libraries = libraryApi.searchLibraries(libraryName);
			List<LibraryDto> libraryDtos = new ArrayList<LibraryDto>();
			for (Library library: libraries) {
				libraryDtos.add(modelMapper.map(library, LibraryDto.class));
			}
						
			return libraryDtos;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public LibraryDto getById(@PathVariable int id)
	{
		Library library;
		try {
			library = libraryApi.getById(id);
			LibraryDto libraryDto = modelMapper.map(library, LibraryDto.class);
			return libraryDto;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@PostMapping
	@ResponseBody
	public LibraryDto createLibrary(@RequestBody LibraryDto newLibraryDto) {
		Library library = modelMapper.map(newLibraryDto, Library.class);
		Library newLibrary;
		try {
			newLibrary = libraryApi.createLibrary(library);
		    return modelMapper.map(newLibrary, LibraryDto.class);

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
    public String deleteCity(@PathVariable int id) {
		try {
			libraryApi.deleteCity(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Successfuly deleted.";
		
    }	
	
	@PutMapping(path="/{id}", produces = "application/json")
	@ResponseBody
	public LibraryDto updateLibrary(@PathVariable int id, @RequestBody LibraryDto newLibraryDto) {
		Library library = modelMapper.map(newLibraryDto, Library.class);		
		Library newLibrary;
		try {
			newLibrary = libraryApi.updateLibrary(id, library);
			return modelMapper.map(newLibrary, LibraryDto.class);	

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	    
	}

}
