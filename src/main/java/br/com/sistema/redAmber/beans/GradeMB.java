package br.com.sistema.redAmber.beans;

import java.io.IOException;
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

import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.Grade;
import br.com.sistema.redAmber.basicas.enums.StatusGrade;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class GradeMB {
	
	public Curso curso;
	public Grade grade;
	public List<Grade> listaGrades;
	
	private Boolean isAdd;
	
	
	public void salvar() {
		try {
			
			
			// Create Jersey client
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			
			if(this.getGrade().getId() == null){
				this.getGrade().setStatus(StatusGrade.PENDENTE);
			}
			
			this.getGrade().setCurso(this.getCurso());
			
			WebResource webResourcePost = client.resource(URLUtil.SALVAR_GRADE);
			//ClientResponse response = 
					webResourcePost.type("application/json").post(ClientResponse.class, this.getGrade());
			/*
			if (response.getStatus() == 200) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/grade/index.xhtml");
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
			}
			*/
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
		}
	}
	
	public void salvarGrade() {

		this.salvar();

		RequestContext.getCurrentInstance().execute("alert('Grade salva com sucesso!');");

	}
	
	
	
	public void ativar(){
		
		Long idGrade = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idGradeParam"));
		
		for (Grade grade : this.listaGrades) {
			if (grade.getId() == idGrade) {
				
				grade.setStatus(StatusGrade.ATIVO);
				
				this.setGrade(grade);
				this.salvar();
				continue;
			}
		}
		
	}
	
	public void inativar(){
		
		Long idGrade = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idGradeParam"));
		
		for (Grade grade : this.listaGrades) {
			if (grade.getId() == idGrade) {
				
				grade.setStatus(StatusGrade.INATIVO);
				
				this.setGrade(grade);
				this.salvar();
				continue;
			}
		}
		
	}
	
	public void redirectGrade(){
		try {
			this.setIsAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/grade/grade.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}

	public void redirectPeriodos(){
		try {
			this.setIsAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/periodo/index.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}

	
	public void redirectIndex(){
		try {
			this.setIsAdd(false);
			this.setGrade(new Grade());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/grade/index.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	/**
	 * Cria um novo objeto para o operador e redireciona para a página de cadastro.
	 */
	public void redirectAdd(){
		try {
			this.setIsAdd(true);
			this.setCurso(new Curso());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/curso/add.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}

	
	

	public Curso getCurso() {
		return curso;
	}



	public void setCurso(Curso curso) {
		this.curso = curso;
	}



	public Grade getGrade() {
		if (this.grade == null) {
			this.grade = new Grade();
		}
		return grade;
	}



	public void setGrade(Grade grade) {
		if (this.grade == null) {
			this.grade = new Grade();
		}
		this.grade = grade;
	}



	public List<Grade> getListaGrades() {
		
		Client c = new Client();
	    WebResource wr = c.resource(URLUtil.LISTAR_GRADES_POR_CURSO + this.getCurso().getId());
	    String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			Grade[] lista = gson.fromJson(jsonResult, Grade[].class);
			this.listaGrades = Arrays.asList(lista);
		}
		
		return listaGrades;
		
	}



	public void setListaGrades(List<Grade> listaGrades) {
		this.listaGrades = listaGrades;
	}


	public Boolean getIsAdd() {
		return isAdd;
	}


	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}
	
	public StatusGrade[] getStatusGrade(){
		return StatusGrade.values();
	}
	
	
}
