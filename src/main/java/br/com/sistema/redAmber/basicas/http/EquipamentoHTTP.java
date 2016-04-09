package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.basicas.enums.StatusEquipamento;

public class EquipamentoHTTP {

	private Long id;
	private String descricao;
	private String tombo;
	private Sala sala;
	private StatusEquipamento status;
	
	/*
	 * Construtor padrão
	 */
	public EquipamentoHTTP() {}
	
	/*
	 * Construtor com parâmetros
	 */
	public EquipamentoHTTP(Long id, String descricao, String tombo, Sala sala,
			StatusEquipamento status) {
		this.id = id;
		this.descricao = descricao;
		this.tombo = tombo;
		this.sala = sala;
		this.status = status;
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