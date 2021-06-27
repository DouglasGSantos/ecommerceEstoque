package br.com.ecommerce.infrastructure.kafka;

import org.springframework.beans.factory.annotation.Value;

public class KafkaConfig {
	@Value(value = "${kafka.bootstrapAddress}")
	protected String bootstrapAddress;
}
