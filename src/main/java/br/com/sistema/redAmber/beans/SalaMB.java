package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.basicas.enums.StatusSala;
import br.com.sistema.redAmber.basicas.enums.TipoSala;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class SalaMB {

	private Sala sala;
	private List<Sala> listaSalas;
	private boolean isPagAdd;
	private String buscaSala;
	private boolean flagTabela;
	
	public SalaMB() {
		this.sala = new Sala();
		this.listaSalas = new ArrayList<Sala>();
		this.buscaSala = "";
		this.setFlagTabela(true);
	}

	public void atualizaLista(ActionEvent event) {
		this.getListaSalas();
		if (this.listaSalas.isEmpty()) {
			this.setFlagTabela(false);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/sala/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.setFlagTabela(true);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/sala/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void salvar() {
		
		try {
			Sala salaJaExiste = null;
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_SALA_POR_DESCRICAO + this.getSala().getDescricao());
			String jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				salaJaExiste = gson.fromJson(jsonResult, Sala.class);
			}
			if ((this.isPagAdd() && salaJaExiste == null) || !this.isPagAdd()) {
				// Create Jersey client
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);
				
				WebResource webResourcePost = client.resource(URLUtil.SALVAR_SALA);
				ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
						this.getSala());
				if (response.getStatus() == 200) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/sala/index.xhtml");
				} else {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
				}
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m17 + "');");
			}
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
			e.printStackTrace();
		}
	}
	
	/**
	 * Cria um novo objeto para a Sala e redireciona para a p�gina de cadastro.
	 */
	public void redirectAdd() {
		try {
			this.setPagAdd(true);
			this.setSala(new Sala());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/sala/add.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	/**
	 * Redireciona para a p�gina de edi��o.
	 */
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/sala/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	/**
	 * Getters and setters
	 */
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public List<Sala> getListaSalas() {
		
		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		
		String jsonResult;
		try {
			WebResource webResourcePost = client.resource(URLUtil.LISTAR_SALAS_POR_DESCRICAO);
			ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
					this.getBuscaSala());
			if (response.getStatus() != 200) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			}
			jsonResult = response.getEntity(String.class);
		    if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				
				Sala[] lista = gson.fromJson(jsonResult, Sala[].class);
				this.listaSalas = Arrays.asList(lista);
				return listaSalas;
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
		return listaSalas;
	}
	public void setListaSalas(List<Sala> listaSalas) {
		this.listaSalas = listaSalas;
	}
	public boolean isPagAdd() {
		return isPagAdd;
	}
	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}
	
	public StatusSala[] getStatusSala() {
		return StatusSala.values();
	}
	
	public TipoSala[] getTipoSala() {
		return TipoSala.values();
	}

	public String getBuscaSala() {
		return buscaSala;
	}

	public void setBuscaSala(String buscaSala) {
		this.buscaSala = buscaSala;
	}

	public boolean isFlagTabela() {
		return flagTabela;
	}

	public void setFlagTabela(boolean flagTabela) {
		this.flagTabela = flagTabela;
	}
}