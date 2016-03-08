package br.com.sistema.redAmber.basicas;

import br.com.sistema.redAmber.basicas.enums.TipoFuncionario;

public class Funcionario extends GeralUsuario{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TipoFuncionario tipoFuncionario;
	

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}
	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
	
}
