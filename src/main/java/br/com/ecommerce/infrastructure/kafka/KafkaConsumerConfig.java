package br.com.ecommerce.infrastructure.kafka;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.ecommerce.infrastructure.kafka.produto.Produto;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;


    public ConsumerFactory<String, Produto> produtoConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "estoque_ecommerce");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "ecommerce_estoque");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); 
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Produto.class,false ) {
		});
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Produto> produtoKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Produto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(produtoConsumerFactory());        
        return factory;
    }
}