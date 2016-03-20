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

import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.enums.StatusCurso;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class CursoMB {
	private Curso curso;
	private List<Curso> listaCursos;
	private Boolean isAdd;
	

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
	
	/**
	 * Redireciona para a página de edição.
	 */
	public void redirectEdit(){
		try {
			this.setIsAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/curso/edit.xhtml");	

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
	
	public List<Curso> getListaCursos() {
		
		Client c = new Client();
	    WebResource wr = c.resource(URLUtil.LISTAR_CURSOS);
	    String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			Curso[] lista = gson.fromJson(jsonResult, Curso[].class);
			this.listaCursos = Arrays.asList(lista);
		}
	    
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}
	
	public void salvar(){
		try {
				Curso cursoJaExiste = null;
			
				Client c = new Client();
			    WebResource wr = c.resource(URLUtil.BUSCAR_CURSO_POR_NOME_E_SIGLA + 
			    		URLEncoder.encode(curso.getNome(), java.nio.charset.StandardCharsets.UTF_8.toString()) + "/" + 
			    		URLEncoder.encode(curso.getSigla(), java.nio.charset.StandardCharsets.UTF_8.toString()));
			    
			    String jsonResult = wr.get(String.class);
			    
			    if (!jsonResult.equalsIgnoreCase("null")) {
					Gson gson = new Gson();
					cursoJaExiste = gson.fromJson(jsonResult, Curso.class);
					
			    }
			    
			    
				if ((this.getIsAdd() && cursoJaExiste == null ) || !this.getIsAdd()) {
					// Create Jersey client
					ClientConfig clientConfig = new DefaultClientConfig();
					clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
					Client client = Client.create(clientConfig);
					WebResource webResourcePost = client.resource(URLUtil.SALVAR_CURSO);
					ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
							this.getCurso());
					if (response.getStatus() == 200) {
						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("/redAmber-WebApp/curso/index.xhtml");
					} else {
						RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
					} 
				}
				else{
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m8 + "');");
				}
			
			
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
		} 
	}

	public Boolean getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}
	
	public StatusCurso[] getStatusCurso() {
		return StatusCurso.values();
	}
	
}
