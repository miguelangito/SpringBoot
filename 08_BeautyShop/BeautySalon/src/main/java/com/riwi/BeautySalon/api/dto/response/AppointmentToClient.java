package com.riwi.BeautySalon.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentToClient {

    private Long id;

    private LocalTime dateTime;

    private Integer duration;

    private String comments;

    private ServiceResp service;

    private EmployeeResp employee;

}
