package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.enums.StatusSala;
import br.com.sistema.redAmber.basicas.enums.TipoSala;

public class SalaHTTP {

	private Long id;
	private String descricao;
	private Integer capacidadeAlunos;
	private TipoSala tipoSala;
	private StatusSala status;
	
	/*
	 * Construtor padrão
	 */
	public SalaHTTP() {}
	
	/*
	 * Construtor com parâmetros
	 */
	public SalaHTTP(Long id, String descricao, Integer capacidadeAlunos, TipoSala tipoSala,
			StatusSala status) {
		this.id = id;
		this.descricao = descricao;
		this.capacidadeAlunos = capacidadeAlunos;
		this.tipoSala = tipoSala;
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

	public Integer getCapacidadeAlunos() {
		return capacidadeAlunos;
	}

	public void setCapacidadeAlunos(Integer capacidadeAlunos) {
		this.capacidadeAlunos = capacidadeAlunos;
	}

	public TipoSala getTipoSala() {
		return tipoSala;
	}

	public void setTipoSala(TipoSala tipoSala) {
		this.tipoSala = tipoSala;
	}
	
	public StatusSala getStatus() {
		return status;
	}

	public void setStatus(StatusSala status) {
		this.status = status;
	}
}