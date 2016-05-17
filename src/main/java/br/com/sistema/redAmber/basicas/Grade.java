package br.com.sistema.redAmber.basicas;

import br.com.sistema.redAmber.basicas.enums.StatusGrade;

public class Grade {
	
	private Long id;
	private Curso curso;
	private String titulo;
	private StatusGrade status;
	
	
	public boolean isAtivo(){
		return this.getStatus().equals(StatusGrade.ATIVA);
	}
	
	public boolean isPendente(){
		return this.getStatus().equals(StatusGrade.PENDENTE);
	}
	
	public boolean isInativo(){
		return this.getStatus().equals(StatusGrade.INATIVA);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grade other = (Grade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public StatusGrade getStatus() {
		return status;
	}

	public void setStatus(StatusGrade status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}	
}