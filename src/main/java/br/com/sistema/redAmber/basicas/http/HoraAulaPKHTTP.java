package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.Turma;

public class HoraAulaPKHTTP {
	
	private Turma turma;
	
	private AulaHTTP aula;

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public AulaHTTP getAula() {
		return aula;
	}

	public void setAula(AulaHTTP aula) {
		this.aula = aula;
	}
	
	
}
