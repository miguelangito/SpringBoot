package com.riwi.BeautySalon.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResp {

    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private List<AppointmentToClient> appointments;

}
