package br.com.sistema.redAmber.basicas.integracao;

import java.io.Serializable;

public class AlunoIntegracao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id_aluno;
	private String id_usuario;
	private String id_dispositivo;
	private String email_aluno;
	private String rg_aluno;
	private String nome_aluno;
	private String dataNascimento_aluno;
	private String telefone_aluno;
	private String matricula_aluno;
    private String login_aluno;
    private String status_aluno;
    private String id_serie_aluno;
    private String nome_serie_aluno;
    private String turma_serie_aluno;
    private String id_turno_aluno;
    private String descricao_turno_aluno;
    private String id_matricula_aluno;
    
    /*
     * Getters and setters
     */
	public Long getId_aluno() {
		return id_aluno;
	}
	public void setId_aluno(Long id_aluno) {
		this.id_aluno = id_aluno;
	}
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getId_dispositivo() {
		return id_dispositivo;
	}
	public void setId_dispositivo(String id_dispositivo) {
		this.id_dispositivo = id_dispositivo;
	}
	public String getEmail_aluno() {
		return email_aluno;
	}
	public void setEmail_aluno(String email_aluno) {
		this.email_aluno = email_aluno;
	}
	public String getRg_aluno() {
		return rg_aluno;
	}
	public void setRg_aluno(String rg_aluno) {
		this.rg_aluno = rg_aluno;
	}
	public String getNome_aluno() {
		return nome_aluno;
	}
	public void setNome_aluno(String nome_aluno) {
		this.nome_aluno = nome_aluno;
	}
	public String getDataNascimento_aluno() {
		return dataNascimento_aluno;
	}
	public void setDataNascimento_aluno(String dataNascimento_aluno) {
		this.dataNascimento_aluno = dataNascimento_aluno;
	}
	public String getTelefone_aluno() {
		return telefone_aluno;
	}
	public void setTelefone_aluno(String telefone_aluno) {
		this.telefone_aluno = telefone_aluno;
	}
	public String getMatricula_aluno() {
		return matricula_aluno;
	}
	public void setMatricula_aluno(String matricula_aluno) {
		this.matricula_aluno = matricula_aluno;
	}
	public String getLogin_aluno() {
		return login_aluno;
	}
	public void setLogin_aluno(String login_aluno) {
		this.login_aluno = login_aluno;
	}
	public String getStatus_aluno() {
		return status_aluno;
	}
	public void setStatus_aluno(String status_aluno) {
		this.status_aluno = status_aluno;
	}
	public String getId_serie_aluno() {
		return id_serie_aluno;
	}
	public void setId_serie_aluno(String id_serie_aluno) {
		this.id_serie_aluno = id_serie_aluno;
	}
	public String getNome_serie_aluno() {
		return nome_serie_aluno;
	}
	public void setNome_serie_aluno(String nome_serie_aluno) {
		this.nome_serie_aluno = nome_serie_aluno;
	}
	public String getTurma_serie_aluno() {
		return turma_serie_aluno;
	}
	public void setTurma_serie_aluno(String turma_serie_aluno) {
		this.turma_serie_aluno = turma_serie_aluno;
	}
	public String getId_turno_aluno() {
		return id_turno_aluno;
	}
	public void setId_turno_aluno(String id_turno_aluno) {
		this.id_turno_aluno = id_turno_aluno;
	}
	public String getDescricao_turno_aluno() {
		return descricao_turno_aluno;
	}
	public void setDescricao_turno_aluno(String descricao_turno_aluno) {
		this.descricao_turno_aluno = descricao_turno_aluno;
	}
	public String getId_matricula_aluno() {
		return id_matricula_aluno;
	}
	public void setId_matricula_aluno(String id_matricula_aluno) {
		this.id_matricula_aluno = id_matricula_aluno;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlunoIntegracao other = (AlunoIntegracao) obj;
		if (id_aluno == null) {
			if (other.id_aluno != null)
				return false;
		} else if (!id_aluno.equals(other.id_aluno))
			return false;
		return true;
	}
}