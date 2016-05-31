package br.com.sistema.redAmber.beans;

import java.io.IOException;
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

import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.StatusTurma;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class TurmaMB {

	private Turma turma;
	private List<Turma> listaTurmas;
	private List<Curso> listaCursos;
	private boolean isPagAdd;
	private Curso curso;
	private TipoTurno turno;
	private boolean flagTabela;
	
	public TurmaMB() {
		turma = new Turma();
		curso = new Curso();
		turno = null;
		this.setFlagTabela(true);
	}
	
	public void atualizaLista(ActionEvent event) {
		this.getListaTurmas();
		if (this.listaTurmas.isEmpty()) {
			this.setFlagTabela(false);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/turma/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.setFlagTabela(true);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/turma/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void salvar() {

		try {
			// Create Jersey client
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResourcePost = client.resource(URLUtil.SALVAR_TURMA);
			ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
					this.getTurma());
			if (response.getStatus() == 200) {

				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/turma/index.xhtml");

			} else {

				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");

			}

		} catch (Exception e) {

			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");

		}

	}

	/**
	 * Cria um novo objeto para a Turma e redireciona para a p�gina de cadastro.
	 */
	public void redirectAdd() {
		try {
			this.setPagAdd(true);
			this.setTurma(new Turma());
			this.getTurma().setCurso(new Curso());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/turma/add.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('" + e.getMessage() + "');");
		}
	}

	/**
	 * Redireciona para a p�gina de edi��o.
	 */
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/turma/edit.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('" + e.getMessage() + "');");
		}
	}
	
	/**
	 * Getters and setters
	 */
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getListaTurmas() {

		String paramIdCurso = "";
		String paramTurno = "";
		
		if (this.getTurno() == null) {
			paramTurno = "null";
		} else {
			paramTurno = String.valueOf(this.getTurno());
		}
		
		if (this.getCurso() == null || this.getCurso().getId() == null) {
			paramIdCurso = "null";
		} else {
			paramIdCurso = String.valueOf(this.getCurso().getId());
		}
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_TURMAS_POR_CURSO_TURNO + paramIdCurso + "/" + 
				paramTurno);
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

	public boolean isPagAdd() {
		return isPagAdd;
	}

	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}

	public TipoTurno[] getTiposTurnos() {
		return TipoTurno.values();
	}

	public StatusTurma[] getStatusTurma() {
		return StatusTurma.values();
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public TipoTurno getTurno() {
		return turno;
	}

	public void setTurno(TipoTurno turno) {
		this.turno = turno;
	}

	public boolean isFlagTabela() {
		return flagTabela;
	}

	public void setFlagTabela(boolean flagTabela) {
		this.flagTabela = flagTabela;
	}
}