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
import com.sun.jersey.api.client.WebResource;

import br.com.sistema.redAmber.basicas.Aluno;
import br.com.sistema.redAmber.basicas.Matricula;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class MatriculaMB {
	
	private Aluno aluno;
	private Matricula matricula;
	private List<Matricula> listaMatriculas;
	
	
	public void redirectIndex(){
		try {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/matricula/index.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	
	public void redirectAdd(){
		try {
			
			this.matricula.setAluno(this.getAluno());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/matricula/add.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Matricula getMatricula() {
		return matricula;
	}
	
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public List<Matricula> getListaMatriculas() {
		
		Client c = new Client();
	    WebResource wr = c.resource(URLUtil.BUSCAR_MATRICULAS_POR_ALUNO + this.getAluno().getId());
	    String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			Matricula[] lista = gson.fromJson(jsonResult, Matricula[].class);
			this.listaMatriculas = Arrays.asList(lista);
		}
		
		return listaMatriculas;
	}

	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}
	
}
