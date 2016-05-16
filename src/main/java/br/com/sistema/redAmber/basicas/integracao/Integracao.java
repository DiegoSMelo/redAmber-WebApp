package br.com.sistema.redAmber.basicas.integracao;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Integracao implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("resposta")
	private List<Alunos> alunos;

    /*
     * Getters and setters
     */
	public List<Alunos> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Alunos> alunos) {
		this.alunos = alunos;
	}
}