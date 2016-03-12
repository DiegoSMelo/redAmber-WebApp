package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
import br.com.sistema.redAmber.basicas.enums.TipoFuncionario;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class FuncionarioMB {
	
	private Funcionario funcionario;
	private List<Funcionario> listaFuncionarios;
	private boolean isPagAdd;
	
	public void salvar() {
		try {
			
			Funcionario funcionarioJaExiste = null;
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_FUNCIONARIO_POR_RG + this.getFuncionario().getRg());
			String jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				funcionarioJaExiste = gson.fromJson(jsonResult, Funcionario.class);
			}
			
			if ((this.isPagAdd() && funcionarioJaExiste == null) || !this.isPagAdd()) {

				// Create Jersey client
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);

				WebResource webResourcePost = client.resource(URLUtil.SALVAR_FUNCIONARIO);
				ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
						this.getFuncionario());

				if (response.getStatus() == 200) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/funcionario/index.xhtml");
				} else {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
				}
				
			} else {
				
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m6 + "');");
			}
			
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
		}
	}
	
	/**
	 * Cria um novo objeto para o operador e redireciona para a página de cadastro.
	 */
	public void redirectAdd(){
		try {
			this.setPagAdd(true);
			this.setFuncionario(new Funcionario());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/funcionario/add.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	/**
	 * Redireciona para a página de edição.
	 */
	public void redirectEdit(){
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/funcionario/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	public void init(){
		funcionario = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getListaFuncionarios() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_FUNCIONARIOS);
	    String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			Funcionario[] lista = gson.fromJson(jsonResult, Funcionario[].class);
			this.listaFuncionarios = Arrays.asList(lista);
		}		
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
	
	public TipoFuncionario[] getTipos() {
		return TipoFuncionario.values();
	}
	
	public boolean isPagAdd() {
		return isPagAdd;
	}

	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}
}