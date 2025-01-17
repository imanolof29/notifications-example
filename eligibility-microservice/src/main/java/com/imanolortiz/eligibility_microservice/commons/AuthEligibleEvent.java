package com.imanolortiz.eligibility_microservice.commons;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthEligibleEvent {

    private String userId;

}
