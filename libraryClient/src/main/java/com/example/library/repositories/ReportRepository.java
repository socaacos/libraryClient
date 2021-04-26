package com.example.library.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.library.dtos.Report;

public interface ReportRepository extends CrudRepository<Report, Integer>{
	

}
