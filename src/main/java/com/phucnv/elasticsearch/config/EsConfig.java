package com.phucnv.elasticsearch.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.phucnv.elasticsearch.config.properties.EsProperties;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EsConfig {

  private final EsProperties esProperties;

  @Bean
  public RestClient getRestClient() {
    RestClient restClient =
        RestClient.builder(
                new HttpHost(esProperties.getClusterNode(), esProperties.getClusterPort()))
            .build();
    return restClient;
  }

  @Bean
  public ElasticsearchTransport getElasticsearchTransport() {
    return new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
  }

  @Bean
  public ElasticsearchClient getElasticsearchClient() {
    ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
    return client;
  }
}
