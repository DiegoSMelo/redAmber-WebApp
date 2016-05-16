package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.sistema.redAmber.basicas.DuracaoAula;
import br.com.sistema.redAmber.basicas.Equipamento;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.ReservaEquipamento;
import br.com.sistema.redAmber.basicas.enums.StatusReserva;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class ReservaEquipamentoMB {

	private ReservaEquipamento reservaEquipamento;
	private List<ReservaEquipamento> listaReservas;
	private List<Equipamento> listaEquipamentos;
	private List<Professor> listaProfessores;
	private List<DuracaoAula> listaHorarios;
	private boolean isPagAdd;
	
	public ReservaEquipamentoMB() {
		reservaEquipamento = new ReservaEquipamento();
		listaReservas = new ArrayList<ReservaEquipamento>();
		listaEquipamentos = new ArrayList<Equipamento>();
		listaProfessores = new ArrayList<Professor>();
		listaHorarios = new ArrayList<DuracaoAula>();
	}
	
	public void redirectIndex() {
		try {
			this.setReservaEquipamento(new ReservaEquipamento());
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/reserva_equipamento/index.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	public void redirectAdd() {
		try {
			this.setReservaEquipamento(new ReservaEquipamento());
			this.getReservaEquipamento().setDataReserva(Calendar.getInstance());
			this.setPagAdd(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/reserva_equipamento/add.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	public void redirectApproval() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/reserva_equipamento/approval.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/reserva_equipamento/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void salvar(ActionEvent event) {
		try {
			ReservaEquipamento reservaJaExiste = null;
			Client c = new Client();
			
			WebResource wr = c.resource(URLUtil.VERIFICAR_RESERVA_POR_DATA_RESERVA_HORARIO +
					URLEncoder.encode(String.valueOf(this.getReservaEquipamento().getEquip().getId()),
		    				java.nio.charset.StandardCharsets.UTF_8.toString()) + "/" +
					URLEncoder.encode(String.valueOf(this.getReservaEquipamento().getDataReserva().getTimeInMillis()),
							java.nio.charset.StandardCharsets.UTF_8.toString()) + "/" + 
		    		URLEncoder.encode(String.valueOf(this.getReservaEquipamento().getHorarioReserva().getId()),
		    				java.nio.charset.StandardCharsets.UTF_8.toString()));
			String jsonResult = "";
			jsonResult = wr.get(String.class);
			String teste = "\"data anterior\"";
			if (jsonResult.equalsIgnoreCase(teste)) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m25 + "');");
				return;
			} else if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				reservaJaExiste = gson.fromJson(jsonResult, ReservaEquipamento.class);
			}
			
			if ((this.isPagAdd() && reservaJaExiste == null) || !this.isPagAdd()) {
				// Create Jersey client
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);
				
				WebResource webResourcePost = client.resource(URLUtil.SALVAR_RESERVA_EQUIPAMENTO);
				ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
						this.getReservaEquipamento());
				if (response.getStatus() == 200) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/reserva_equipamento/index.xhtml");
				} else {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
				}
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m24 + "');");
			}
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
			e.printStackTrace();
		}
	}
	
	public void aprovarRequisicao(ActionEvent event) {
		this.getReservaEquipamento().setStatus(StatusReserva.APROVADA);
		this.salvar(event);
	}
	
	public void negarRequisicao(ActionEvent event) {
		this.getReservaEquipamento().setStatus(StatusReserva.NEGADA);
		this.salvar(event);
	}
	
	/*
	 * Getters and setters
	 */
	public ReservaEquipamento getReservaEquipamento() {
		return reservaEquipamento;
	}
	public void setReservaEquipamento(ReservaEquipamento reservaEquipamento) {
		this.reservaEquipamento = reservaEquipamento;
	}
	public List<ReservaEquipamento> getListaReservas() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_RESERVAS_EQUIPAMENTO);
	    String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			ReservaEquipamento[] lista = gson.fromJson(jsonResult, ReservaEquipamento[].class);
			this.listaReservas = Arrays.asList(lista);
	    }
		return listaReservas;
	}
	public void setListaReservas(List<ReservaEquipamento> listaReservas) {
		this.listaReservas = listaReservas;
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
	public List<Professor> getListaProfessores() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_PROFESSORES);
	    String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			Professor[] lista = gson.fromJson(jsonResult, Professor[].class);
			this.listaProfessores = Arrays.asList(lista);
	    }
		return listaProfessores;
	}
	public void setListaProfessores(List<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}
	public List<DuracaoAula> getListaHorarios() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_DURACOES_AULA);
	    String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			DuracaoAula[] lista = gson.fromJson(jsonResult, DuracaoAula[].class);
			this.listaHorarios = Arrays.asList(lista);
	    }
		return listaHorarios;
	}
	public void setListaHorarios(List<DuracaoAula> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}
	public boolean isPagAdd() {
		return isPagAdd;
	}
	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}
	
	public StatusReserva[] getStatusReservas() {
		return StatusReserva.values();
	}
}