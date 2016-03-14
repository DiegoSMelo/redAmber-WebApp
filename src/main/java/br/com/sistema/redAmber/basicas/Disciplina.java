package br.com.sistema.redAmber.basicas;

import java.util.List;

import br.com.sistema.redAmber.basicas.enums.StatusDisciplina;

public class Disciplina {
	
	private Long id;
	
	private String titulo;
	
	private List<Professor> listaProfessores;
	
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

	public List<Professor> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(List<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
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
