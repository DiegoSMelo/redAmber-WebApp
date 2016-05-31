package br.com.sistema.redAmber.basicas.integracao;

import java.io.Serializable;
import java.util.Calendar;

import br.com.sistema.redAmber.basicas.Grade;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.StatusMatricula;

public class MatriculaIntegracao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;	
	private String codigoMatricula;
	private Long idAluno;	
	private Calendar dataMatricula;
	private Grade grade;
	private Integer entrada;
	private Turma turma;
	private StatusMatricula status;
	
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
	public Long getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Calendar getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(Calendar dataMatricula) {
		this.dataMatricula = dataMatricula;
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
		MatriculaIntegracao other = (MatriculaIntegracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}