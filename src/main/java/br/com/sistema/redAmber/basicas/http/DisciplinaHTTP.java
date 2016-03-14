package br.com.sistema.redAmber.basicas.http;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.enums.StatusDisciplina;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class DisciplinaHTTP {
	
	private Long id;
	private String titulo;
	private List<Professor> listaProfessores;
	private String descricao;
	private StatusDisciplina status;
	


	public DisciplinaHTTP(Long id, String titulo, List<Professor> listaProfessores, String descricao, StatusDisciplina status) {
		
		this.id = id;
		this.titulo = titulo;
		this.listaProfessores = listaProfessores;
		this.descricao = descricao;
		this.status = status;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Professor> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(List<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
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
