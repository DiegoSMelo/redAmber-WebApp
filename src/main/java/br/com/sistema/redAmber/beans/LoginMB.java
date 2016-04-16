package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.basicas.http.LoginHTTP;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private LoginHTTP login;
	private Funcionario usuarioLogado;
	
	
	public void autenticar() {
		if ((this.getLogin().getLogin() != null && this.getLogin().getSenha() != null)
				&& (!this.getLogin().getLogin().equals("") && !this.getLogin().getSenha().equals(""))) {
			
			// Create Jersey client
	        ClientConfig clientConfig = new DefaultClientConfig();
	        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	        Client client = Client.create(clientConfig);
	       
	        WebResource webResourcePost = client.resource(URLUtil.LOGIN_FUNCIONARIO);
	        ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class, this.getLogin());
	        
	        if (response.getStatus() == 200) {
	        	
	        	WebResource wr = client.resource(URLUtil.BUSCAR_FUNCIONARIO_POR_LOGIN + this.getLogin().getLogin());
				String jsonResult = wr.get(String.class);
				Gson gson = new Gson();
				Funcionario f = gson.fromJson(jsonResult, Funcionario.class);
				
				
				if (f.getStatus().toString().equals(StatusUsuario.ATIVO.toString())) {
					
					this.setUsuarioLogado(f);
					RequestContext.getCurrentInstance().execute("loginSucess('" + Mensagens.m1 + "');");
					
				}else{
					
					RequestContext.getCurrentInstance().execute("loginError('" + Mensagens.m9 + "');");
					
				}
				
			}else{
				
				RequestContext.getCurrentInstance().execute("loginError('" + Mensagens.m2 + "');");
				
			}
			
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

	public Funcionario getUsuarioLogado() {
		
		return usuarioLogado;
	}

	public void setUsuarioLogado(Funcionario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
