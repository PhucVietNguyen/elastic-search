package com.phucnv.elasticsearch.model;

import com.phucnv.elasticsearch.model.enums.EGender;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "person-index")
@Getter
@Setter
@Builder
public class PersonDocument {

  @Id private UUID id;

  @Field(type = FieldType.Text)
  private String firstName;

  @Field(type = FieldType.Text)
  private String lastName;

  @Field(type = FieldType.Text)
  private String fullName;

  @Field(type = FieldType.Integer)
  private Integer age;

  @Field(type = FieldType.Text)
  private EGender gender;
}
