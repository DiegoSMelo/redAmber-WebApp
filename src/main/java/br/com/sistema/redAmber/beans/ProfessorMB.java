package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

import br.com.sistema.redAmber.basicas.BuscaProfessor;
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
	private BuscaProfessor buscaProfessor;
	private boolean flagTabela;
	private boolean flagDisciplina;

	public ProfessorMB() {
		this.professor = new Professor();
		this.disciplina = new Disciplina();
		this.buscaProfessor = new BuscaProfessor();
		this.buscaProfessor.setNome("");
		this.buscaProfessor.setRg("");
		this.setFlagTabela(true);
		this.setFlagDisciplina(false);
	}
	
	public void atualizaLista(ActionEvent event) {
		this.getListaProfessores();
		if (this.listaProfessores.isEmpty()) {
			this.setFlagTabela(false);
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/redAmber-WebApp/professor/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.setFlagTabela(true);
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/redAmber-WebApp/professor/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void salvar() {
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
						if (!this.flagDisciplina) {
							FacesContext.getCurrentInstance().getExternalContext()
								.redirect("/redAmber-WebApp/professor/index.xhtml");
						}
				} else {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
				}
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m5 + "');");
			}

		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
		}
	}

	public boolean isDisciplinaJaAdd(Long id) {

		int gat = 0;
		for (Disciplina discipl : this.getProfessor().getListDisciplinas()) {
			if (discipl.getId() == id) {
				gat = 1;
			}
		}
		return gat == 1;
	}

	public void addDisciplina() {
		
		this.setFlagDisciplina(true);

		Disciplina dsp = null;

		Client c = new Client();
		WebResource wr = c.resource(URLUtil.BUSCAR_DISCIPLINA_POR_ID + FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("idDisciplinaParam"));

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

	public void removeDisciplina() {
		
		this.setFlagDisciplina(true);

		Long idRetorno = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("idDisciplinaParam"));

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
	 * Cria um novo objeto para o Professor e redireciona para a p�gina de
	 * cadastro.
	 */
	public void redirectAdd() {
		try {
			this.setFlagDisciplina(false);
			this.setPagAdd(true);
			this.setProfessor(new Professor());
			this.getProfessor().setDataNascimento(Calendar.getInstance());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/professor/add.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('" + e.getMessage() + "');");
		}
	}

	/**
	 * Redireciona para a p�gina de edi��o.
	 */
	public void redirectEdit() {
		try {
			this.setFlagDisciplina(false);
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/professor/edit.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('" + e.getMessage() + "');");
		}
	}

	/**
	 * Cria um novo objeto para o Professor e redireciona para a p�gina de
	 * cadastro de disciplinas para professor.
	 */
	public void redirectDisciplinas() {
		try {
			this.setFlagDisciplina(true);
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/redAmber-WebApp/professor/disciplinas.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('" + e.getMessage() + "');");
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
		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);

		String jsonResult;
		try {
			WebResource webResourcePost = client.resource(URLUtil.BUSCAR_PROFESSOR_POR_NOME_RG);
			ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
					this.getBuscaProfessor());
			if (response.getStatus() != 200) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			}
			jsonResult = response.getEntity(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();

				Professor[] professores = gson.fromJson(jsonResult, Professor[].class);
				this.listaProfessores = Arrays.asList(professores);
				return listaProfessores;
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

	public BuscaProfessor getBuscaProfessor() {
		return buscaProfessor;
	}

	public void setBuscaProfessor(BuscaProfessor buscaProfessor) {
		this.buscaProfessor = buscaProfessor;
	}

	public boolean isFlagTabela() {
		return flagTabela;
	}

	public void setFlagTabela(boolean flagTabela) {
		this.flagTabela = flagTabela;
	}

	public boolean isFlagDisciplina() {
		return flagDisciplina;
	}

	public void setFlagDisciplina(boolean flagDisciplina) {
		this.flagDisciplina = flagDisciplina;
	}
}