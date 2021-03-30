package com.example.springposgres.repository;

import java.util.List;

import com.example.springposgres.model.Customer;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	List<Customer> findByFirstName(String FirstName);

	List<Customer> findAll();
}