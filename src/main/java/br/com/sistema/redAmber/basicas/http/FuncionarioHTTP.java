package br.com.sistema.redAmber.basicas.http;

import java.util.Calendar;

import br.com.sistema.redAmber.basicas.enums.StatusUsuario;
import br.com.sistema.redAmber.basicas.enums.TipoFuncionario;

public class FuncionarioHTTP {

	private Long id;

	private String nome;

	private String rg;

	private String email;

	private String telefone;

	private Calendar dataNascimento;

	private StatusUsuario status;

	private TipoFuncionario tipoFuncionario;

	public FuncionarioHTTP() {
	}

	public FuncionarioHTTP(Long id, String nome, String rg, String email, String telefone,
			Calendar dataNascimento, StatusUsuario status, TipoFuncionario tipoFuncionario) {
		this.id = id;
		this.nome = nome;
		this.rg = rg;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.status = status;
		this.tipoFuncionario = tipoFuncionario;
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

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
}