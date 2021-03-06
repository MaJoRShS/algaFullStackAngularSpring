package com.algaworks.comercial.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//Aqui só de deixar assim o spring sabe que é uma entidade de banco de dados isso é JPA
@Entity
public class Oportunidade {

	// Aqui é para mostrar que isso é um ID
	@Id

	// e isso é para mostrar que é um auto incremente do banco
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Aqui segundo a lenda não precisa mais do @Column porque parece que o JPA
	// mapea tudo.
	@NotEmpty
	@Size(max = 80)
	@Column(name = "nome_prospecto")
	private String nomeProspecto;

	// Essas novas anotações são para validação de campo, aqui eu digo que ele não
	// pode ser nulo e nem maior que 200 por exemplo
	@NotEmpty
	@Size(max = 200)
	private String descricao;

	// aqui eu digo que ele precisa ser no minimo 0
	@Min(0)
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProspecto() {
		return nomeProspecto;
	}

	public void setNomeProspecto(String nomeProspecto) {
		this.nomeProspecto = nomeProspecto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oportunidade other = (Oportunidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
