package br.com.sistema.redAmber.basicas;

import br.com.sistema.redAmber.basicas.enums.StatusCurso;
import br.com.sistema.redAmber.basicas.enums.TipoCurso;



public class Curso {
	
	private Long id;
	
	private String  nome;
	
	private String sigla;
	
	private Integer cargaHorariaTotal;
	
	private TipoCurso tipoCurso;
	
	private StatusCurso status;

	
	
	public TipoCurso getTipoCurso() {
		return tipoCurso;
	}

	public void setTipoCurso(TipoCurso tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

	public StatusCurso getStatus() {
		return status;
	}

	public void setStatus(StatusCurso status) {
		this.status = status;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getCargaHorariaTotal() {
		return cargaHorariaTotal;
	}

	public void setCargaHorariaTotal(Integer cargaHorariaTotal) {
		this.cargaHorariaTotal = cargaHorariaTotal;
	}

	public Boolean equals(Curso curso){
	 if(curso.getNome().equals(this.getNome()) || curso.getSigla().equals(this.getSigla())){
		 return true;
	 }
	 return false;
	}
	

}
