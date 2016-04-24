package br.com.sistema.redAmber.basicas;

import java.io.Serializable;
import java.util.Calendar;

import br.com.sistema.redAmber.basicas.enums.StatusReserva;

public class ReservaEquipamento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Professor professor;
	private Equipamento equip;
	private Calendar dataRequisicao;
	private Calendar dataReserva;
	private HoraAula horarioReserva;
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
		ReservaEquipamento other = (ReservaEquipamento) obj;
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

	public Equipamento getEquip() {
		return equip;
	}

	public void setEquip(Equipamento equip) {
		this.equip = equip;
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

	public HoraAula getHorarioReserva() {
		return horarioReserva;
	}

	public void setHorarioReserva(HoraAula horarioReserva) {
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