package br.com.sistema.redAmber.basicas;

import java.util.List;

public class Professor extends GeralUsuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Disciplina> listaDisciplinas;


	public List<Disciplina> getListDisciplinas() {
		return listaDisciplinas;
	}


	public void setListDisciplinas(List<Disciplina> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}
}
