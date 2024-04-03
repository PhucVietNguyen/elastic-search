package com.phucnv.elasticsearch.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
@Getter
@Setter
public class EsProperties {

  private String clusterNode;

  private String clusterName;

  private int clusterPort;
}
