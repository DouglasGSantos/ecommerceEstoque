package br.com.ecommerce.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "estoques")
public class Estoque {

	@Id
	private String codigoProduto;

	private Long quantidadeDisponivel;

	private Long quantidadeReservada;

	public Estoque(String codigoProduto, Long quantidadeDisponivel, Long quantidadeReservada) {
		super();
		this.codigoProduto = codigoProduto;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.quantidadeReservada = quantidadeReservada;
	}

	public Estoque() {
		super();
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public Long getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(Long quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

	public Long getQuantidadeReservada() {
		return quantidadeReservada;
	}

	public void setQuantidadeReservada(Long quantidadeReservada) {
		this.quantidadeReservada = quantidadeReservada;
	}

	public boolean reservar(int quantidade) {

		if (quantidadeDisponivel > 0 && (quantidadeDisponivel - quantidade >= 0)) {
			quantidadeDisponivel -= quantidade;
			quantidadeReservada += quantidade;
			return true;
		}
		return false;
	}

	public boolean isDisponivel() {
		return quantidadeDisponivel > 0;
	}

	public void adicionarEstoque(Long quantidade) {
		this.quantidadeDisponivel += quantidade;

	}

}
