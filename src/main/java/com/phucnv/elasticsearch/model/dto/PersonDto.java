package com.phucnv.elasticsearch.model.dto;

import com.phucnv.elasticsearch.model.enums.EGender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {

  private String firstName;

  private String lastName;

  private String fullName;

  private Integer age;

  private EGender gender;
}
