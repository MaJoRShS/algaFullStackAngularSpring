package com.algaworks.comercial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.comercial.model.Oportunidade;
import com.algaworks.comercial.repository.OportunidadeRepository;

//Aqui mostra para o spring que isso aqui é uma API
@RestController

//Aqui é onde eu to mapeando as minhas URI
@RequestMapping("/oportunidades")
public class OportunidadeController {

	// Aqui é a primeira forma que ele fez.

	// aqui eu to falando que essa é minha API e vou usar o GET como metodo
//@GetMapping

	// Vai ser retornado no metodo.
//	public List<Oportunidade> listar() {
//		Oportunidade oportunidade = new Oportunidade();
//		oportunidade.setId(1L);
//		oportunidade.setDescricao("Desenvolvimento de ERP com Angular e Spring");
//		oportunidade.setNomeProspecto("The Shadows Technology");
//		oportunidade.setValor(new BigDecimal(490000));
//		return Arrays.asList(oportunidade);
//	}

	@Autowired
	private OportunidadeRepository oportunidades;

	// Segunda forma
	// Aqui eu não fiz nada e trouxe todos os registros que existem na base de dados
	@GetMapping
	public List<Oportunidade> listar() {
		return oportunidades.findAll();
	}

	// Essa aqui é uma forma de buscar os resultados por ID, mais tem um problema
	// que ela retorna 200 mesmo quando está nullo a resposta
//	@GetMapping("/{id}")
//	public Optional<Oportunidade> buscar(@PathVariable Long id){
//		return oportunidades.findById(id);
//	}

	// Aqui ele está retonando 404 caso não exista registro no banco de dados para
	// serem retornados.
	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = oportunidades.findById(id);

		if (oportunidade.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(oportunidade.get());
	}

	// Aqui eu to conseguindo inserir um novo registro no banco de dados, bem
	// simples apenas mandando um JSON na chamada
	@PostMapping

	// Essa anotação é responsavel por traser a informação de status 201 informando
	// que é um created e não apenas uma requisição de sucesso.

	// Aqui acrescentei o @Valid que permite fazer validações nos campos antes de
	// salvar minha requisição, e ainda formata para devolver como resposta.
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade adcionar(@Valid @RequestBody Oportunidade oportunidade) {

		Optional<Oportunidade> oportunidadeExistente = oportunidades
				.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());
		
		if(oportunidadeExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Já existe uma oportunidade para este prospecto com a mesma descrição");
		}

		return oportunidades.save(oportunidade);
		
		
		//Mandou implementar o resto do crud por conta propria
	}

}
