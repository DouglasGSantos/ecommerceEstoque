package br.com.ecommerce.application.criacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.ecommerce.domain.Estoque;
import br.com.ecommerce.infrastructure.EstoqueRepository;
import br.com.ecommerce.infrastructure.kafka.produto.Produto;

@Service
public class CriarEstoqueService {

	
	@Autowired
	private EstoqueRepository repository;
	
	@KafkaListener(topics = "ECOMMERCE_PRODUTO_NOVO",containerFactory = "produtoKafkaListenerContainerFactory")
	public void criarEstoque(Produto produto) {
		
		if(produto!=null) {
			
			Estoque estoque = new Estoque();
			estoque.setCodigoProduto(produto.getCodigo());
			estoque.setQuantidadeDisponivel(0L);
			estoque.setQuantidadeReservada(0L);
			repository.save(estoque);
		}
		
		
		
		
	}
	
	
}
