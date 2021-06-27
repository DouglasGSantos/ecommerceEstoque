package br.com.ecommerce.infrastructure.kafka.produto;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.ecommerce.application.criacao.CriarEstoqueService;
import br.com.ecommerce.infrastructure.kafka.KafkaConfig;

@Configuration
public class KafkaProdutoConsumerConfig extends KafkaConfig {

	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Produto> produtoKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Produto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(produtoConsumerFactory());
		return factory;
	}

	public ConsumerFactory<String, Produto> produtoConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, CriarEstoqueService.class.getName());
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new JsonDeserializer<>(Produto.class, false) {
				});
	}
}
