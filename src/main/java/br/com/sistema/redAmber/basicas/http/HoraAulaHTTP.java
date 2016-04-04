package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;

public class HoraAulaHTTP {

	private Long id;
	private TipoTurno turno;
	private String horaInicio;
	private String horaFim;
	private StatusHoraAula status;
	
	/*
	 * Construtor padrão
	 */
	public HoraAulaHTTP(){}
	
	/*
	 * Construtor com parâmetros
	 */
	public HoraAulaHTTP(TipoTurno turno, String horaInicio, String horaFim) {
		this.turno = turno;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}
	
	/*
	 * Construtor com parâmetros (completo)
	 */
	public HoraAulaHTTP(Long id, TipoTurno turno, String horaInicio, String horaFim,
			StatusHoraAula status) {
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

	public StatusHoraAula getStatus() {
		return status;
	}

	public void setStatus(StatusHoraAula status) {
		this.status = status;
	}
}