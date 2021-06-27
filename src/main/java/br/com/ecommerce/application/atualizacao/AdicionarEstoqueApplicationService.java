package br.com.ecommerce.application.atualizacao;

import static java.util.Objects.isNull;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.application.atualizacao.request.EstoqueAtualizacaoRequest;
import br.com.ecommerce.domain.Estoque;
import br.com.ecommerce.infrastructure.EstoqueRepository;
import br.com.ecommerce.infrastructure.exception.types.BusinessException;

@Service
public class AdicionarEstoqueApplicationService {

	private static final BusinessException REQUEST_NULL = new BusinessException("as informações do estoque não podem ser nulas");
	private static final BusinessException NO_CONTENT = new BusinessException("O produto informado não foi encontrado");
	
	@Autowired
	private EstoqueRepository repository;
	
	public void executar(EstoqueAtualizacaoRequest request) {
		
		validarRequisicaoNula(request);
		
		Estoque estoque = getEstoque(request);
		estoque.adicionarEstoque(request.getQuantidade());
		
		repository.save(estoque);
		
		
	}

	private Estoque getEstoque(EstoqueAtualizacaoRequest request) {
		Optional<Estoque> findById = repository.findById(request.getId());
		
		if(findById.isEmpty()) {
			throw NO_CONTENT;
		}
		
		return findById.get();		
	}

	private void validarRequisicaoNula(EstoqueAtualizacaoRequest request) {
		if(isNull(request)) {
		throw REQUEST_NULL;	
		}
	}
	
}
