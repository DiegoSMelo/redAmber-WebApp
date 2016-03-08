package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.basicas.GeralUsuario;
import br.com.sistema.redAmber.basicas.http.LoginHTTP;
import br.com.sistema.redAmber.util.Mensagens;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private LoginHTTP login;
	private GeralUsuario usuarioLogado;
	
	public void init(){
		usuarioLogado = null;
	}
	
	public void autenticar() {
		if ((this.getLogin().getLogin() != null && this.getLogin().getSenha() != null)
				&& (!this.getLogin().getLogin().equals("") && !this.getLogin().getSenha().equals(""))) {
			/*
			 */
			Client c = new Client();
		    WebResource wr = c.resource("http://localhost:8080/redAmber-Service/redamberws/funcionario-login/"
					+ this.getLogin().getLogin() + "/" + this.getLogin().getSenha() + "");
		    String jsonResult = wr.get(String.class);
			/*
			 */
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
			
				this.setUsuarioLogado(gson.fromJson(jsonResult, Funcionario.class));
				
				//falta verificar se o usuário está ativo
				
				RequestContext.getCurrentInstance().execute("loginSucess('" + Mensagens.m1 + "');");
				
				// logou
			} else {
				
				RequestContext.getCurrentInstance().execute("loginError('" + Mensagens.m2 + "');");
				// não logou
			}
			/*
			 */
		}
	}
	
	public void logout()
	{
		usuarioLogado = null;
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/login.xhtml");
		} catch (IOException e) {
		
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
		
	}
	
	public boolean isLogado(){
		return this.usuarioLogado != null;
	}

	public LoginHTTP getLogin() {
		if (this.login == null) {
			this.login = new LoginHTTP();
		}
		return login;
	}

	public void setLogin(LoginHTTP login) {
		this.login = login;
	}

	public GeralUsuario getUsuarioLogado() {
		
		return usuarioLogado;
	}

	public void setUsuarioLogado(GeralUsuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	
	
}
