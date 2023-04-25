package com.webfluxdemo.webfluxdemo;

import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


@RequiredArgsConstructor
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<UpdateResult> changeEnabled(String id, Boolean enable){

        Query query = query(where("id").is(id));

        Update update = new Update();

        update.set("enabled",enable);

        return reactiveMongoTemplate.updateFirst(query,update,Customer.class);
    }

}
