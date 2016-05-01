package br.com.sistema.redAmber.basicas;

import java.io.Serializable;
import java.util.Calendar;

import br.com.sistema.redAmber.basicas.enums.StatusUsuario;

public class GeralUsuario implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String rg;
	
	private String email;
	
	private String telefone;
	
	private Calendar dataNascimento;
	
	private StatusUsuario status;
	
	private Usuario usuario;
	
	public GeralUsuario() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public GeralUsuario(Long id, String nome, String rg, String email, String telefone, Calendar dataNascimento,
			StatusUsuario status, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.rg = rg;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.status = status;
		this.usuario = usuario;
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
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	
}
