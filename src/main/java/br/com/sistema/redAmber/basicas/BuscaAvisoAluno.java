package br.com.sistema.redAmber.basicas;

import java.util.Date;

public class BuscaAvisoAluno {

	private Long idFuncionario;
	private Date dataAviso;
	private Long idTurma;
	
	/**
	 * Getters and setters
	 */
	public Long getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public Date getDataAviso() {
		return dataAviso;
	}
	public void setDataAviso(Date dataAviso) {
		this.dataAviso = dataAviso;
	}
	public Long getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}
}