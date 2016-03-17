package br.com.sistema.redAmber.basicas.http;

import br.com.sistema.redAmber.basicas.enums.StatusCurso;

public class CursoHTTP {
	
	//tipo das datas mudado para String para armazenar o TimeStamp
	
	private Long id;
	
	private String  nome;
	
	private String dataInicio;
	
	private String dataFim;
	
	private String sigla;
	
	private Integer cargaHorariaTotal;
	
	private StatusCurso status;
	
	
	

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

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
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

	public StatusCurso getStatus() {
		return status;
	}

	public void setStatus(StatusCurso status) {
		this.status = status;
	}

	
	
	
	
		
}


