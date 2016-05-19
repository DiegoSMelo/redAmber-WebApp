package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.sistema.redAmber.basicas.Aluno;
import br.com.sistema.redAmber.basicas.Curso;
import br.com.sistema.redAmber.basicas.Grade;
import br.com.sistema.redAmber.basicas.Matricula;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.StatusMatricula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class MatriculaNewMB {

	private Aluno aluno;
	private Matricula matricula;
	private List<Matricula> listaMatriculas;
	private Long idCurso; // selecionado
	private List<Curso> listaCursos;
	private List<Grade> listaGrades;
	private List<Turma> listaTurmas;
	private TipoTurno turno; // selecionado
	private boolean isPagAdd;
	private Long idComparacao;
	
	public void redirectIndex() {
		try {
			matricula = new Matricula();
			idCurso = null;
			turno = null;
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/matricula_new/index.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	public void redirectAdd() {
		try {
			this.setPagAdd(true);
			this.setMatricula(new Matricula());
			this.matricula.setAluno(this.getAluno());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/matricula_new/add.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/matricula_new/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}
	
	/*
	 * M�todo chamado internamente para salvar a matr�cula de um aluno (tanto para inserior ou editar).
	 */
	public void salvar() {
		
		this.getMatricula().setAluno(this.getAluno());
		
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		
		WebResource webResourcePost = client.resource(URLUtil.SALVAR_MATRICULA);
		ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
				this.getMatricula());
		if (response.getStatus() == 200) {
			redirectIndex();
		} else {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m23 + "');");
		}
	}
	
	/*
	 * M�todo que salva a matr�cula de um aluno.
	 */
	public void salvarMatricula(ActionEvent event) {
		if (this.getMatricula().getGrade() == null) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m12 + "');");
		} else if (this.getMatricula().getTurma() == null) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m15 + "');");
		} else {
			try {
				Matricula matriculaJaExiste = null;
				Client c = new Client();
				WebResource wr = c.resource(URLUtil.BUSCAR_MATRICULA_POR_ALUNO_CURSO + 
						URLEncoder.encode(String.valueOf(this.getAluno().getId()), 
								java.nio.charset.StandardCharsets.UTF_8.toString()) + "/" + 
						URLEncoder.encode(String.valueOf(this.getMatricula().getGrade().getCurso().getId()), 
								java.nio.charset.StandardCharsets.UTF_8.toString()));
				String jsonResult = "";
				jsonResult = wr.get(String.class);
				if (!jsonResult.equalsIgnoreCase("null")) {
					Gson gson = new Gson();
					matriculaJaExiste = gson.fromJson(jsonResult, Matricula.class);
				}
				
				if (this.isPagAdd() && matriculaJaExiste == null) {
					this.salvar();
				} else if (this.isPagAdd() && matriculaJaExiste != null) {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m14 + "');");
				}
				
				if (!this.isPagAdd()) {
					System.err.println("ID DO CURSO ESCOLHIDO: " + this.matricula.getGrade().getCurso().getId());
					System.err.println("________ID COMPARA��O: " + this.getIdComparacao());
					if (this.matricula.getGrade().getCurso().getId() == this.getIdComparacao()) {
						this.salvar();
					} else {
						List<Curso> cursos = new ArrayList<Curso>();
						for (Matricula matric : this.getListaMatriculas()) {
							cursos.add(matric.getGrade().getCurso());
							System.err.println("ID DO ALUNO: " + matric.getAluno().getId());
							System.err.println("NOME DO ALUNO: " + matric.getAluno().getNome());
							System.err.println("ID DOS CURSOS DO ALUNO: " + matric.getGrade().getCurso().getId());
							System.err.println("NOME DOS CURSOS DO ALUNO: " + matric.getGrade().getCurso().getNome());
						}
						System.err.println("ID DO CURSO DA LISTA: " + cursos.get(0).getId());
						System.err.println("LISTA CONT�M? " + cursos.contains(this.matricula.getGrade().getCurso()));
						if (!cursos.contains(this.matricula.getGrade().getCurso())) {
							this.salvar();
						} else {
							RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m14 + "');");
						}
					}				
				}
			} catch (Exception e) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m23 + "');");
				e.printStackTrace();
			}
		}
	}
	
	public void atualizarListaGrades(ValueChangeEvent event) {
		try {
			this.idCurso = Long.parseLong(event.getNewValue().toString());
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.LISTAR_GRADES_POR_CURSO + this.getIdCurso());
			String jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				
				Grade[] lista = gson.fromJson(jsonResult, Grade[].class);
				this.setListaGrades(Arrays.asList(lista));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizarListaTurmas(ValueChangeEvent event) {
		try {
			this.turno = (TipoTurno) event.getNewValue();
			Client c = new Client();
			WebResource wr = null;
			if (this.getIdCurso() == null) {
				wr = c.resource(URLUtil.LISTAR_TURMAS);
			} else {
				wr = c.resource(URLUtil.LISTAR_TURMAS_POR_CURSO_TURNO + 
						URLEncoder.encode(getIdCurso().toString(),
								java.nio.charset.StandardCharsets.UTF_8.toString()) + "/" + 
						this.getTurno().name());
			}
			String jsonResult = wr.get(String.class);
		    if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				
				Turma[] lista = gson.fromJson(jsonResult, Turma[].class);
				this.setListaTurmas(Arrays.asList(lista));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Getters and setters
	 */
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
	    WebResource wr = c.resource(URLUtil.BUSCAR_MATRICULAS_POR_ALUNO + 
	    		this.getAluno().getId());
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
	
	public Long getIdCurso() {
		return idCurso;
	}
	
	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
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

	public List<Grade> getListaGrades() {
		setListaGrades(null);
		Client c = new Client();
		WebResource wr = null;
		if (this.getIdCurso() == null) {
			wr = c.resource(URLUtil.LISTAR_GRADES);
		} else {
			wr = c.resource(URLUtil.LISTAR_GRADES_POR_CURSO + this.getIdCurso());
		}
	    String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			Grade[] lista = gson.fromJson(jsonResult, Grade[].class);
			this.listaGrades = Arrays.asList(lista);
		}
		return listaGrades;
	}

	public void setListaGrades(List<Grade> listaGrades) {
		this.listaGrades = listaGrades;
	}

	public List<Turma> getListaTurmas() {
		try {
			setListaTurmas(null);
			Client c = new Client();
			WebResource wr = null;
			if (this.getIdCurso() == null) {
				wr = c.resource(URLUtil.LISTAR_TURMAS);
			} else {
				wr = c.resource(URLUtil.LISTAR_TURMAS_POR_CURSO_TURNO + 
						URLEncoder.encode(this.getIdCurso().toString(),
								java.nio.charset.StandardCharsets.UTF_8.toString()) + "/" + 
						URLEncoder.encode(this.getTurno().toString(),
								java.nio.charset.StandardCharsets.UTF_8.toString()));
			}
			String jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				
				Turma[] lista = gson.fromJson(jsonResult, Turma[].class);
				this.listaTurmas = Arrays.asList(lista);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTurmas;
	}

	public void setListaTurmas(List<Turma> listaTurmas) {
		this.listaTurmas = listaTurmas;
	}
	
	public TipoTurno getTurno() {
		return turno;
	}
	
	public void setTurno(TipoTurno turno) {
		this.turno = turno;
	}

	public boolean isPagAdd() {
		return isPagAdd;
	}

	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}
	
	public TipoTurno[] getTurnos() {
		return TipoTurno.values();
	}
	
	public StatusMatricula[] getStatusMatricula() {
		return StatusMatricula.values();
	}

	public Long getIdComparacao() {
		return idComparacao;
	}

	public void setIdComparacao(Long idComparacao) {
		this.idComparacao = idComparacao;
	}
}