package br.com.sistema.redAmber.basicas;

import java.util.List;

public class Professor extends GeralUsuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Disciplina> listaDisciplinas;

	/*
	 * Getters and setters
	 */
	public List<Disciplina> getListDisciplinas() {
		return listaDisciplinas;
	}

	public void setListDisciplinas(List<Disciplina> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}
	
	/*
	 * Método que compara se dois objetos do tipo Professor são iguais
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
}