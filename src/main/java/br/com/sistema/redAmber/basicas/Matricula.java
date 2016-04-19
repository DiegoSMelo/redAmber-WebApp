package br.com.sistema.redAmber.basicas;

import java.io.Serializable;
import java.util.Calendar;

import br.com.sistema.redAmber.basicas.enums.StatusMatricula;

public class Matricula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String codigoMatricula;
	
	private Aluno aluno;
	
	private Calendar dataMatricula;
	
	private Grade grade;

	private Integer entrada;
	
	private StatusMatricula status;
	
	private Turma turma;

	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoMatricula() {
		return codigoMatricula;
	}

	public void setCodigoMatricula(String codigoMatricula) {
		this.codigoMatricula = codigoMatricula;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Calendar getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Calendar dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	public StatusMatricula getStatus() {
		return status;
	}

	public void setStatus(StatusMatricula status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getEntrada() {
		return entrada;
	}

	public void setEntrada(Integer entrada) {
		this.entrada = entrada;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}