package br.com.sistema.redAmber.beans;

import java.io.IOException;
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

import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class ProfessorMB {
	
	private Professor professor;
	private List<Professor> listaProfessores;
	private boolean isPagAdd;
	
	private Disciplina disciplina;
	
	
	
	public void salvar(){
		try {
			Professor professorJaExiste = null;
			Client c = new Client();
		    WebResource wr = c.resource(URLUtil.BUSCAR_PROFESSOR_POR_RG + this.getProfessor().getRg());
		    String jsonResult = wr.get(String.class);
		    
		    if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				professorJaExiste = gson.fromJson(jsonResult, Professor.class);
			}
		    
			if ((this.isPagAdd() && professorJaExiste == null) || !this.isPagAdd()) {

				// Create Jersey client
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);

				WebResource webResourcePost = client.resource(URLUtil.SALVAR_PROFESSOR);
				ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
						this.getProfessor());

				if (response.getStatus() == 200) {
					
					if (this.isPagAdd()) {
						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("/redAmber-WebApp/professor/index.xhtml");
					}
					
				} else {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
				}
				
			}else{
				
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m5 + "');");
			}
			
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
		} 
	}
	
	
	public boolean isDisciplinaJaAdd(Long id){
		
		int gat = 0;
		
		for (Disciplina discipl : this.getProfessor().getListDisciplinas()) {
			
			if (discipl.getId() == id) {
				
				gat = 1;
				
			}
			
		}
		
		return gat == 1;
		
	}
	
	
	public void addDisciplina(){
		
		Disciplina dsp = null;
		
		Client c = new Client();
	    WebResource wr = c.resource(URLUtil.BUSCAR_DISCIPLINA_POR_ID + 
	    		FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDisciplinaParam"));
	 
	    String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			dsp = gson.fromJson(jsonResult, Disciplina.class);
		}
		
		if (!this.isDisciplinaJaAdd(dsp.getId())) {
			
			this.getProfessor().getListDisciplinas().add(dsp);
			this.salvar();
			
		}
		
	}
	
	public void removeDisciplina(){
		
		Long idRetorno = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDisciplinaParam"));
		
		List<Disciplina> listaDisciplinas = this.getProfessor().getListDisciplinas();
		
		for (int i = 0; i < listaDisciplinas.size(); i++) {
			if (idRetorno == listaDisciplinas.get(i).getId()) {
				listaDisciplinas.remove(listaDisciplinas.get(i));
			}
		}
		
		this.getProfessor().setListDisciplinas(listaDisciplinas);
		this.salvar();
		
	}
	
	
	/**
	 * Cria um novo objeto para o Professor e redireciona para a página de cadastro.
	 */
	public void redirectAdd(){
		try {
			this.setPagAdd(true);
			this.setProfessor(new Professor());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/professor/add.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	/**
	 * Redireciona para a página de edição.
	 */
	public void redirectEdit(){
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/professor/edit.xhtml");	

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	/**
	 * Cria um novo objeto para o Professor e redireciona para a página de cadastro de disciplinas para professor.
	 */
	public void redirectDisciplinas(){
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/professor/disciplinas.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	
	public Professor getProfessor() {
		if (this.professor.getListDisciplinas() == null) {
			this.professor.setListDisciplinas(new ArrayList<Disciplina>());
		}
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
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
	
	public boolean isPagAdd() {
		return isPagAdd;
	}
	
	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public StatusUsuario[] getStatusUsuario() {
		return StatusUsuario.values();
	}
}
