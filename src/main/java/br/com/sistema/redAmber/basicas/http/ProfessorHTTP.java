package br.com.sistema.redAmber.basicas.http;

import java.util.Calendar;
import java.util.List;

import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.Usuario;
import br.com.sistema.redAmber.basicas.enums.StatusUsuario;

public class ProfessorHTTP {
	
	private Long id;
	
	private String nome;
	
	private String rg;
	
	private String email;
	
	private String telefone;
	
	
	
	private Calendar dataNascimento;
	
	private StatusUsuario status;
	
	private Usuario usuario;
	
	private List<Disciplina> listaDisciplinas;


	public List<Disciplina> getListDisciplinas() {
		return listaDisciplinas;
	}


	public void setListDisciplinas(List<Disciplina> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public List<Disciplina> getListaDisciplinas() {
		return listaDisciplinas;
	}


	public void setListaDisciplinas(List<Disciplina> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}


	public StatusUsuario getStatus() {
		return status;
	}

	public void setStatus(StatusUsuario status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Calendar getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
}
