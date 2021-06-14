package br.com.ecommerce.application.reserva;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.ecommerce.domain.Estoque;
import br.com.ecommerce.infrastructure.EstoqueRepository;
import br.com.ecommerce.infrastructure.kafka.KafkaTopics;
import br.com.ecommerce.infrastructure.kafka.pedido.Pedido;

@Service
public class ReservarEstoqueApplicationService {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ReservarEstoqueApplicationService.class);

	
	@Autowired
	private EstoqueRepository repository;
	
	@Autowired
	private KafkaTemplate<String, Pedido> kafkaTemplate;
	

	@KafkaListener(containerFactory = "pedidoKafkaListenerContainerFactory", topics = "ECOMMERCE_PEDIDO_NOVO")
	public void criarEstoque(Pedido pedido) {

		logger.info("reservando estoque para o pedido {} mercadoria {}", pedido.getId(), pedido.getCodigoProduto());

		Optional<Estoque> findById = repository.findById(pedido.getCodigoProduto());
		if (findById.isEmpty()) {
			notificarIndisponibilidade(pedido);
			return;
		} 
		Estoque estoque = findById.get();
		efetivarReserva(pedido, estoque);

	}

	private void efetivarReserva(Pedido pedido, Estoque estoque) {

		boolean reservar = estoque.reservar(pedido.getQuantidade());
		repository.save(estoque);
		if (reservar) {
			notificarReserva(pedido);
		} else {
			notificarIndisponibilidade(pedido);
		}
	}

	private void notificarReserva(Pedido pedido) {
		kafkaTemplate.send(KafkaTopics.ECOMMERCE_PEDIDO_ESTOQUE_RESERVADO.toString(), pedido);

	}

	private void notificarIndisponibilidade(Pedido pedido) {
		kafkaTemplate.send(KafkaTopics.ECOMMERCE_PEDIDO_ESTOQUE_INVALIDO.toString(), pedido);
	}

}
