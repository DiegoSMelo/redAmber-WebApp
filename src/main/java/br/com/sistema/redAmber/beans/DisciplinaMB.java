package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.net.URLEncoder;
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
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class DisciplinaMB {
	
	private Disciplina disciplina;
	private List<Disciplina> listaDisciplinas;
	private Boolean isAdd;
	
	
	public void salvar(){
		
		try {
			
				Disciplina disciplinaJaExiste = null;
			
				Client c = new Client();
				WebResource wr = c.resource(URLUtil.BUSCAR_DISCIPLINA_POR_TITULO + URLEncoder.encode(disciplina.getTitulo(), java.nio.charset.StandardCharsets.UTF_8.toString()));
			    String jsonResult = wr.get(String.class);
			    
			    if (!jsonResult.equalsIgnoreCase("null")) {
			    	
					Gson gson = new Gson();
					disciplinaJaExiste = gson.fromJson(jsonResult, Disciplina.class);
					
			    }
			    
			    
				if ((this.getIsAdd() && disciplinaJaExiste == null ) || !this.getIsAdd()) {
					
					// Create Jersey client
					ClientConfig clientConfig = new DefaultClientConfig();
					clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
					Client client = Client.create(clientConfig);
					
					WebResource webResourcePost = client.resource(URLUtil.SALVAR_DISCIPLINA);
					ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class, this.getDisciplina());
					
					if (response.getStatus() == 200) {
						
						FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/disciplina/index.xhtml");
						
					} else {
						
						RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
						
					} 
				}
				else{
					
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m7 + "');");
					
				}
		} catch (Exception e) {
			
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
			
		} 
		
	}
	
	
	
	
	/**
	 * Cria um novo objeto para a Disciplina e redireciona para a página de cadastro.
	 */
	public void redirectAdd(){
		try {
			
			this.setIsAdd(true);
			this.setDisciplina(new Disciplina());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/disciplina/add.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	/**
	 * Redireciona para a página de edição.
	 */
	public void redirectEdit(){
		try {
			
			this.setIsAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/disciplina/edit.xhtml");	

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	
	public List<Disciplina> getListaDisciplinas() {
		
		if (this.listaDisciplinas == null) {
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.LISTAR_DISCIPLINAS);
			String jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();

				Disciplina[] lista = gson.fromJson(jsonResult, Disciplina[].class);
				this.listaDisciplinas = Arrays.asList(lista);
			} 
		}
		
		return listaDisciplinas;
	}
	
	public void setListaDisciplinas(List<Disciplina> listaDisciplina) {
		this.listaDisciplinas = listaDisciplina;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Boolean getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}

}
