package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.enums.StatusDuracaoAula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;

public class DuracaoAulaHTTP {

	private Long id;
	private TipoTurno turno;
	private String horaInicio;
	private String horaFim;
	private StatusDuracaoAula status;
	
	/*
	 * Construtor padrão
	 */
	public DuracaoAulaHTTP(){}
	
	/*
	 * Construtor com parâmetros
	 */
	public DuracaoAulaHTTP(TipoTurno turno, String horaInicio, String horaFim) {
		this.turno = turno;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}
	
	/*
	 * Construtor com parâmetros (completo)
	 */
	public DuracaoAulaHTTP(Long id, TipoTurno turno, String horaInicio, String horaFim,
			StatusDuracaoAula status) {
		this.id = id;
		this.turno = turno;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
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

	public TipoTurno getTurno() {
		return turno;
	}

	public void setTurno(TipoTurno turno) {
		this.turno = turno;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public StatusDuracaoAula getStatus() {
		return status;
	}

	public void setStatus(StatusDuracaoAula status) {
		this.status = status;
	}
}