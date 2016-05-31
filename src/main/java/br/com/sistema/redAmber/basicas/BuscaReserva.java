package br.com.sistema.redAmber.basicas;

import java.util.Date;

import br.com.sistema.redAmber.basicas.enums.StatusReserva;

public class BuscaReserva {

	private Long idProfessor;
	private Date dataReserva;
	private Date dataRequisicao;
	private StatusReserva status;
	
	/**
	 * Getters and setters
	 */
	public Long getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	public Date getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}
	public Date getDataRequisicao() {
		return dataRequisicao;
	}
	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}
	public StatusReserva getStatus() {
		return status;
	}
	public void setStatus(StatusReserva status) {
		this.status = status;
	}
}