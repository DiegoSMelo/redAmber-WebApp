package br.com.sistema.redAmber.basicas.http;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.sistema.redAmber.basicas.enums.StatusDisciplina;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class DisciplinaHTTP {
	
	private Long id;
	private String titulo;
	private String descricao;
	private String conteudoProgramatico;
	private CursoHTTP curso;
	private StatusDisciplina status;
	
	public DisciplinaHTTP() {}
	
	public DisciplinaHTTP(Long id, String titulo, String descricao, String conteudoProgramatico, 
			CursoHTTP curso, StatusDisciplina status) {
		
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.conteudoProgramatico = conteudoProgramatico;
		this.curso = curso;
		this.status = status;
	}
	
	/*
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConteudoProgramatico() {
		return conteudoProgramatico;
	}

	public void setConteudoProgramatico(String conteudoProgramatico) {
		this.conteudoProgramatico = conteudoProgramatico;
	}

	public CursoHTTP getCurso() {
		return curso;
	}

	public void setCurso(CursoHTTP curso) {
		this.curso = curso;
	}

	public StatusDisciplina getStatus() {
		return status;
	}

	public void setStatus(StatusDisciplina status) {
		this.status = status;
	}
}