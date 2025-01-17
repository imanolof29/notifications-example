package com.imanolortiz.eligibility_microservice.configuration;

import com.imanolortiz.eligibility_microservice.commons.AuthEligibleEvent;
import com.imanolortiz.eligibility_microservice.commons.UserLoggedInEvent;
import com.imanolortiz.eligibility_microservice.processors.EligibilityAuthProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class StreamConfig {

    @Bean
    public Function<Flux<UserLoggedInEvent>, Flux<AuthEligibleEvent>> userLoggedInBinding(final EligibilityAuthProcessor processor){
        return processor::process;
    }

}
