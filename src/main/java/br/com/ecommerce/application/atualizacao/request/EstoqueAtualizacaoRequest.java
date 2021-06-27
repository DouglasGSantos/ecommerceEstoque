package br.com.ecommerce.application.atualizacao.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstoqueAtualizacaoRequest {
	@NotBlank
	private String id;
	
	@NotNull
	@Min(value = 1)
	private Long quantidade;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
