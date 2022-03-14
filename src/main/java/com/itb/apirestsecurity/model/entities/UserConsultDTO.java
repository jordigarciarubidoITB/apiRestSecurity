package com.itb.apirestsecurity.model.entities;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UserConsultDTO {
    private String username;
    private String avatar;
    private String rol;
}