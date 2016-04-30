package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.sistema.redAmber.basicas.Horario;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.DiasSemana;

@ManagedBean
@SessionScoped
public class GradeAulaMB {
	
	public Turma turma;
	public List<String> colunas;
	public int numColunas;
	public List<Horario> listaHorarios;
	
	public Horario horario;

	
	public void addHorario(){
		
		this.getListaHorarios().add(getHorario());
		
		this.horario = null;
		
		RequestContext.getCurrentInstance().execute("fechaModal()");
		
	}
	
	/**
	 * Redireciona para a página de criação de grade de horários de aulas.
	 */
	public void redirectIndex(){
		try {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/grade-aula/index.xhtml");	

		} catch (IOException e) {
			
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}

	

	public List<Horario> getListaHorarios() {
		
		if (this.listaHorarios == null) {
			this.listaHorarios = new ArrayList<Horario>();
		}
		
		return listaHorarios;
	}



	public void setListaHorarios(List<Horario> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}



	public Horario getHorario() {
		
		if (this.horario == null) {
			this.horario = new Horario();
		}
		
		return horario;
	}



	public void setHorario(Horario horario) {
		this.horario = horario;
	}



	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}


	public List<String> getColunas() {
		
		
		if (this.colunas == null) {
			
			this.colunas = new ArrayList<String>();
			this.colunas.add("Horários");
			
			for (DiasSemana diaSemana : DiasSemana.values()) {
				this.colunas.add(diaSemana.toString());
			} 
		}
		
		return colunas;
	}


	public int getNumColunas() {
		return this.getColunas().size();
	}
	
}
