package com.imanolortiz.eligibility_microservice.commons;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoggedInEvent {

    private String userId;

}
