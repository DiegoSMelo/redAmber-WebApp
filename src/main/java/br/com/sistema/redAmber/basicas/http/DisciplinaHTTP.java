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
	private StatusDisciplina status;
	


	public DisciplinaHTTP(Long id, String titulo, String descricao, StatusDisciplina status) {
		
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusDisciplina getStatus() {
		return status;
	}

	public void setStatus(StatusDisciplina status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
