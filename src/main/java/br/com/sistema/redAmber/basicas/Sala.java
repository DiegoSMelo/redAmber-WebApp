package br.com.sistema.redAmber.basicas;

import java.io.Serializable;

import br.com.sistema.redAmber.basicas.enums.StatusSala;
import br.com.sistema.redAmber.basicas.enums.TipoSala;

public class Sala implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private Integer capacidadeAlunos;
	private TipoSala tipoSala;
	private StatusSala status;
	
	/*
	 * Construtor padrão
	 */
	public Sala() {}

	/*
	 * Construtor com parâmetros
	 */
	public Sala(Long id, String descricao, Integer capacidadeAlunos, TipoSala tipoSala,
			StatusSala status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.capacidadeAlunos = capacidadeAlunos;
		this.tipoSala = tipoSala;
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
		try {
			
			Sala other = (Sala) obj;
			if (other.getId() != null && other.getId().intValue() == this.getId().intValue()) {
				return true;
			}
			if (other.getDescricao() != null && other.getDescricao().equalsIgnoreCase(this.getDescricao())) {
				return true;
			}
		} catch (NullPointerException e) {
			return false;
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