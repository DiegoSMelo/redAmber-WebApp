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
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.sistema.redAmber.basicas.AvisoProfessor;
import br.com.sistema.redAmber.basicas.BuscaAvisoProfessor;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.enums.StatusAvisoProfessor;
import br.com.sistema.redAmber.basicas.enums.TipoAvisoProfessor;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class AvisoProfessorMB {
	
	private AvisoProfessor avisoProfessor;
	private List<AvisoProfessor> listaAvisoProfessor;
	private List<Professor> listaProfessores;
	private boolean isPagAdd;
	private BuscaAvisoProfessor buscaAvisoProfessor;
	private boolean flagTabela;
	
	public AvisoProfessorMB() {
		avisoProfessor = new AvisoProfessor();
		listaAvisoProfessor = new ArrayList<AvisoProfessor>();
		listaProfessores = new ArrayList<Professor>();
		buscaAvisoProfessor = new BuscaAvisoProfessor();
		this.setFlagTabela(true);
	}
	
	public void atualizaLista(ActionEvent event) {
		this.getListaAvisoProfessor();
		if (this.listaAvisoProfessor.isEmpty()) {
			this.setFlagTabela(false);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aviso_professor/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.setFlagTabela(true);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aviso_professor/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean verificarStatus(Long id) {
		int gat = 0;
		for (AvisoProfessor aviso : this.listaAvisoProfessor) {
			if (aviso.getId() == id && aviso.getStatusAvisoProfessor() == StatusAvisoProfessor.ENVIADO) {
				gat = 1;
			}
		}
		return gat == 1;
	}
	
	public void salvar() {
		
		try {
			// Create Jersey client
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			
			WebResource webResourcePost = client.resource(URLUtil.SALVAR_AVISO_PROFESSOR);
			ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
					this.getAvisoProfessor());
			if (response.getStatus() == 200) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aviso_professor/index.xhtml");
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
			}
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
			e.printStackTrace();
		}
	}
	
	/**
	 * Altera o status de um aviso do professor para RECEBIDO
	 */
	public void receber() {
		this.avisoProfessor.setStatusAvisoProfessor(StatusAvisoProfessor.RECEBIDO);
		this.salvar();
	}
	
	/**
	 * Cria um novo objeto AvisoProfessor e redireciona para a página de cadastro.
	 */
	public void redirectAdd() {
		try {
			this.setPagAdd(true);
			this.setAvisoProfessor(new AvisoProfessor());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aviso_professor/add.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	/**
	 * Redireciona para a página de edição.
	 */
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aviso_professor/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	/**
	 * Redireciona para a página de cadastro.
	 */
	public void redirectIndex() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aviso_professor/index.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	/**
	 * Redireciona para a página que permite o operador mudar o status do aviso do professor
	 * para RECEBIDO.
	 */
	public void redirectApproval() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aviso_professor/approval.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	/**
	 * Redireciona para a página onde o operador pode enviar alertas aos alunos sobre o aviso 
	 * do professor.
	 */
	public void redirectAlert() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aviso_aluno/add.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	/**
	 * Getters and setters
	 */
	public AvisoProfessor getAvisoProfessor() {
		return avisoProfessor;
	}
	public void setAvisoProfessor(AvisoProfessor avisoProfessor) {
		this.avisoProfessor = avisoProfessor;
	}
	public List<AvisoProfessor> getListaAvisoProfessor() {
		
		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);

		String jsonResult;
		try {
			WebResource webResourcePost = client.resource(URLUtil.LISTAR_AVISOS_PROFESSORES_POR_PARAMETROS);
			ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
					this.getBuscaAvisoProfessor());
			if (response.getStatus() != 200) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			}
			jsonResult = response.getEntity(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();

				AvisoProfessor[] avisos = gson.fromJson(jsonResult, AvisoProfessor[].class);
				this.listaAvisoProfessor = Arrays.asList(avisos);
				return listaAvisoProfessor;
			}
		} catch (UniformInterfaceException e1) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			e1.printStackTrace();
		} catch (ClientHandlerException e1) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			e1.printStackTrace();
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			e.printStackTrace();
		}
		return listaAvisoProfessor;
	}
	public void setListaAvisoProfessor(List<AvisoProfessor> listaAvisoProfessor) {
		this.listaAvisoProfessor = listaAvisoProfessor;
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
	public TipoAvisoProfessor[] getTiposAvisosProfessores() {
		return TipoAvisoProfessor.values();
	}
	public StatusAvisoProfessor[] getStatusAvisosProfessores() {
		return StatusAvisoProfessor.values();
	}
	public BuscaAvisoProfessor getBuscaAvisoProfessor() {
		return buscaAvisoProfessor;
	}
	public void setBuscaAvisoProfessor(BuscaAvisoProfessor buscaAvisoProfessor) {
		this.buscaAvisoProfessor = buscaAvisoProfessor;
	}
	public boolean isFlagTabela() {
		return flagTabela;
	}
	public void setFlagTabela(boolean flagTabela) {
		this.flagTabela = flagTabela;
	}
}