package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.util.Criptografia;

public class LoginHTTP {
	
	private String login;
	private String senha;
	
	public LoginHTTP(){}
	
	public LoginHTTP(String login, String senha){
		setLogin(login);
		setSenha(senha);
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = Criptografia.criptografarSenhas(senha);
	}
	
	
}
