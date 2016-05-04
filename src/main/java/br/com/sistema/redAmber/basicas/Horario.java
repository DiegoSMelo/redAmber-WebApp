package br.com.sistema.redAmber.basicas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Horario {
	
	private Date horaInicio;
	private Date horaFim;
	
	@Override
	public boolean equals(Object obj) {
		
		Horario horario = (Horario)obj;
		
		DateFormat df = new SimpleDateFormat("HH:mm");
		if (df.format(this.getHoraInicio()).equals(df.format(horario.getHoraInicio())) && df.format(this.getHoraFim()).equals(df.format(horario.getHoraFim()))) {
			return true;
		}
		
		return false;
		
	}
	
	@Override
	public String toString() {
		
		if (this.horaInicio != null && this.horaFim != null) {
			DateFormat df = new SimpleDateFormat("HH:mm");
			String horaIniStr = df.format(this.getHoraInicio());
			String horaFimStr = df.format(this.getHoraFim());
			return horaIniStr + "/" + horaFimStr;
		}
		
		return "";
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
