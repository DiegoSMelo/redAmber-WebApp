package br.com.sistema.redAmber.basicas;

import br.com.sistema.redAmber.basicas.enums.StatusDisciplina;

public class Disciplina {
	
	private Long id;
	
	private String titulo;
	
	
	private String descricao;
	
	private StatusDisciplina status;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusDisciplina getStatus() {
		return status;
	}

	public void setStatus(StatusDisciplina status) {
		this.status = status;
	}	
}
