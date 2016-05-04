package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.Sala;

public class AulaPKHTTP {
	
	private Sala sala;
	
	private Disciplina disciplina;
	
	private ProfessorHTTP professor;

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public ProfessorHTTP getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorHTTP professor) {
		this.professor = professor;
	}
	
	
	
}
