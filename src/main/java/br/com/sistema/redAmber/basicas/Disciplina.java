package br.com.sistema.redAmber.basicas;

import br.com.sistema.redAmber.basicas.enums.StatusDisciplina;

public class Disciplina {
	
	private Long id;
	
	private String titulo;
	
	
	private String descricao;
	
	private StatusDisciplina status;
	
	public Disciplina() {
		
	}
	
	
	public Disciplina(Long id, String titulo, String descricao, StatusDisciplina status) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
	}

	/*
	 * Método equals sobrescrito. Ele serve para definir os critérios que definem se um objeto é ou
	 * não igual a outro.
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		if (obj == null || this == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		try {
			
			Disciplina other = (Disciplina) obj;
			if (other.getId() != null && other.getId().intValue() == this.getId().intValue()) {
				return true;
			}
			if (other.getTitulo() != null && other.getTitulo().equalsIgnoreCase(this.getTitulo())) {
				return true;
			}
			
		} catch (NullPointerException e) {
			return false;
		}
		
		return false;
		
	}
	
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
