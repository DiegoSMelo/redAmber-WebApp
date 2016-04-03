package br.com.sistema.redAmber.basicas;

/**
 * Representa o período.
 * 
 * @author Diego Melo
 *
 */

public class Grade_Disciplina {
	
	private Grade_Disciplina_PK id;
	
	private Integer nPeriodo;
	
	public boolean isMesmoPeriodo(String paramPeriodo){
		return (paramPeriodo.equals(paramPeriodo+""));
	}

	public Grade_Disciplina_PK getId() {
		return id;
	}

	public void setId(Grade_Disciplina_PK id) {
		this.id = id;
	}

	public Integer getnPeriodo() {
		return nPeriodo;
	}

	public void setnPeriodo(Integer nPeriodo) {
		this.nPeriodo = nPeriodo;
	}
	
	
}
