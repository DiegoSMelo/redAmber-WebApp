package br.com.sistema.redAmber.basicas;

import java.io.Serializable;
import java.util.Calendar;

public class AvisoAluno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Funcionario funcionario;
	private Calendar dataAviso;
	private String observacao;
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
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Calendar getDataAviso() {
		return dataAviso;
	}
	public void setDataAviso(Calendar dataAviso) {
		this.dataAviso = dataAviso;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvisoAluno other = (AvisoAluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}