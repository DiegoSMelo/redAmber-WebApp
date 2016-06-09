package br.com.sistema.redAmber.basicas;

import java.io.Serializable;
import java.util.Date;

import br.com.sistema.redAmber.basicas.enums.DiasSemana;

public class HoraAulaPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	private Aula aula;
	
	private DiasSemana dia;
	
	private Date horaInicio;
	
	private Date horaFim;
	


	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public DiasSemana getDia() {
		return dia;
	}

	public void setDia(DiasSemana dia) {
		this.dia = dia;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}
	
	
	
}
