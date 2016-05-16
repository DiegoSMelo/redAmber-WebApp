package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.richfaces.event.DropEvent;
import org.richfaces.event.DropListener;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.Grade;
import br.com.sistema.redAmber.basicas.Grade_Disciplina;
import br.com.sistema.redAmber.basicas.Grade_Disciplina_PK;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class PeriodoMB implements DropListener{

	public Grade grade;
	public int numeroPeriodos;
	public List<Disciplina> listaDisciplinas;
	public List<Grade_Disciplina> listaGrade_Disciplina;
	

	public void salvarPeriodo() {

		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);

		/*
		 * delete
		 */
		WebResource webResourcePostRemove = client.resource(URLUtil.REMOVER_GRADE_DISCIPLINA);
		ClientResponse responseRemove = webResourcePostRemove.type("application/json").post(ClientResponse.class, this.grade);
		
		if (responseRemove.getStatus() != 200) {
		
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m10 + "');");	
			
		} else {
			
			int gatilhoSucesso = 0;
			
			for (Grade_Disciplina gd : this.listaGrade_Disciplina) {

				/*
				 * add
				 */
				WebResource webResourcePostSalva = client.resource(URLUtil.SALVAR_GRADE_DISCIPLINA);
				ClientResponse responseSalva = webResourcePostSalva.type("application/json").post(ClientResponse.class, gd);
				
				if (responseSalva.getStatus() != 200) {
					gatilhoSucesso = 1;
				}
				
			}
			
			if (gatilhoSucesso == 1) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m10 + "');");
			}else{
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m11 + "');");
			}
			
		}
		
		
		/*
		 * Para ele atualizar a lista, na hora de chamar o get.
		 */
		this.listaGrade_Disciplina = null;
	}
	
	public void adicionarPeriodo(){
		
		this.numeroPeriodos++;
		
	}
	
	public void removerPeriodo(){
		
		this.numeroPeriodos--;		
		
	}

	@Override
	public void processDrop(DropEvent event) {
		
		try {
			Grade_Disciplina gd = new Grade_Disciplina();
			gd.setId(new Grade_Disciplina_PK());
			
			gd.getId().setDisciplina((Disciplina) event.getDragValue());
			gd.getId().setGrade(this.getGrade());
			gd.getId().setnPeriodo(Integer.parseInt(event.getDropTarget().getClientId().replace("per-", "")));
			
			this.getListaGrade_Disciplina().add(gd);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public int getNumeroPeriodos() {
		
		if (numeroPeriodos == 0) {
			int numP = 0;
			for (Grade_Disciplina gd : this.getListaGrade_Disciplina()) {
				if (gd.getId().getnPeriodo() > numP) {
					numP = gd.getId().getnPeriodo();
				}
			}
			numeroPeriodos = numP;
		}
		
		return numeroPeriodos;
		
	}

	public void setNumeroPeriodos(int numeroPeriodos) {
		this.numeroPeriodos = numeroPeriodos;
	}
	

	public List<Disciplina> getListaDisciplinas() {
		
		if (this.listaDisciplinas == null || this.listaDisciplinas.isEmpty()) {
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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	public void reset(){
		this.listaGrade_Disciplina = new ArrayList<Grade_Disciplina>();
	}
	
	public List<Grade_Disciplina> getListaGrade_Disciplina() {
		
		if (this.listaGrade_Disciplina == null) {
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.LISTAR_GRADE_DISCIPLINA_POR_GRADE + this.getGrade().getId());
			String jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				Grade_Disciplina[] lista = gson.fromJson(jsonResult, Grade_Disciplina[].class);

				this.listaGrade_Disciplina = new ArrayList<Grade_Disciplina>();
				this.listaGrade_Disciplina.addAll(Arrays.asList(lista));
			} 
		}
		return listaGrade_Disciplina;
	}

	public void setListaGrade_Disciplina(List<Grade_Disciplina> listaGrade_Disciplina) {
		this.listaGrade_Disciplina = listaGrade_Disciplina;
	}
	
	public void redirectPeriodos(){
		try {
			this.listaGrade_Disciplina = null;
			this.numeroPeriodos = 0;
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/periodo/index.xhtml");

		} catch (IOException e) {
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	
}
