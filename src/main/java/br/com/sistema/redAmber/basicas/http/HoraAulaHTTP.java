package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;

public class HoraAulaHTTP {

	private HoraAulaPKHTTP id;

	
	private StatusHoraAula status;

	
	public HoraAulaPKHTTP getId() {
		return id;
	}

	public void setId(HoraAulaPKHTTP id) {
		this.id = id;
	}


	public StatusHoraAula getStatus() {
		return status;
	}

	public void setStatus(StatusHoraAula status) {
		this.status = status;
	}
	
	
}
