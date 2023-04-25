package com.webfluxdemo.webfluxdemo;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public Mono<Customer> createCustomer(@RequestBody CustomerCreateRequest req){

        return service.createCustomer(Mono.just(CreateDto.builder()
                .email(req.getEmail())
                .name(req.getName())
                .build()));

    }

    /*@GetMapping("/{id}")
    public Mono<Customer> getCustomer(@PathVariable("id") String id){
        return service.findCustomer(id);
    }*/

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Customer>> getCustomer(@PathVariable("id") String id){
        return service.findCustomer(id)
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping
    public Flux<Customer> getAll(){
        return service.findAllCustomer();
    }

    @DeleteMapping("/{id}")
    public Mono<String> deleteCustomer(@PathVariable("id") String id){
        return service.deleteCustomer(id).thenReturn("Deleted");
        //pipe tamamlanınca deleted dön ile string dönme işlemi gerçekleşti.
    }



    @PatchMapping("/{id}/status")
    public Mono<String> changeStatus(@PathVariable("id") String id, @RequestBody Mono<ChangeStatusReq> reqMono){
        return reqMono.flatMap(req -> service.changeStatus(id,req.getEnabled()));
    }





}
