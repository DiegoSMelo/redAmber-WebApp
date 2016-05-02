package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.net.URLEncoder;
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
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.sistema.redAmber.basicas.DuracaoAula;
import br.com.sistema.redAmber.basicas.enums.StatusDuracaoAula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;
import br.com.sistema.redAmber.basicas.http.DuracaoAulaHTTP;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class DuracaoAulaMB {

	private DuracaoAula duracaoAula;
	private DuracaoAulaHTTP duracaoAulaHTTP;
	private List<DuracaoAula> listaHorasAula;
	private boolean isPagAdd;
	
	public void salvar(ActionEvent event) {
		
		try {
			System.out.println(duracaoAula.getHoraInicio().getTime());
			System.out.println(duracaoAula.getHoraFim().getTime());
			
			DuracaoAula duracaoAulaExistente = null;
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_DURACAO_AULA_POR_HORA + 
		    		URLEncoder.encode(String.valueOf(duracaoAula.getHoraInicio().getTime()),
		    				java.nio.charset.StandardCharsets.UTF_8.toString()) + "/" + 
		    		URLEncoder.encode(String.valueOf(duracaoAula.getHoraFim().getTime()),
		    				java.nio.charset.StandardCharsets.UTF_8.toString()));
			String jsonResult = "";
			jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				duracaoAulaExistente = gson.fromJson(jsonResult, DuracaoAula.class);
			}
			if ((this.isPagAdd() && duracaoAulaExistente == null) || !this.isPagAdd()) {
				// Create Jersey client
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);
				
				WebResource webResourcePost = client.resource(URLUtil.SALVAR_DURACAO_AULA);
				ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
						this.getDuracaoAula());
				if (response.getStatus() == 200) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/duracaoaula/index.xhtml");
				} else {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
				}
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m11 + "');");
			}
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
			e.printStackTrace();
		}
	}
	
	public void redirectAdd(ActionEvent event) {
		try {
			this.setPagAdd(true);
			this.setDuracaoAula(new DuracaoAula());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/duracaoaula/add.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void redirectEdit(ActionEvent event) {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/duracaoaula/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void init() {
		duracaoAula = new DuracaoAula();
		listaHorasAula = new ArrayList<DuracaoAula>();
	}

	/*
	 * Getters and setters
	 */
	public DuracaoAula getDuracaoAula() {
		return duracaoAula;
	}

	public void setDuracaoAula(DuracaoAula duracaoAula) {
		this.duracaoAula = duracaoAula;
	}

	public List<DuracaoAula> getListaHorasAula() {

		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_DURACOES_AULA);
		String jsonResult = wr.get(String.class);
		if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			DuracaoAula[] lista = gson.fromJson(jsonResult, DuracaoAula[].class);
			this.listaHorasAula = Arrays.asList(lista);
		}
		return listaHorasAula;
	}

	public void setListaHorasAula(List<DuracaoAula> listaHorasAula) {
		this.listaHorasAula = listaHorasAula;
	}

	public boolean isPagAdd() {
		return isPagAdd;
	}

	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}

	public DuracaoAulaHTTP getDuracaoAulaHTTP() {
		return duracaoAulaHTTP;
	}

	public void setDuracaoAulaHTTP(DuracaoAulaHTTP duracaoAulaHTTP) {
		this.duracaoAulaHTTP = duracaoAulaHTTP;
	}
	
	public TipoTurno[] getTipoTurno() {
		return TipoTurno.values();
	}

	public StatusDuracaoAula[] getStatusDuracaoAula() {
		return StatusDuracaoAula.values();
	}
}