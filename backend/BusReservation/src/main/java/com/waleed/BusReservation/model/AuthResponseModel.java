package com.waleed.BusReservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseModel {

  private Integer statusCode;
  private String message;
  private String accessToken;
  private Long loginTime;
  private Long expirationDuration;
}
