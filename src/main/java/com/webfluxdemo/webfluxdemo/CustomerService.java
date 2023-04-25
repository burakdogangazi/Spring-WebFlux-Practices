package com.webfluxdemo.webfluxdemo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;
import java.util.List;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepo repo;

    public Mono<Customer> createCustomer(Mono<CreateDto> createDtoMono){
        return createDtoMono.map(createDto -> Customer.builder()
                .email(createDto.getEmail())
                .name(createDto.getName())
                .build())
                .flatMap(customer -> repo.insert(customer)
                        .doOnError(throwable -> Mono.error(new RuntimeException("Customer create failed"))));
    }

    public Mono<Customer> findCustomer(String id){
        log.info("FindCustomer : {}",id);

        //return repo.findAll().next(); // fluxtan mono dönüşüm aynı çözüm / mono aslında flux altında

        return repo.findById(id)
                .doOnNext(customer -> log.info("Found customer {}",customer))
                .doOnError(throwable -> log.error("Customer Not Found"));
    }

    public Flux<Customer> findAllCustomer(){
        return repo.findAll();
    }

    public Mono<List<Customer>> findAllAsList(){
        return repo.findAll().collectList(); // artık mono oldu tek bir customer listesi anlamında
    }

    public Mono<Void> deleteCustomer(String id){
        return findCustomer(id)
                .flatMap(customer -> repo.deleteById(id));
    }

    public Mono<String> changeStatus(String id, Boolean status){
        return Mono.empty();
    }

    // subscribe olmak burada gerekmiyor çünkü browser açınca subscribe otomatik oluyor. Console uygulamasında subscribe olmalıyız.

}
