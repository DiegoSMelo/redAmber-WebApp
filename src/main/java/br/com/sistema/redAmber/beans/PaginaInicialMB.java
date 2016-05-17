package br.com.sistema.redAmber.beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class PaginaInicialMB {

	private int numeroAvisosProfessor;
	private int numeroReservasEquipamento;
	private int numeroReservasSala;
	
	public void init() {
		numeroAvisosProfessor = 0;
		numeroReservasEquipamento = 0;
		numeroReservasSala = 0;
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
	public int getNumeroAvisosProfessor() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.NUMERO_AVISOS_PROFESSORES_HOJE);
	    String jsonResult = wr.get(String.class);
		
		if (!jsonResult.equalsIgnoreCase("null")) {
			try {
				Gson gson = new Gson();
				
				Integer numeroAvisos = gson.fromJson(jsonResult, Integer.class);
				this.numeroAvisosProfessor = numeroAvisos;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return numeroAvisosProfessor;
	}
	public void setNumeroAvisosProfessor(int numeroAvisosProfessor) {
		this.numeroAvisosProfessor = numeroAvisosProfessor;
	}
	public int getNumeroReservasEquipamento() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.NUMERO_RESERVAS_EQUIPAMENTOS_HOJE);
	    String jsonResult = wr.get(String.class);
				
		if (!jsonResult.equalsIgnoreCase("null")) {
			try {
				Gson gson = new Gson();
				
				Integer numeroReservas = gson.fromJson(jsonResult, Integer.class);
				this.numeroReservasEquipamento = numeroReservas;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}	
		}
		return numeroReservasEquipamento;
	}
	public void setNumeroReservasEquipamento(int numeroReservasEquipamento) {
		this.numeroReservasEquipamento = numeroReservasEquipamento;
	}
	public int getNumeroReservasSala() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.NUMERO_RESERVAS_SALAS_HOJE);
	    String jsonResult = wr.get(String.class);
				
		if (!jsonResult.equalsIgnoreCase("null")) {
			try {
				Gson gson = new Gson();
				
				Integer numeroReservas = gson.fromJson(jsonResult, Integer.class);
				this.numeroReservasSala = numeroReservas;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}	
		}
		return numeroReservasSala;
	}
	public void setNumeroReservasSala(int numeroReservasSala) {
		this.numeroReservasSala = numeroReservasSala;
	}
}