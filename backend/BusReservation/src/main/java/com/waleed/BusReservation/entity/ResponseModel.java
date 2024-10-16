package com.waleed.BusReservation.entity;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {

  private Integer statusCode;
  private String message;
  private Map<String, Object> object;
}
