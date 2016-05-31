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
	private List<List<Alunos>> resposta;
	
	/**
	 * Getters and setters
	 */
	public List<List<Alunos>> getLista() {
		return resposta;
	}
	public void setLista(List<List<Alunos>> resposta) {
		this.resposta = resposta;
	}
}