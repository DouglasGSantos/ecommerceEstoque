package br.com.ecommerce.presentation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.application.atualizacao.AdicionarEstoqueApplicationService;
import br.com.ecommerce.application.atualizacao.request.EstoqueAtualizacaoRequest;
import br.com.ecommerce.application.consulta.ConsultarEstoqueService;
import br.com.ecommerce.application.consulta.response.EstoqueResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	private ConsultarEstoqueService service;
	
	@Autowired
	private AdicionarEstoqueApplicationService atualizarEstoqueService;

	@GetMapping("/{codigo}")
	public ResponseEntity<EstoqueResponse> getDisponibilidade(@PathVariable("codigo") String codigo) {
		EstoqueResponse estoqueById = service.getEstoqueById(codigo);
		return ResponseEntity.ok(estoqueById);
	}
	
	
	@PutMapping
	public void atualizar(@Valid @RequestBody EstoqueAtualizacaoRequest request) {
		
		atualizarEstoqueService.executar(request);
		
		
	}
}
