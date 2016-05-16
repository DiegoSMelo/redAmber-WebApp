package br.com.sistema.redAmber.basicas;

import java.io.Serializable;
import java.util.Calendar;

import br.com.sistema.redAmber.basicas.enums.StatusAvisoProfessor;
import br.com.sistema.redAmber.basicas.enums.TipoAvisoProfessor;

public class AvisoProfessor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Professor professor;
	private Calendar dataAviso;
	private String observacao;
	private TipoAvisoProfessor tipoAvisoProfessor;
	private StatusAvisoProfessor statusAvisoProfessor;
	
	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
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
	public TipoAvisoProfessor getTipoAvisoProfessor() {
		return tipoAvisoProfessor;
	}
	public void setTipoAvisoProfessor(TipoAvisoProfessor tipoAvisoProfessor) {
		this.tipoAvisoProfessor = tipoAvisoProfessor;
	}
	public StatusAvisoProfessor getStatusAvisoProfessor() {
		return statusAvisoProfessor;
	}
	public void setStatusAvisoProfessor(StatusAvisoProfessor statusAvisoProfessor) {
		this.statusAvisoProfessor = statusAvisoProfessor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvisoProfessor other = (AvisoProfessor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}