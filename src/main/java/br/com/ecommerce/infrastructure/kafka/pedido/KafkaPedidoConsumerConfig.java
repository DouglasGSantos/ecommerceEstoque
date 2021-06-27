package br.com.ecommerce.infrastructure.kafka.pedido;

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

import br.com.ecommerce.application.reserva.ReservarEstoqueApplicationService;
import br.com.ecommerce.infrastructure.kafka.KafkaConfig;

@Configuration
public class KafkaPedidoConsumerConfig extends KafkaConfig {

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Pedido> pedidoKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Pedido> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(pedidoConsumerFactory());
		return factory;
	}

	public ConsumerFactory<String, Pedido> pedidoConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, ReservarEstoqueApplicationService.class.getName());
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new JsonDeserializer<>(Pedido.class, false) {
				});
	}

}