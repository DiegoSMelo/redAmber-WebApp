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

import br.com.sistema.redAmber.basicas.Equipamento;
import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.basicas.enums.StatusEquipamento;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class EquipamentoMB {
	
	private Equipamento equipamento;
	private List<Equipamento> listaEquipamentos;
	private List<Sala> listaSalas;
	private boolean isPagAdd;
	
	public void salvar() {
		
		try {
			Equipamento equipamentoJaExiste = null;
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_EQUIPAMENTO_POR_TOMBO + this.getEquipamento().getTombo());
			String jsonResult = "";
			jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				equipamentoJaExiste = gson.fromJson(jsonResult, Equipamento.class);
			}
			
			if ((this.isPagAdd() && equipamentoJaExiste == null) || !this.isPagAdd()) {
				// Create Jersey client
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);
				
				WebResource webResourcePost = client.resource(URLUtil.SALVAR_EQUIPAMENTO);
				ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
						this.getEquipamento());
				if (response.getStatus() == 200) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/equipamento/index.xhtml");
				} else {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
				}
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m16 + "');");
			}
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
			e.printStackTrace();
		}
	}
	
	public void redirectAdd() {
		try {
			this.setPagAdd(true);
			this.setEquipamento(new Equipamento());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/equipamento/add.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/equipamento/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void init() {
		equipamento = new Equipamento();
		listaSalas = new ArrayList<Sala>();
	}
	
	/*
	 * Getters and setters
	 */
	public Equipamento getEquipamento() {
		return equipamento;
	}
	
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public List<Equipamento> getListaEquipamentos() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_EQUIPAMENTOS);
	    String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			Equipamento[] lista = gson.fromJson(jsonResult, Equipamento[].class);
			this.listaEquipamentos = Arrays.asList(lista);
	    }
		return listaEquipamentos;
	}
	
	public void setListaEquipamentos(List<Equipamento> listaEquipamentos) {
		this.listaEquipamentos = listaEquipamentos;
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
	
	public void setListaSala(List<Sala> listaSalas) {
		this.listaSalas = listaSalas;
	}
	
	public boolean isPagAdd() {
		return isPagAdd;
	}
	
	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}
	
	public StatusEquipamento[] getStatusEquipamento() {
		return StatusEquipamento.values();
	}
}