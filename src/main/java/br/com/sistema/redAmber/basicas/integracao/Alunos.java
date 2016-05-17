package br.com.sistema.redAmber.basicas.integracao;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Alunos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @SerializedName("alunos")
	private AlunoIntegracao alunoIntegracao;

	public AlunoIntegracao getAlunoIntegracao() {
		return alunoIntegracao;
	}

	public void setAlunoIntegracao(AlunoIntegracao alunoIntegracao) {
		this.alunoIntegracao = alunoIntegracao;
	}
}