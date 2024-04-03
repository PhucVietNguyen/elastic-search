package com.phucnv.elasticsearch.service.impl;

import com.phucnv.elasticsearch.mapper.PersonMapper;
import com.phucnv.elasticsearch.model.PersonDocument;
import com.phucnv.elasticsearch.model.dto.PersonDto;
import com.phucnv.elasticsearch.repository.PersonRepository;
import com.phucnv.elasticsearch.service.PersonService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  private final ElasticsearchOperations elasticsearchOperations;

  private final PersonMapper personMapper = PersonMapper.INSTANCE;

  @Override
  public PersonDocument savePersonDocument(PersonDto personDto) {

    PersonDocument document = personMapper.dtoToDocument(personDto);
    document.setId(UUID.randomUUID());
    return personRepository.save(document);
  }

  @Override
  public List<PersonDocument> matchPersonByFullName(String fullName) {
    return personRepository.matchPersonDocumentsByFullName(fullName);
  }

  @Override
  public List<PersonDocument> matchPhrasePersonByFullName(String fullName) {
    return personRepository.matchPhrasePersonDocumentsByFullName(fullName);
  }

  @Override
  public List<PersonDocument> termPersonDocumentsByAge(Integer age) {
    return personRepository.termPersonDocumentsByAge(age);
  }

  @Override
  public List<PersonDocument> wildcardPersonByFullName(String fullName) {
    Query query =
        NativeQuery.builder()
            .withQuery(q -> q.wildcard(m -> m.field("fullName").value(fullName)))
            .build();

    SearchHits<PersonDocument> searchHits =
        elasticsearchOperations.search(query, PersonDocument.class);

    return searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
    //    return personRepository.wildcardPersonDocumentsByAge(fullName);
  }

  /*
   * Elasticsearch query using Criteria
   */
  @Override
  public List<PersonDocument> rangePersonByAgeCriteria(Integer age) {
    Criteria criteria = new Criteria("age").greaterThanEqual(age);
    Query query = new CriteriaQuery(criteria);
    SearchHits<PersonDocument> searchHits =
        elasticsearchOperations.search(query, PersonDocument.class);
    return searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
  }

  /*
   * Elasticsearch query using String query
   */
  @Override
  public List<PersonDocument> matchPersonByFullNameStringQuery(String fullName) {
    Query query =
        new StringQuery("{ \"match\": { \"fullName\": { \"query\": \"" + fullName + "\" } } } ");

    SearchHits<PersonDocument> searchHits =
        elasticsearchOperations.search(query, PersonDocument.class);

    return searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
  }

  /*
   * Elasticsearch query using Native query
   */
  @Override
  public List<PersonDocument> matchPersonByFullNameNativeQuery(String fullName) {
    Query query =
        NativeQuery.builder()
            .withQuery(q -> q.match(m -> m.field("fullName").query(fullName)))
            .build();

    SearchHits<PersonDocument> searchHits =
        elasticsearchOperations.search(query, PersonDocument.class);

    return searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
  }
}
