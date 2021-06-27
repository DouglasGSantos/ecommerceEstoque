package br.com.ecommerce.application.atualizacao;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.ecommerce.application.atualizacao.request.EstoqueAtualizacaoRequest;
import br.com.ecommerce.domain.Estoque;
import br.com.ecommerce.infrastructure.EstoqueRepository;

class AdicionarEstoqueApplicationServiceTest {

	@InjectMocks
	private AdicionarEstoqueApplicationService service = new AdicionarEstoqueApplicationService();
	
	@Mock
	private EstoqueRepository repository;
	
	
	@BeforeEach
	public void setup() {
	
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	void testExecutar() {
		
		when(repository.findById(anyString())).thenReturn(Optional.of(getEstoque()));
		
		service.executar(getEstoqueAtualizarRequest());
		
		verify(repository,times(1)).findById(anyString());
		verify(repository,times(1)).save(ArgumentMatchers.any(Estoque.class));
		
		
	}


	private Estoque getEstoque() {
		Estoque estoque = new Estoque();
		estoque.setCodigoProduto("25");
		estoque.setQuantidadeDisponivel(25L);
		estoque.setQuantidadeReservada(25L);
		
		return estoque;
	}


	private EstoqueAtualizacaoRequest getEstoqueAtualizarRequest() {
	
		EstoqueAtualizacaoRequest request = new EstoqueAtualizacaoRequest();
		request.setId("25");
		request.setQuantidade(1L);
		
		return request;
	}

}
