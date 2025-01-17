package com.imanolortiz.eligibility_microservice.services;

import com.imanolortiz.eligibility_microservice.commons.AuthEligibleEvent;
import com.imanolortiz.eligibility_microservice.commons.UserLoggedInEvent;
import reactor.core.publisher.Mono;

public interface AuthEligibleService {
    Mono<AuthEligibleEvent> eligibilityAuth(UserLoggedInEvent event);
}
