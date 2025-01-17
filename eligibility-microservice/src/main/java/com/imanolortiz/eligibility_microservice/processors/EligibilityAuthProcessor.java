package com.imanolortiz.eligibility_microservice.processors;

import com.imanolortiz.eligibility_microservice.commons.AuthEligibleEvent;
import com.imanolortiz.eligibility_microservice.commons.UserLoggedInEvent;
import com.imanolortiz.eligibility_microservice.services.AuthEligibleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class EligibilityAuthProcessor {

    private AuthEligibleService authEligibleService;

    public EligibilityAuthProcessor(AuthEligibleService authEligibleService){
        this.authEligibleService = authEligibleService;
    }

    public Flux<AuthEligibleEvent> process(Flux<UserLoggedInEvent> userLoggedInEventFlux){
        return userLoggedInEventFlux.doOnNext(given -> log.info("Entry event: {}", given))
                .flatMap(authEligibleService::eligibilityAuth)
                .onErrorContinue(this::handleError);
    }

    private void handleError(Throwable throwable, Object object){
        log.error("Error processing event: {}", object, throwable);
    }

}
