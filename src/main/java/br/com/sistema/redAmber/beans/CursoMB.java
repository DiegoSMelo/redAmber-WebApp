package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.net.URLEncoder;
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
import br.com.sistema.redAmber.basicas.enums.StatusCurso;
import br.com.sistema.redAmber.basicas.enums.TipoCurso;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class CursoMB {
	private Curso curso;
	private List<Curso> listaCursos;
	private Boolean isAdd;
	private boolean flagTabela;
	private TipoCurso tipoCurso;

	public CursoMB() {
		tipoCurso = null;
		curso = new Curso();
		this.setFlagTabela(true);
	}

	public void atualizaLista(ActionEvent event) {
		this.getListaCursos();
		if (this.listaCursos.isEmpty()) {
			this.setFlagTabela(false);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/curso/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.setFlagTabela(true);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/curso/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void salvar() {
		try {
			Curso cursoJaExiste = null;

			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_CURSO_POR_NOME_E_SIGLA
					+ URLEncoder.encode(curso.getNome(), java.nio.charset.StandardCharsets.UTF_8.toString()) + "/"
					+ URLEncoder.encode(curso.getSigla(), java.nio.charset.StandardCharsets.UTF_8.toString()));

			String jsonResult = wr.get(String.class);

			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				cursoJaExiste = gson.fromJson(jsonResult, Curso.class);

			}

			if ((this.getIsAdd() && cursoJaExiste == null) || !this.getIsAdd()) {
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
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m8 + "');");
			}

		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
		}
	}

	/**
	 * Cria um novo objeto para o operador e redireciona para a p�gina de
	 * cadastro.
	 */
	public void redirectAdd() {
		try {
			this.setIsAdd(true);
			this.setCurso(new Curso());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/curso/add.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('" + e.getMessage() + "');");
		}
	}

	/**
	 * Redireciona para a p�gina de edi��o.
	 */
	public void redirectEdit() {
		try {
			this.setIsAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/curso/edit.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('" + e.getMessage() + "');");
		}
	}

	/**
	 * Getters and setters
	 */
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getListaCursos() {
		
		String paramTipoCurso = "";
		if (this.getTipoCurso() == null) {
			paramTipoCurso = "null";
		} else {
			paramTipoCurso = String.valueOf(this.getTipoCurso());
		}

		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_CURSOS_POR_TIPO + paramTipoCurso);
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

	public Boolean getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}

	public StatusCurso[] getStatusCurso() {
		return StatusCurso.values();
	}

	public TipoCurso[] getTiposCurso() {
		return TipoCurso.values();
	}

	public boolean isFlagTabela() {
		return flagTabela;
	}

	public void setFlagTabela(boolean flagTabela) {
		this.flagTabela = flagTabela;
	}

	public TipoCurso getTipoCurso() {
		return tipoCurso;
	}

	public void setTipoCurso(TipoCurso tipoCurso) {
		this.tipoCurso = tipoCurso;
	}
}