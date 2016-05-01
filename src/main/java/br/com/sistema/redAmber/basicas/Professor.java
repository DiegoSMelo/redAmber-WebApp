package br.com.sistema.redAmber.basicas;

import java.util.Calendar;
import java.util.List;

import br.com.sistema.redAmber.basicas.enums.StatusUsuario;

public class Professor extends GeralUsuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Professor() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Professor(Long id, String nome, String rg, String email, String telefone, Calendar dataNascimento,
			StatusUsuario status, Usuario usuario, List<Disciplina> listaDisciplinas) {
		super(id, nome, rg, email, telefone, dataNascimento, status, usuario);
		this.listaDisciplinas = listaDisciplinas;
	}


	/*
	 * Método equals sobrescrito. Ele serve para definir os critérios que definem se um objeto é ou
	 * não igual a outro.
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		if (obj == null || this == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		try {
			
			Professor other = (Professor) obj;
			if (other.getId() != null && other.getId().intValue() == this.getId().intValue()) {
				return true;
			}
			if (other.getRg() != null && other.getRg().equalsIgnoreCase(this.getRg())) {
				return true;
			}
			
		} catch (NullPointerException e) {
			return false;
		}
		
		return false;
		
	}

	private List<Disciplina> listaDisciplinas;


	public List<Disciplina> getListDisciplinas() {
		return listaDisciplinas;
	}


	public void setListDisciplinas(List<Disciplina> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}
}
