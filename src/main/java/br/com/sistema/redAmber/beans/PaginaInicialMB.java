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
import com.sun.jersey.api.client.WebResource;

import br.com.sistema.redAmber.basicas.AvisoProfessor;
import br.com.sistema.redAmber.basicas.ReservaEquipamento;
import br.com.sistema.redAmber.basicas.ReservaSala;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class PaginaInicialMB {

	private int numeroAvisosProfessorHoje;
	private int numeroReservasEquipamentoHoje;
	private int numeroReservasSalasHoje;
	@SuppressWarnings("unused")
	private int numeroAvisosProfessorPendentes;
	@SuppressWarnings("unused")
	private int numeroReservasEquipamentoPendentes;
	@SuppressWarnings("unused")
	private int numeroReservasSalasPendentes;
	private List<AvisoProfessor> avisosProfessoresPendentes;
	private List<ReservaEquipamento> reservasEquipamentosPendentes;
	private List<ReservaSala> reservasSalasPendentes;
	
	public void init() {
		this.numeroAvisosProfessorHoje = 0;
		this.numeroReservasEquipamentoHoje = 0;
		this.numeroReservasSalasHoje = 0;
		this.numeroAvisosProfessorPendentes = 0;
		this.numeroReservasEquipamentoPendentes = 0;
		this.numeroReservasSalasPendentes = 0;
		this.avisosProfessoresPendentes = new ArrayList<AvisoProfessor>();
		this.reservasEquipamentosPendentes = new ArrayList<ReservaEquipamento>();
		this.reservasSalasPendentes = new ArrayList<ReservaSala>();
	}
	
	public void redirectAvisoProfessor(ActionEvent event) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().
				redirect("/redAmber-WebApp/aviso_professor/index.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void redirectReservaEquipamento(ActionEvent event) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().
				redirect("/redAmber-WebApp/reserva_equipamento/index.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void redirectReservaSala(ActionEvent event) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().
				redirect("/redAmber-WebApp/reserva_sala/index.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	/*
	 * Getters and setters
	 */
	public int getNumeroAvisosProfessorHoje() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.NUMERO_AVISOS_PROFESSORES_HOJE);
	    String jsonResult = wr.get(String.class);
		
		if (!jsonResult.equalsIgnoreCase("null")) {
			try {
				Gson gson = new Gson();
				
				Integer numeroAvisos = gson.fromJson(jsonResult, Integer.class);
				this.numeroAvisosProfessorHoje = numeroAvisos;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return numeroAvisosProfessorHoje;
	}
	public void setNumeroAvisosProfessorHoje(int numeroAvisosProfessorHoje) {
		this.numeroAvisosProfessorHoje = numeroAvisosProfessorHoje;
	}
	public int getNumeroReservasEquipamentoHoje() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.NUMERO_RESERVAS_EQUIPAMENTOS_HOJE);
	    String jsonResult = wr.get(String.class);
				
		if (!jsonResult.equalsIgnoreCase("null")) {
			try {
				Gson gson = new Gson();
				
				Integer numeroReservas = gson.fromJson(jsonResult, Integer.class);
				this.numeroReservasEquipamentoHoje = numeroReservas;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}	
		}
		return numeroReservasEquipamentoHoje;
	}
	public void setNumeroReservasEquipamentoHoje(int numeroReservasEquipamentoHoje) {
		this.numeroReservasEquipamentoHoje = numeroReservasEquipamentoHoje;
	}
	public int getNumeroReservasSalasHoje() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.NUMERO_RESERVAS_SALAS_HOJE);
	    String jsonResult = wr.get(String.class);
				
		if (!jsonResult.equalsIgnoreCase("null")) {
			try {
				Gson gson = new Gson();
				
				Integer numeroReservas = gson.fromJson(jsonResult, Integer.class);
				this.numeroReservasSalasHoje = numeroReservas;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}	
		}
		return numeroReservasSalasHoje;
	}
	public void setNumeroReservasSalasHoje(int numeroReservasSalasHoje) {
		this.numeroReservasSalasHoje = numeroReservasSalasHoje;
	}

	public int getNumeroAvisosProfessorPendentes() {
		return this.getAvisosProfessoresPendentes().size();
	}

	public void setNumeroAvisosProfessorPendentes(int numeroAvisosProfessorPendentes) {
		this.numeroAvisosProfessorPendentes = numeroAvisosProfessorPendentes;
	}

	public int getNumeroReservasEquipamentoPendentes() {
		return this.getReservasEquipamentosPendentes().size();
	}

	public void setNumeroReservasEquipamentoPendentes(int numeroReservasEquipamentoPendentes) {
		this.numeroReservasEquipamentoPendentes = numeroReservasEquipamentoPendentes;
	}

	public int getNumeroReservasSalasPendentes() {
		return this.getReservasSalasPendentes().size();
	}

	public void setNumeroReservasSalasPendentes(int numeroReservasSalasPendentes) {
		this.numeroReservasSalasPendentes = numeroReservasSalasPendentes;
	}

	public List<AvisoProfessor> getAvisosProfessoresPendentes() {
		
		Client c = new Client();
		//WebResource wr = c.resource("http://localhost:8089/redAmber-Service/avisoprofessorws/listar-pendentes");
		WebResource wr = c.resource(URLUtil.LISTAR_AVISOS_PROFESSORES_PENDENTES);
	    String jsonResult = wr.get(String.class);
				
		if (!jsonResult.equalsIgnoreCase("null")) {
			try {
				Gson gson = new Gson();
				
				AvisoProfessor[] avisosPendentes = gson.fromJson(jsonResult, AvisoProfessor[].class);
				this.avisosProfessoresPendentes = Arrays.asList(avisosPendentes);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}	
		}
		return avisosProfessoresPendentes;
	}

	public void setAvisosProfessoresPendentes(List<AvisoProfessor> avisosProfessoresPendentes) {
		this.avisosProfessoresPendentes = avisosProfessoresPendentes;
	}

	public List<ReservaEquipamento> getReservasEquipamentosPendentes() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_RESERVAS_EQUIPAMENTOS_PENDENTES);
	    String jsonResult = wr.get(String.class);
				
		if (!jsonResult.equalsIgnoreCase("null")) {
			try {
				Gson gson = new Gson();
				
				ReservaEquipamento[] reservasPendentes = gson.fromJson(jsonResult, ReservaEquipamento[].class);
				this.reservasEquipamentosPendentes = Arrays.asList(reservasPendentes);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}	
		}
		return reservasEquipamentosPendentes;
	}

	public void setReservasEquipamentosPendentes(List<ReservaEquipamento> reservasEquipamentosPendentes) {
		this.reservasEquipamentosPendentes = reservasEquipamentosPendentes;
	}

	public List<ReservaSala> getReservasSalasPendentes() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_RESERVAS_SALAS_PENDENTES);
	    String jsonResult = wr.get(String.class);
				
		if (!jsonResult.equalsIgnoreCase("null")) {
			try {
				Gson gson = new Gson();
				
				ReservaSala[] reservasPendentes = gson.fromJson(jsonResult, ReservaSala[].class);
				this.reservasSalasPendentes = Arrays.asList(reservasPendentes);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return reservasSalasPendentes;
	}

	public void setReservasSalasPendentes(List<ReservaSala> reservasSalasPendentes) {
		this.reservasSalasPendentes = reservasSalasPendentes;
	}
}