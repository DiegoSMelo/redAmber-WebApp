package br.com.sistema.redAmber.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PeriodoMB {

	public int numeroPeriodos;

	
	
	public void adicionarPeriodo(){
		
		this.numeroPeriodos++;
		
		
	}
	
	public int getNumeroPeriodos() {
		return numeroPeriodos;
	}

	public void setNumeroPeriodos(int numeroPeriodos) {
		this.numeroPeriodos = numeroPeriodos;
	}
	
	
	
}
