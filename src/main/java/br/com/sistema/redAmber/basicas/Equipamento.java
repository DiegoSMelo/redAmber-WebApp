package br.com.sistema.redAmber.basicas;

import java.io.Serializable;

import br.com.sistema.redAmber.basicas.enums.StatusEquipamento;

public class Equipamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private String tombo;
	private Sala sala;
	private StatusEquipamento status;

	/*
	 * Construtor padrão
	 */
	public Equipamento() {}
	
	/*
	 * Construtor com parâmetros
	 */
	public Equipamento(Long id, String descricao, String tombo, Sala sala, StatusEquipamento status) {
		this.id = id;
		this.descricao = descricao;
		this.tombo = tombo;
		this.sala = sala;
		this.status = status;
	}
	
	/*
	 * Construtor com parâmetros (exceto tombo)
	 */
	public Equipamento(Long id, String descricao, Sala sala, StatusEquipamento status) {
		this.id = id;
		this.descricao = descricao;
		this.sala = sala;
		this.status = status;
	}

	/*
	 * Método equals sobrescrito. Ele serve para definir os critérios que definem se um objeto é ou
	 * não igual a outro.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || this == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Equipamento other = (Equipamento) obj;
		if (other.getId().intValue() == this.getId().intValue()) {
			return true;
		}
		if (other.getTombo().equalsIgnoreCase(this.getTombo())) {
			return true;
		}
		return false;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getTombo() {
		return tombo;
	}
	
	public void setTombo(String tombo) {
		this.tombo = tombo;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public StatusEquipamento getStatus() {
		return status;
	}

	public void setStatus(StatusEquipamento status) {
		this.status = status;
	}
}