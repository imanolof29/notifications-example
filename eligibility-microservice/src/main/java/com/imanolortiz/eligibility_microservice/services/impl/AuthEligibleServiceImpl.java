package com.imanolortiz.eligibility_microservice.services.impl;

import com.imanolortiz.eligibility_microservice.commons.AuthEligibleEvent;
import com.imanolortiz.eligibility_microservice.commons.UserLoggedInEvent;
import com.imanolortiz.eligibility_microservice.services.AuthEligibleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthEligibleServiceImpl implements AuthEligibleService {


    @Override
    public Mono<AuthEligibleEvent> eligibilityAuth(UserLoggedInEvent event) {
        return Mono.just(event)
                .map(given -> AuthEligibleEvent
                        .builder()
                        .userId(event.getUserId())
                        .build()
                );
    }

}
