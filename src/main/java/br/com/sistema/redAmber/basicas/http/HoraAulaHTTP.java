package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.enums.DiasSemana;
import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;

public class HoraAulaHTTP {

	private HoraAulaPKHTTP id;
	
	private DiasSemana dia;
	
	private String horaInicio;
	
	private String horaFim;
	
	private StatusHoraAula status;

	
	
	public HoraAulaPKHTTP getId() {
		return id;
	}

	public void setId(HoraAulaPKHTTP id) {
		this.id = id;
	}

	public DiasSemana getDia() {
		return dia;
	}

	public void setDia(DiasSemana dia) {
		this.dia = dia;
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
