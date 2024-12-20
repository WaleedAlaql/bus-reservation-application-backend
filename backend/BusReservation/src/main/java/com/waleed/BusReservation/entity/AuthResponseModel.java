package com.waleed.BusReservation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseModel {
    
    private String httpStatus;
    private String message;
    private String accessToken;
    private Long logInTime;
    private Long expirationDuration;
}
