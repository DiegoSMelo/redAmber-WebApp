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

import br.com.sistema.redAmber.basicas.Aluno;
import br.com.sistema.redAmber.basicas.BuscaAluno;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class AlunoMB {

	private Aluno aluno;
	private List<Aluno> listaAlunos = new ArrayList<Aluno>();
	private boolean isPagAdd;
	private BuscaAluno buscaAluno;
	private boolean flagTabela;

	public AlunoMB() {
		this.aluno = new Aluno();
		this.buscaAluno = new BuscaAluno();
		this.buscaAluno.setNome("");
		this.buscaAluno.setRg("");
		this.setFlagTabela(true);
	}
	
	public void atualizaLista(ActionEvent event) {
		this.getListaAlunos();
		if (this.listaAlunos.isEmpty()) {
			this.setFlagTabela(false);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aluno/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.setFlagTabela(true);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aluno/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void salvar() {
		try {
			Aluno alunoJaExiste = null;
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_ALUNO_POR_RG + this.getAluno().getRg());
			String jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				alunoJaExiste = gson.fromJson(jsonResult, Aluno.class);
			}

			if ((this.isPagAdd() && alunoJaExiste == null) || !this.isPagAdd()) {

				// Create Jersey client
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);

				WebResource webResourcePost = client.resource(URLUtil.SALVAR_ALUNO);
				ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
						this.getAluno());

				if (response.getStatus() == 200) {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("/redAmber-WebApp/aluno/index.xhtml");
				} else {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
				}
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m4 + "');");
			}
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
		}
	}

	/**
	 * Cria um novo objeto para o Aluno e redireciona para a página de cadastro.
	 */
	public void redirectAdd() {
		try {
			this.setPagAdd(true);
			this.setAluno(new Aluno());
			this.getAluno().setDataNascimento(Calendar.getInstance());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aluno/add.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('" + e.getMessage() + "');");
		}
	}

	/**
	 * Redireciona para a página de edição.
	 */
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/aluno/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('" + e.getMessage() + "');");
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getListaAlunos() {

		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);

		String jsonResult;
		try {
			WebResource webResourcePost = client.resource(URLUtil.BUSCAR_ALUNO_POR_NOME_RG);
			ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
					this.getBuscaAluno());
			if (response.getStatus() != 200) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m28 + "');");
			}
			jsonResult = response.getEntity(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();

				Aluno[] alunos = gson.fromJson(jsonResult, Aluno[].class);
				this.listaAlunos = Arrays.asList(alunos);
				return listaAlunos;
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
		return listaAlunos;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public boolean isPagAdd() {
		return isPagAdd;
	}

	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}

	public StatusUsuario[] getStatusUsuario() {
		return StatusUsuario.values();
	}

	public BuscaAluno getBuscaAluno() {
		return buscaAluno;
	}

	public void setBuscaAluno(BuscaAluno buscaAluno) {
		this.buscaAluno = buscaAluno;
	}

	public boolean isFlagTabela() {
		return flagTabela;
	}

	public void setFlagTabela(boolean flagTabela) {
		this.flagTabela = flagTabela;
	}
}