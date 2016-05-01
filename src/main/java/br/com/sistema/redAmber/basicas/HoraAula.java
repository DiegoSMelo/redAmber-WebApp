package br.com.sistema.redAmber.basicas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sistema.redAmber.basicas.enums.DiasSemana;
import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;

public class HoraAula {

	private HoraAulaPK id;
	
	private DiasSemana dia;
	
	private Date horaInicio;
	
	private Date horaFim;
	
	private StatusHoraAula status;
	
	@Override
	public String toString() {
		
		if (this.horaInicio != null && this.horaFim != null) {
			DateFormat df = new SimpleDateFormat("HH:mm");
			String horaIniStr = df.format(this.getHoraInicio());
			String horaFimStr = df.format(this.getHoraFim());

			String retorno = this.dia.toString() + "(Início: " + horaIniStr + " Fim: " + horaFimStr + ")";

			return retorno;
		}

		return "";
	}
	
	/*
	 * Getters and setters
	 */
	public HoraAulaPK getId() {
		return id;
	}

	public void setId(HoraAulaPK id) {
		this.id = id;
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

	public StatusHoraAula getStatus() {
		return status;
	}

	public void setStatus(StatusHoraAula status) {
		this.status = status;
	}

	public DiasSemana getDia() {
		return dia;
	}

	public void setDia(DiasSemana dia) {
		this.dia = dia;
	}
}