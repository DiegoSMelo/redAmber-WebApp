package br.com.sistema.redAmber.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.richfaces.event.DropEvent;
import org.richfaces.event.DropListener;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.Grade;
import br.com.sistema.redAmber.basicas.Grade_Disciplina;
import br.com.sistema.redAmber.basicas.Grade_Disciplina_PK;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class PeriodoMB implements DropListener{

	public int numeroPeriodos;
	public List<Disciplina> listaDisciplinas;
	public List<Disciplina> listaDisciplinas_periodo1;
	public List<Grade_Disciplina> listaGrade_Disciplina;
	public Grade grade;
	
	
	
	
	public void adicionarPeriodo(){
		
		this.numeroPeriodos++;
		
	}
	
	public void removerPeriodo(){
		
		this.numeroPeriodos--;		
		
	}
	
	public void moveDisciplina(Disciplina dragValue) {
		
		this.listaDisciplinas.remove(dragValue);
		this.listaDisciplinas_periodo1.add(dragValue);
		
	}

	@Override
	public void processDrop(DropEvent event) {
		
		Grade_Disciplina gd = new Grade_Disciplina();
		gd.setId(new Grade_Disciplina_PK());
		
		gd.getId().setDisciplina((Disciplina) event.getDragValue());
		gd.getId().setGrade(this.getGrade());
		gd.setnPeriodo(Integer.parseInt(event.getDropTarget().getClientId().replace("per-", "")));
		
		this.getListaGrade_Disciplina().add(gd);
		
	}

	
	public int getNumeroPeriodos() {
		return numeroPeriodos;
	}

	public void setNumeroPeriodos(int numeroPeriodos) {
		this.numeroPeriodos = numeroPeriodos;
	}
	

	public List<Disciplina> getListaDisciplinas() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_DISCIPLINAS);
		String jsonResult = wr.get(String.class);
		if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();

			Disciplina[] lista = gson.fromJson(jsonResult, Disciplina[].class);
			this.listaDisciplinas = Arrays.asList(lista);
		}
		
		return listaDisciplinas;
		
	}

	public void setListaDisciplinas(List<Disciplina> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}

	public List<Disciplina> getListaDisciplinas_periodo1() {
		
		if (this.listaDisciplinas_periodo1 == null) {
			this.listaDisciplinas_periodo1 = new ArrayList<Disciplina>();
		}
		
		return listaDisciplinas_periodo1;
	}

	public void setListaDisciplinas_periodo1(List<Disciplina> listaDisciplinas_periodo1) {
		this.listaDisciplinas_periodo1 = listaDisciplinas_periodo1;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	public void reset(){
		this.listaGrade_Disciplina = new ArrayList<Grade_Disciplina>();
	}
	
	public List<Grade_Disciplina> getListaGrade_Disciplina() {
		
		
		if (this.listaGrade_Disciplina == null) {
			this.listaGrade_Disciplina = new ArrayList<Grade_Disciplina>();
		}
		return listaGrade_Disciplina;
	}

	public void setListaGrade_Disciplina(List<Grade_Disciplina> listaGrade_Disciplina) {
		this.listaGrade_Disciplina = listaGrade_Disciplina;
	}
	
	
	
}
