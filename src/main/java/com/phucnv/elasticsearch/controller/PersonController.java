package com.phucnv.elasticsearch.controller;

import com.phucnv.elasticsearch.model.PersonDocument;
import com.phucnv.elasticsearch.model.dto.PersonDto;
import com.phucnv.elasticsearch.service.PersonService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/person")
@RequiredArgsConstructor
public class PersonController {

  private final PersonService personService;

  @PostMapping
  public ResponseEntity<UUID> insertPerson(@RequestBody PersonDto personDto) {
    PersonDocument personDocument = personService.savePersonDocument(personDto);
    return ResponseEntity.ok(personDocument.getId());
  }

  @GetMapping(value = "/match")
  public ResponseEntity<List<PersonDocument>> matchPerson(
      @RequestParam("fullName") String fullName) {
    List<PersonDocument> personDocuments = personService.matchPersonByFullNameNativeQuery(fullName);
    return ResponseEntity.ok(personDocuments);
  }

  @GetMapping(value = "/match-phrase")
  public ResponseEntity<List<PersonDocument>> matchPhrasePerson(
          @RequestParam("fullName") String fullName) {
    List<PersonDocument> personDocuments = personService.matchPhrasePersonByFullName(fullName);
    return ResponseEntity.ok(personDocuments);
  }

  @GetMapping(value = "/term")
  public ResponseEntity<List<PersonDocument>> termPerson(@RequestParam("age") Integer age) {
    List<PersonDocument> personDocuments = personService.termPersonDocumentsByAge(age);
    return ResponseEntity.ok(personDocuments);
  }

  @GetMapping(value = "/wildcard")
  public ResponseEntity<List<PersonDocument>> wildcardPerson(
          @RequestParam("fullName") String fullName) {
    List<PersonDocument> personDocuments = personService.wildcardPersonByFullName(fullName);
    return ResponseEntity.ok(personDocuments);
  }

  @GetMapping(value = "/range")
  public ResponseEntity<List<PersonDocument>> rangePerson(@RequestParam("age") Integer age) {
    List<PersonDocument> personDocuments = personService.rangePersonByAgeCriteria(age);
    return ResponseEntity.ok(personDocuments);
  }

}
