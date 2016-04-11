package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
import br.com.sistema.redAmber.basicas.enums.StatusMatricula;
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
	private boolean isPagAdd;
	
	public void redirectIndex(){
		try {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/matricula/index.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	public void redirectAdd(){
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
	 * ESTE MÉTODO AINDA NÃO VERIFICA SE O ALUNO JÁ ESTÁ MATRICULADO EM DETERMINADO MESMO CURSO!
	 */
	public String matricular() {

		if (this.getGradeSelecionada() == null) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m15 + "');");
			return null;
		}
		try {
			String codigoMatricula = gerarCodigoMatricula();
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			this.getMatricula().setAluno(aluno);
			this.getMatricula().setCodigoMatricula(codigoMatricula);
			this.getMatricula().setDataMatricula(cal);
			this.getMatricula().setGrade(this.getGradeSelecionada());
			this.getMatricula().setStatus(StatusMatricula.ATIVO);
			
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			
			WebResource webResourcePost = client.resource(URLUtil.SALVAR_MATRICULA);
			ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
					this.getMatricula());
			System.out.println("CÓDIGO DE RESPOSTA: " + response.getStatus());
			if (response.getStatus() == 200) {
//					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m16 + "');");
//					FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/matricula/index.xhtml");
				redirectIndex();
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m17 + "');");
			}
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m17 + "');");
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Método que matricula um aluno em uma grade.
	 * ESTE MÉTODO VERIFICA SE O ALUNO ESTÁ SE MATRICULANDO NOVAMENTE NO MESMO CURSO, MAS ESTE
	 * RECURSO AINDA NÃO FUNCIONA!
	 */
	public String matricularQuebrado() {

		if (this.getGradeSelecionada() == null) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m15 + "');");
			return null;
		}
		try {
			String codigoMatricula = gerarCodigoMatricula();
			Matricula matriculaJaExiste = null;
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_MATRICULA_POR_CODIGO + codigoMatricula);
			String jsonResult = "";
			jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				matriculaJaExiste = gson.fromJson(jsonResult, Matricula.class);
			}
			
			if ((this.isPagAdd() && matriculaJaExiste == null) || !this.isPagAdd()) {
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				
				this.getMatricula().setAluno(aluno);
				this.getMatricula().setCodigoMatricula(codigoMatricula);
				this.getMatricula().setDataMatricula(cal);
				this.getMatricula().setGrade(this.getGradeSelecionada());
				this.getMatricula().setStatus(StatusMatricula.ATIVO);
				
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);
				
				WebResource webResourcePost = client.resource(URLUtil.SALVAR_MATRICULA);
				ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class,
						this.getMatricula());
				System.out.println("CÓDIGO DE RESPOSTA: " + response.getStatus());
				if (response.getStatus() == 200) {
//					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m16 + "');");
//					FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/matricula/index.xhtml");
					redirectIndex();
				} else {
					RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m17 + "');");
				}
			} else {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m18 + "');");
			}
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m17 + "');");
			e.printStackTrace();
		}
		return null;
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
		
		String codigoMatricula = paramAno + this.getEntrada() + this.getCursoSelecionado() + paramAluno;
		
	    return  codigoMatricula;
	}
	
	public void atualizarListaGrades(ValueChangeEvent event) {
		try {
			this.cursoSelecionado = Long.parseLong(event.getNewValue().toString());
			System.out.println("NOME DO CURSO: " + this.getCursoSelecionado());
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
	
	public boolean isPagAdd() {
		return isPagAdd;
	}
	
	public void setPagAdd(boolean isPagAdd) {
		this.isPagAdd = isPagAdd;
	}
}