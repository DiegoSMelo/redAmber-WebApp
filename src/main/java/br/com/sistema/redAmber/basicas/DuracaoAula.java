package br.com.sistema.redAmber.basicas;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sistema.redAmber.basicas.enums.StatusDuracaoAula;
import br.com.sistema.redAmber.basicas.enums.TipoTurno;

public class DuracaoAula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private TipoTurno turno;
	private Date horaInicio;
	private Date horaFim;
	private StatusDuracaoAula status;
	
	/*
	 * Construtor padr�o
	 */
	public DuracaoAula(){}
	
	/*
	 * Construtor com par�metros
	 */
	public DuracaoAula(TipoTurno turno, Date horaInicio, Date horaFim,
			StatusDuracaoAula status){
		this.turno = turno;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.status = status;
	}
	
	/*
	 * Construtor com par�metros (completo)
	 */
	public DuracaoAula(Long id, TipoTurno turno, Date horaInicio, Date horaFim,
			StatusDuracaoAula status){
		this.id = id;
		this.turno = turno;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.status = status;
	}
	
	/*
	 * M�todo que compara se dois objetos do tipo DuracaoAula s�o iguais
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DuracaoAula other = (DuracaoAula) obj;
		if (horaFim == null) {
			if (other.horaFim != null)
				return false;
		} else if (!horaFim.equals(other.horaFim))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (turno != other.turno)
			return false;
		return true;
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

	public TipoTurno getTurno() {
		return turno;
	}

	public void setTurno(TipoTurno turno) {
		this.turno = turno;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public StatusDuracaoAula getStatus() {
		return status;
	}

	public void setStatus(StatusDuracaoAula status) {
		this.status = status;
	}
	
	public String getHoraInicioString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String hora = sdf.format(this.horaInicio);
		return hora;
	}
	
	public String getHoraFimString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String hora = sdf.format(this.horaFim);
		return hora;
	}
	
	public String getHorarioString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String inicio = sdf.format(this.horaInicio);
		String fim = sdf.format(this.horaFim);
		return inicio + " �s " + fim;
	}
}