package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.enums.DiasSemana;

public class HoraAulaPKHTTP {
	
	
	
	private AulaHTTP aula;
	
	private DiasSemana dia;
	
	private String horaInicio;
	
	private String horaFim;
	


	public AulaHTTP getAula() {
		return aula;
	}

	public void setAula(AulaHTTP aula) {
		this.aula = aula;
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
	
	
}
