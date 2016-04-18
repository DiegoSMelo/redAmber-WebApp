package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
public class MatriculaMB {
	
	private Aluno aluno;
	private Matricula matricula;
	private List<Matricula> listaMatriculas;
	private Long cursoSelecionado;
	private List<Curso> listaCursos;
	private Grade gradeSelecionada;
	private List<Grade> listaGrades;
	private Integer entrada;
	private Turma turma;
	private List<Turma> listaTurmas;
	private TipoTurno turno;
	private boolean isPagAdd;
	
	public void redirectIndex() {
		try {
			matricula = new Matricula();
			cursoSelecionado = null;
			gradeSelecionada = new Grade();
			entrada = null;
			turma = new Turma();
			turno = null;
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/matricula/index.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	public void redirectAdd() {
		try {
			this.setPagAdd(true);
			this.setMatricula(new Matricula());
			this.matricula.setAluno(this.getAluno());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/matricula/add.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	public void redirectEdit() {
		try {
			this.setPagAdd(false);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/matricula/edit.xhtml");
		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
			e.printStackTrace();
		}
	}

	/*
	 * Método que matricula um aluno em uma grade.
	 */
	public void matricular(ActionEvent event) {

		if (this.getGradeSelecionada() == null) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m15 + "');");
			//return null;
		} else if (this.getTurma() == null) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m19 + "');");
		} else {
			try {
				String codigoMatricula = gerarCodigoMatricula();
				
				Matricula matriculaJaExiste = null;
				Client c = new Client();
				WebResource wr = c.resource(URLUtil.BUSCAR_MATRICULA_POR_ALUNO_CURSO + 
						URLEncoder.encode(String.valueOf(this.getAluno().getId()), 
								java.nio.charset.StandardCharsets.UTF_8.toString()) + "/" + 
			    		URLEncoder.encode(String.valueOf(this.getGradeSelecionada().getCurso().getId()), 
			    				java.nio.charset.StandardCharsets.UTF_8.toString()));
				String jsonResult = "";
				jsonResult = wr.get(String.class);
				if (!jsonResult.equalsIgnoreCase("null")) {
					Gson gson = new Gson();
					matriculaJaExiste = gson.fromJson(jsonResult, Matricula.class);
				}
				
				if ((this.isPagAdd() && matriculaJaExiste == null)) {
					Date date = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					
					this.getMatricula().setAluno(aluno);
					this.getMatricula().setCodigoMatricula(codigoMatricula);
					this.getMatricula().setDataMatricula(cal);
					this.getMatricula().setGrade(this.getGradeSelecionada());
					this.getMatricula().setEntrada(this.getEntrada());
					this.getMatricula().setTurma(this.getTurma());
					this.getMatricula().setStatus(StatusMatricula.ATIVO);
					
					ClientConfig clientConfig = new DefaultClientConfig();
					clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
					Client client = Client.create(clientConfig);
					
					WebResource webResourcePost = client.resource(URLUtil.SALVAR_MATRICULA);
					ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
							this.getMatricula());
					if (response.getStatus() == 200) {
						redirectIndex();
					} else {
						RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m17 + "');");
					}
				} else if (matriculaJaExiste != null) {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m18 + "');");
				}
			} catch (Exception e) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m17 + "');");
				e.printStackTrace();
			}
			//return null;
		}
	}
	
	/*
	 * Método que gera um código de matrícula de 12 dígitos para o aluno baseado no seguinte critério:
	 * Código de matrícula = ANO DA MATRÍCULA + ENTRADA + CÓDIGO DO CURSO + ID DO ALUNO COM 6 DÍGITOS
	 */
	public String gerarCodigoMatricula() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    
		String paramAno = Integer.toString(cal.get(Calendar.YEAR));
		String paramAluno = String.format("%06d",this.getAluno().getId());
		
		String codigoMatricula = paramAno + this.getEntrada() + this.getCursoSelecionado() + 
				paramAluno;
		
	    return  codigoMatricula;
	}
	
	/*
	 * Método que modifica os dados de uma matrícula previamente efetuada
	 */
	public void modificarMatricula(ActionEvent event) {

		if (this.getMatricula().getGrade() == null) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m15 + "');");
		} else if (this.getMatricula().getTurma() == null) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m19 + "');");
		} else {
			try {
				String novoCodigoMatricula = modificarCodigoMatricula();
				this.getMatricula().setCodigoMatricula(novoCodigoMatricula);
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);
				
				WebResource webResourcePost = client.resource(URLUtil.SALVAR_MATRICULA);
				ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
						this.getMatricula());
				if (response.getStatus() == 200) {
					redirectIndex();
				} else {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m17 + "');");
				}
			} catch (Exception e) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m17 + "');");
				e.printStackTrace();
			}
			//return null;
		}
	}

	/*
	 * Método que atualiza o código de uma matrícula previamente efetuada
	 */
	public String modificarCodigoMatricula() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    
		String paramAno = Integer.toString(cal.get(Calendar.YEAR));
		String paramAluno = String.format("%06d",this.getAluno().getId());
		
		String codigoMatricula = paramAno + this.getMatricula().getEntrada() + 
				this.getMatricula().getGrade().getCurso().getId() + paramAluno;
		
	    return  codigoMatricula;
	}
	
	public void atualizarListaGrades(ValueChangeEvent event) {
		try {
			this.cursoSelecionado = Long.parseLong(event.getNewValue().toString());
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.LISTAR_GRADES_POR_CURSO + this.getCursoSelecionado());
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
			if (this.getCursoSelecionado() == null) {
				wr = c.resource(URLUtil.LISTAR_TURMAS);
			} else {
				wr = c.resource(URLUtil.LISTAR_TURMAS_POR_CURSO_TURNO + 
						URLEncoder.encode(getCursoSelecionado().toString(),
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
	    WebResource wr = c.resource(URLUtil.BUSCAR_MATRICULAS_POR_ALUNO + this.getAluno().getId());
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

	public Long getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Long cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
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

	public Grade getGradeSelecionada() {
		return gradeSelecionada;
	}

	public void setGradeSelecionada(Grade gradeSelecionada) {
		this.gradeSelecionada = gradeSelecionada;
	}

	public List<Grade> getListaGrades() {
		
		setListaGrades(null);
		Client c = new Client();
		WebResource wr = null;
		if (this.getCursoSelecionado() == null) {
			wr = c.resource(URLUtil.LISTAR_GRADES);
		} else {
			wr = c.resource(URLUtil.LISTAR_GRADES_POR_CURSO + this.getCursoSelecionado());
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

	public Integer getEntrada() {
		return entrada;
	}

	public void setEntrada(Integer entrada) {
		this.entrada = entrada;
	}
	
	public Turma getTurma() {
		return turma;
	}
	
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	public List<Turma> getListaTurmas() {
		
		try {
			setListaTurmas(null);
			Client c = new Client();
			WebResource wr = null;
			if (this.getCursoSelecionado() == null) {
				wr = c.resource(URLUtil.LISTAR_TURMAS);
			} else {
				wr = c.resource(URLUtil.LISTAR_TURMAS_POR_CURSO_TURNO + 
						URLEncoder.encode(getCursoSelecionado().toString(),
								java.nio.charset.StandardCharsets.UTF_8.toString()) + "/" + 
						URLEncoder.encode(getTurno().toString(),
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
	
	public TipoTurno[] getTurnos() {
		return TipoTurno.values();
	}
	
	public boolean isPagAdd() {
		return isPagAdd;
	}
	
	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}
	
	public StatusMatricula[] getStatusMatricula() {
		return StatusMatricula.values();
	}
}