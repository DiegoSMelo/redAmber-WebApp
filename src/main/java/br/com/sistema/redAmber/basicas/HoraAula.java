package br.com.sistema.redAmber.basicas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;

public class HoraAula {

	private HoraAulaPK id;
	

	
	private StatusHoraAula status;
	
	@Override
	public String toString() {
		
		if (this.getId() != null && this.getId().getHoraInicio() != null && this.getId().getHoraFim() != null) {
			DateFormat df = new SimpleDateFormat("HH:mm");
			String horaIniStr = df.format(this.getId().getHoraInicio());
			String horaFimStr = df.format(this.getId().getHoraFim());

			String retorno = this.getId().getDia().toString() + " (Início: " + horaIniStr + " / Fim: " + horaFimStr + ")";

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


	public StatusHoraAula getStatus() {
		return status;
	}

	public void setStatus(StatusHoraAula status) {
		this.status = status;
	}

}