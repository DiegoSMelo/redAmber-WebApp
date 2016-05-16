package br.com.sistema.redAmber.basicas;

import java.io.Serializable;
import java.util.Calendar;

import br.com.sistema.redAmber.basicas.enums.StatusReserva;

public class ReservaSala implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Professor professor;
	private Sala sala;
	private Calendar dataRequisicao;
	private Calendar dataReserva;
	private DuracaoAula horarioReserva;
	private String observacao;
	private String resposta;
	private StatusReserva status;
	
	/*
	 * Método equals sobrescrito. Ele serve para estabelecer os critérios que definem se um objeto é ou
	 * não igual a outro.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservaSala other = (ReservaSala) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

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

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Calendar getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(Calendar dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	public Calendar getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Calendar dataReserva) {
		this.dataReserva = dataReserva;
	}

	public DuracaoAula getHorarioReserva() {
		return horarioReserva;
	}

	public void setHorarioReserva(DuracaoAula horarioReserva) {
		this.horarioReserva = horarioReserva;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public StatusReserva getStatus() {
		return status;
	}

	public void setStatus(StatusReserva status) {
		this.status = status;
	}
}