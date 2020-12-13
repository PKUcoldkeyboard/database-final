package com.cuterwrite.dbfinal.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
/**
 * ElasticSearch配置类
 * @author Pang S.Z.
 * @create 2020-10-08 23:58:03
 */
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration{
	
	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String uri;
	
	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {
		
		final ClientConfiguration clientConfiguration=ClientConfiguration.builder()
				.connectedTo(uri)
				.build();
		
		return RestClients.create(clientConfiguration).rest();
	}
	
	@Bean
	public ElasticsearchRestTemplate elasticsearchRestTemplate() {
		return new ElasticsearchRestTemplate(elasticsearchClient());
	}
}
