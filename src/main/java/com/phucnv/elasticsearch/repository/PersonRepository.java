package com.phucnv.elasticsearch.repository;

import com.phucnv.elasticsearch.model.PersonDocument;
import java.util.List;
import java.util.UUID;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ElasticsearchRepository<PersonDocument, UUID> {

  @Query("{\"match\": {\"fullName\": \"?0\"}}")
  List<PersonDocument> matchPersonDocumentsByFullName(String fullName);

  @Query("{\"match_phrase\": {\"fullName\": \"?0\"}}")
  List<PersonDocument> matchPhrasePersonDocumentsByFullName(String fullName);

  @Query("{\"term\": {\"age\": \"?0\"}}")
  List<PersonDocument> termPersonDocumentsByAge(Integer age);

  @Query("{\"wildcard\": {\"fullName\": \"?0\"}}")
  List<PersonDocument> wildcardPersonDocumentsByAge(String fullName);
}
