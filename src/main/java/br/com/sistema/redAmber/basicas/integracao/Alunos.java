package br.com.sistema.redAmber.basicas.integracao;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Alunos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @SerializedName("aluno")
	private AlunoIntegracao aluno;

	public AlunoIntegracao getAluno() {
		return aluno;
	}

	public void setAluno(AlunoIntegracao aluno) {
		this.aluno = aluno;
	}
}