package com.webfluxdemo.webfluxdemo;

import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.bulk.UpdateRequest;
import reactor.core.publisher.Mono;

public interface CustomerRepositoryCustom {

    Mono<UpdateResult> changeEnabled(String id, Boolean enable);
}
