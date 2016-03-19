package br.com.sistema.redAmber.basicas;

import br.com.sistema.redAmber.basicas.enums.StatusTurma;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;

public class Turma {
	
	private Long id;
	
	private String nome;
	
	private Curso curso;
	
	private TipoTurno turno;
	
	private StatusTurma status;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public TipoTurno getTurno() {
		return turno;
	}

	public void setTurno(TipoTurno turno) {
		this.turno = turno;
	}
	
	public StatusTurma getStatus() {
		return status;
	}

	public void setStatus(StatusTurma status) {
		this.status = status;
	}

}
