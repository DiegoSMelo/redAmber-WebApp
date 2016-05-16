package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.sistema.redAmber.basicas.BuscaFuncionario;
import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.basicas.enums.TipoFuncionario;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class FuncionarioMB {
	
	private Funcionario funcionario;
	private List<Funcionario> listaFuncionarios;
	private boolean isPagAdd;
	private BuscaFuncionario buscaFuncionario;
	private boolean flagTabela;
	
	public FuncionarioMB() {
		this.funcionario = new Funcionario();
		this.buscaFuncionario = new BuscaFuncionario();
		this.buscaFuncionario.setNome("");
		this.buscaFuncionario.setRg("");
		this.setFlagTabela(true);
	}
	
	public void atualizaLista(ActionEvent event) {
		this.getListaFuncionarios();
		if (this.listaFuncionarios.isEmpty()) {
			this.setFlagTabela(false);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/funcionario/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.setFlagTabela(true);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/funcionario/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
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
			this.getFuncionario().setDataNascimento(Calendar.getInstance());
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getListaFuncionarios() {
		
		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);

		String jsonResult;
		try {
			WebResource webResourcePost = client.resource(URLUtil.BUSCAR_FUNCIONARIO_POR_NOME_RG);
			ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
					this.getBuscaFuncionario());
			if (response.getStatus() != 200) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			}
			jsonResult = response.getEntity(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();

				Funcionario[] funcionarios = gson.fromJson(jsonResult, Funcionario[].class);
				this.listaFuncionarios = Arrays.asList(funcionarios);
				return listaFuncionarios;
			}
		} catch (UniformInterfaceException e1) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			e1.printStackTrace();
		} catch (ClientHandlerException e1) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			e1.printStackTrace();
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			e.printStackTrace();
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
	
	public StatusUsuario[] getStatusUsuario() {
		return StatusUsuario.values();
	}
	
	public BuscaFuncionario getBuscaFuncionario() {
		return buscaFuncionario;
	}

	public void setBuscaFuncionario(BuscaFuncionario buscaFuncionario) {
		this.buscaFuncionario = buscaFuncionario;
	}
	
	public boolean isFlagTabela() {
		return flagTabela;
	}

	public void setFlagTabela(boolean flagTabela) {
		this.flagTabela = flagTabela;
	}
}