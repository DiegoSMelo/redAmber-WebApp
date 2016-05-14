package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.util.ArrayList;
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
	
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/sala/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void init() {
		sala = new Sala();
		listaSalas = new ArrayList<Sala>();
	}
	
	/*
	 * Getters and setters
	 */
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public List<Sala> getListaSalas() {
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_SALAS);
		String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			Sala[] lista = gson.fromJson(jsonResult, Sala[].class);
			this.listaSalas = Arrays.asList(lista);
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
}