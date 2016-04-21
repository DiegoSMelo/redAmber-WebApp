package br.com.sistema.redAmber.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.richfaces.event.DropEvent;
import org.richfaces.event.DropListener;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.DiasSemana;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class GradeAulaMB implements DropListener {
	
	public Turma turma;
	public List<Disciplina> listaDisciplinas;
	public List<String> colunas;
	public int numColunas;
	
	@Override
	public void processDrop(DropEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public List<Disciplina> getListaDisciplinas() {
		
		if (this.listaDisciplinas == null) {
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.LISTAR_DISCIPLINAS);
			String jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();

				Disciplina[] lista = gson.fromJson(jsonResult, Disciplina[].class);
				this.listaDisciplinas = Arrays.asList(lista);
			} 
		}
		
		return listaDisciplinas;
		
	}

	public void setListaDisciplinas(List<Disciplina> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}


	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}


	public List<String> getColunas() {
		
		
		if (this.colunas == null) {
			
			this.colunas = new ArrayList<String>();
			this.colunas.add("Horários");
			
			for (DiasSemana diaSemana : DiasSemana.values()) {
				this.colunas.add(diaSemana.toString());
			} 
		}
		
		return colunas;
	}


	public int getNumColunas() {
		return this.getColunas().size();
	}
	
	
	
}
