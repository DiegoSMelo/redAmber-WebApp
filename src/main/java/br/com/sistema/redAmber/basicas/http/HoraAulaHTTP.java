package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;

public class HoraAulaHTTP {

	private HoraAulaPKHTTP id;

	private Turma turma;
	
	private StatusHoraAula status;

	
	public HoraAulaPKHTTP getId() {
		return id;
	}

	public void setId(HoraAulaPKHTTP id) {
		this.id = id;
	}
	
	

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public StatusHoraAula getStatus() {
		return status;
	}

	public void setStatus(StatusHoraAula status) {
		this.status = status;
	}
	
	
}
