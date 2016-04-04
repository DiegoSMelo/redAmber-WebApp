package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.net.URLEncoder;
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

import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;
import br.com.sistema.redAmber.basicas.http.HoraAulaHTTP;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class HoraAulaMB {

	private HoraAula horaAula;
	private HoraAulaHTTP horaAulaHTTP;
	private List<HoraAula> listaHorasAula;
	private boolean isPagAdd;
	
	public void salvar() {
		
		try {
			System.out.println(horaAula.getHoraInicio().getTime());
			System.out.println(horaAula.getHoraFim().getTime());
			
			HoraAula horaAulaExistente = null;
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_HORA_AULA_POR_HORA + 
		    		URLEncoder.encode(String.valueOf(horaAula.getHoraInicio().getTime()),
		    				java.nio.charset.StandardCharsets.UTF_8.toString()) + "/" + 
		    		URLEncoder.encode(String.valueOf(horaAula.getHoraFim().getTime()),
		    				java.nio.charset.StandardCharsets.UTF_8.toString()));
			String jsonResult = "";
			jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				horaAulaExistente = gson.fromJson(jsonResult, HoraAula.class);
			}
			if ((this.isPagAdd() && horaAulaExistente == null) || !this.isPagAdd()) {
				// Create Jersey client
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);
				
				WebResource webResourcePost = client.resource(URLUtil.SALVAR_HORA_AULA);
				ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
						this.getHoraAula());
				if (response.getStatus() == 200) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/horaaula/index.xhtml");
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
	
	public void redirectAdd() {
		try {
			this.setPagAdd(true);
			this.setHoraAula(new HoraAula());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/horaaula/add.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/horaaula/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void init() {
		horaAula = new HoraAula();
		listaHorasAula = new ArrayList<HoraAula>();
	}

	/*
	 * Getters and setters
	 */
	public HoraAula getHoraAula() {
		return horaAula;
	}

	public void setHoraAula(HoraAula horaAula) {
		this.horaAula = horaAula;
	}

	public List<HoraAula> getListaHorasAula() {

		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_HORAS_AULA);
		String jsonResult = wr.get(String.class);
		if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			HoraAula[] lista = gson.fromJson(jsonResult, HoraAula[].class);
			this.listaHorasAula = Arrays.asList(lista);
		}
		return listaHorasAula;
	}

	public void setListaHorasAula(List<HoraAula> listaHorasAula) {
		this.listaHorasAula = listaHorasAula;
	}

	public boolean isPagAdd() {
		return isPagAdd;
	}

	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}

	public HoraAulaHTTP getHoraAulaHTTP() {
		return horaAulaHTTP;
	}

	public void setHoraAulaHTTP(HoraAulaHTTP horaAulaHTTP) {
		this.horaAulaHTTP = horaAulaHTTP;
	}
	
	public TipoTurno[] getTipoTurno() {
		return TipoTurno.values();
	}

	public StatusHoraAula[] getStatusHoraAula() {
		return StatusHoraAula.values();
	}
}