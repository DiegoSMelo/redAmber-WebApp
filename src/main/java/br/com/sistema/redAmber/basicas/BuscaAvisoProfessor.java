package br.com.sistema.redAmber.basicas;

import java.util.Date;

import br.com.sistema.redAmber.basicas.enums.StatusAvisoProfessor;

public class BuscaAvisoProfessor {

	private Long idProfessor;
	private Date dataAviso;
	private StatusAvisoProfessor status;
	
	/**
	 * Getters and setters
	 */
	public Long getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	public Date getDataAviso() {
		return dataAviso;
	}
	public void setDataAviso(Date dataAviso) {
		this.dataAviso = dataAviso;
	}
	public StatusAvisoProfessor getStatus() {
		return status;
	}
	public void setStatus(StatusAvisoProfessor status) {
		this.status = status;
	}
}