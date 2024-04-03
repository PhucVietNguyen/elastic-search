package com.phucnv.elasticsearch.service;

import com.phucnv.elasticsearch.model.PersonDocument;
import com.phucnv.elasticsearch.model.dto.PersonDto;
import java.util.List;

public interface PersonService {

  PersonDocument savePersonDocument(PersonDto personDto);

  List<PersonDocument> matchPersonByFullName(String fullName);

  List<PersonDocument> matchPhrasePersonByFullName(String fullName);

  List<PersonDocument> termPersonDocumentsByAge(Integer age);

  List<PersonDocument> wildcardPersonByFullName(String fullName);

  List<PersonDocument> rangePersonByAgeCriteria(Integer age);

  List<PersonDocument> matchPersonByFullNameStringQuery(String fullName);

  List<PersonDocument> matchPersonByFullNameNativeQuery(String fullName);
}
