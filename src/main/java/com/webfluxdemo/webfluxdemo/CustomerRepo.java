package com.webfluxdemo.webfluxdemo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepo extends ReactiveMongoRepository<Customer,String> {





}
