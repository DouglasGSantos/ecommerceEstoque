package br.com.ecommerce.application.consulta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.application.consulta.response.EstoqueResponse;
import br.com.ecommerce.domain.Estoque;
import br.com.ecommerce.infrastructure.EstoqueRepository;

@Service
public class ConsultarEstoqueService {

	@Autowired
	private EstoqueRepository repository;

	public EstoqueResponse getEstoqueById(String id) {

		EstoqueResponse response = new EstoqueResponse();
		response.setCodigoProduto(id);

		Optional<Estoque> findById = repository.findById(id);
		if (findById.isEmpty()) {
			return response;
		}
		Estoque estoque = findById.get();
		response.setDisponivel(estoque.isDisponivel());
		return response;

	}

}
