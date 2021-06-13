package br.com.ecommerce.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.ecommerce.domain.Estoque;

public interface EstoqueRepository extends MongoRepository<Estoque, String>{

}
