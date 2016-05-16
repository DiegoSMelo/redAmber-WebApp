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
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.sistema.redAmber.basicas.AvisoAluno;
import br.com.sistema.redAmber.basicas.Funcionario;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class AvisoAlunoMB {

	private AvisoAluno avisoAluno;
	private List<AvisoAluno> listaAvisoAluno;
	private List<Turma> listaTurmas;
	private Funcionario funcionario;
	private boolean isPagAdd;
	
	public void salvar(ActionEvent event) {
		
		try {
			// Create Jersey client
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			
			WebResource webResourcePost = client.resource(URLUtil.SALVAR_AVISO_ALUNO);
			ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
					this.getAvisoAluno());
			if (response.getStatus() == 200) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/home/index.xhtml");
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
			}
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
			e.printStackTrace();
		}
	}
	
	public void redirectAdd() {
		try {
			this.setPagAdd(true);
			this.setAvisoAluno(new AvisoAluno());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aviso_aluno/add.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aviso_aluno/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void redirectIndex() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aviso_aluno/index.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	public void init() {
		avisoAluno = new AvisoAluno();
		listaAvisoAluno = new ArrayList<AvisoAluno>();
		listaTurmas = new ArrayList<Turma>();
		funcionario = new Funcionario();
	}
	
	/*
	 * Getters and setters
	 */
	public AvisoAluno getAvisoAluno() {
		return avisoAluno;
	}
	public void setAvisoAluno(AvisoAluno avisoAluno) {
		this.avisoAluno = avisoAluno;
	}
	public List<AvisoAluno> getListaAvisoAluno() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_AVISOS_ALUNOS);
		String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			AvisoAluno[] lista = gson.fromJson(jsonResult, AvisoAluno[].class);
			this.listaAvisoAluno = Arrays.asList(lista);
		}
		return listaAvisoAluno;
	}
	public void setListaAvisoAluno(List<AvisoAluno> listaAvisoAluno) {
		this.listaAvisoAluno = listaAvisoAluno;
	}
	public List<Turma> getListaTurmas() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_TURMAS);
		String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			Turma[] lista = gson.fromJson(jsonResult, Turma[].class);
			this.listaTurmas = Arrays.asList(lista);
		}
		return listaTurmas;
	}
	public void setListaTurmas(List<Turma> listaTurmas) {
		this.listaTurmas = listaTurmas;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public boolean isPagAdd() {
		return isPagAdd;
	}
	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}
}