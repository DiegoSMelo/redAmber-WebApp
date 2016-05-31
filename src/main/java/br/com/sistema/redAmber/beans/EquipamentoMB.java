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
	private String buscaEquipamento;
	private boolean flagTabela;
	
	public EquipamentoMB() {
		this.equipamento = new Equipamento();
		this.listaEquipamentos = new ArrayList<Equipamento>();
		this.listaSalas = new ArrayList<Sala>();
		this.buscaEquipamento = "";
		this.setFlagTabela(true);
	}
	
	public void atualizaLista(ActionEvent event) {
		this.getListaEquipamentos();
		if (this.listaEquipamentos.isEmpty()) {
			this.setFlagTabela(false);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/equipamento/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.setFlagTabela(true);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/equipamento/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
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
	
	/**
	 * Cria um novo objeto para a Equipamento e redireciona para a página de cadastro.
	 */
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
	
	/**
	 * Redireciona para a página de edição.
	 */
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/equipamento/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	/**
	 * Getters and setters
	 */
	public Equipamento getEquipamento() {
		return equipamento;
	}
	
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public List<Equipamento> getListaEquipamentos() {
		
		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		
		String jsonResult;
		try {
			WebResource webResourcePost = client.resource(URLUtil.LISTAR_EQUIPAMENTOS_POR_DESCRICAO);
			ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
					this.getBuscaEquipamento());
			if (response.getStatus() != 200) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			}
			jsonResult = response.getEntity(String.class);
		    if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				
				Equipamento[] lista = gson.fromJson(jsonResult, Equipamento[].class);
				this.listaEquipamentos = Arrays.asList(lista);
				return listaEquipamentos;
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
	
	public void setListaSalas(List<Sala> listaSalas) {
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

	public String getBuscaEquipamento() {
		return buscaEquipamento;
	}

	public void setBuscaEquipamento(String buscaEquipamento) {
		this.buscaEquipamento = buscaEquipamento;
	}

	public boolean isFlagTabela() {
		return flagTabela;
	}

	public void setFlagTabela(boolean flagTabela) {
		this.flagTabela = flagTabela;
	}
}