package com.algaworks.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.comercial.model.Oportunidade;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {

	/*
	 * Aqui o Spring já faz a implementação automaticamente, segundo o mano isso é
	 * uma convenção e toda as vezes que eu uso o findBy e passo dois paramentros
	 * separados por And ele já cria uma busca para mim com aqueles dois parametros
	 * como condição
	 */
	Optional<Oportunidade> findByDescricaoAndNomeProspecto(String descricao, String nomeProspecto);
}
