package com.itb.apirestsecurity.model.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class ClassStudent {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String classroom;
    private String center;
}