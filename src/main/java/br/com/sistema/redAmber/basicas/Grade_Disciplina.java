package br.com.sistema.redAmber.basicas;

/**
 * Representa o período.
 * 
 * @author Diego Melo
 *
 */

public class Grade_Disciplina {
	
	private Grade_Disciplina_PK id;
	
	
	
	public boolean isMesmoPeriodo(String paramPeriodo){
		return (paramPeriodo.equals(paramPeriodo+""));
	}

	public Grade_Disciplina_PK getId() {
		return id;
	}

	public void setId(Grade_Disciplina_PK id) {
		this.id = id;
	}


	
	
}
