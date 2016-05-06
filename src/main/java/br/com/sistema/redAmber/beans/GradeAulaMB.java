package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.sistema.redAmber.basicas.Aula;
import br.com.sistema.redAmber.basicas.AulaPK;
import br.com.sistema.redAmber.basicas.Disciplina;
import br.com.sistema.redAmber.basicas.HoraAula;
import br.com.sistema.redAmber.basicas.HoraAulaPK;
import br.com.sistema.redAmber.basicas.Horario;
import br.com.sistema.redAmber.basicas.Professor;
import br.com.sistema.redAmber.basicas.Sala;
import br.com.sistema.redAmber.basicas.Turma;
import br.com.sistema.redAmber.basicas.enums.DiasSemana;
import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;
import br.com.sistema.redAmber.basicas.http.HoraAulaHTTP;
import br.com.sistema.redAmber.util.Datas;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class GradeAulaMB {
	
	public Gson gson = new Gson();
	public boolean atualiza;
	
	public Turma turma;
	public List<String> colunas;
	public int numColunas;
	public List<Horario> listaHorarios;
	
	public Horario horario;
	
	public Aula aula;
	
	public HoraAula horaAula;
	public List<HoraAula> listaHoraAulas;
	
	public HoraAulaHTTP horaAulaHTTP;
	public List<HoraAulaHTTP> listaHoraAulaHTTP;
	
	public Sala sala;
	public List<Sala> listaSalas;
	
	public Disciplina disciplina;
	public List<Disciplina> listaDisciplinas;
	
	public Professor professor;
	public List<Professor> listaProfessoresPorDisciplina;
	
	
	public String getResumoHoraAula(){
		
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String dia = params.get("diaSemanaParam2");
	    String horario = params.get("horarioParam2");
	    
		
		String resumo = "";
		String[] horaSplit = horario.split("/");
		String horaIni = horaSplit[0];
		String horaFim = horaSplit[1];
		
		for (HoraAulaHTTP horaAulaHTTP : this.listaHoraAulaHTTP) {
			
			if (Datas.convertStringTimeToDate(horaAulaHTTP.getHoraInicio()).compareTo(Datas.criarHora(Integer.parseInt(horaIni.split(":")[0]), Integer.parseInt(horaIni.split(":")[1]), 0)) == 0 &&
					Datas.convertStringTimeToDate(horaAulaHTTP.getHoraFim()).compareTo(Datas.criarHora(Integer.parseInt(horaFim.split(":")[0]), Integer.parseInt(horaFim.split(":")[1]), 0)) == 0 &&
					horaAulaHTTP.getDia().toString().equalsIgnoreCase(dia)) {
				
				resumo = horaAulaHTTP.getId().getAula().getId().getDisciplina().getTitulo() + " (" + horaAulaHTTP.getId().getAula().getId().getSala().getDescricao() + ")\n" + horaAulaHTTP.getId().getAula().getId().getProfessor().getNome();
				
			}
		}
		
		return resumo;
	}
	


	public void salvarGradeAula(){
		
		try {
			
			this.removerHoraAulaPorTurmaHTTP(this.getTurma());
			
			for (HoraAula ha : this.listaHoraAulas) {
				
				this.salvarHoraAulaHTTP(ha);
				
			}
			
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m20 + "');");
		
		} catch (UniformInterfaceException e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
		} catch (ClientHandlerException e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
		}
		
	}
	
	public void removerHoraAulaPorTurmaHTTP(Turma turma){
		
		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		
		WebResource webResourcePost = client.resource(URLUtil.REMOVER_HORA_AULA_POR_TURMA);
		ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class, turma);
		
		if (response.getStatus() == 200) {
			
			System.out.println("HoraAula removido com sucesso");
			
		} else {
			System.out.println("Falha ao remover HoraAula");
		}
		
	}
	
	public void salvarHoraAulaHTTP(HoraAula ha){
				
		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		
		WebResource webResourcePost = client.resource(URLUtil.ADD_HORA_AULA);
		ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class, ha);
		
		if (response.getStatus() == 200) {
			
			System.out.println("HoraAula salvo com sucesso");
			
		} else {
			System.out.println("Falha ao salvar HoraAula");
		}
		
	}
	
	public void salvarHoraAula(){
		
		HoraAulaPK haPk = new HoraAulaPK();
		AulaPK aulaPK = new AulaPK();
		
		aulaPK.setSala(this.getSala());
		aulaPK.setDisciplina(this.getDisciplina());
		aulaPK.setProfessor(this.getProfessor());
		
		this.getAula().setId(aulaPK);
		
		haPk.setTurma(this.getTurma());
		haPk.setAula(this.getAula());
		
		this.getHoraAula().setId(haPk);	
		
		
		
		this.getListaHoraAulas().add(this.getHoraAula()); //se liga nessa chamada
		
		
		
		RequestContext.getCurrentInstance().execute("fechaModalAula()");
		
		this.setAula(null);
		this.setDisciplina(null);
		this.setSala(null);
		this.setProfessor(null);
		this.setHoraAula(null);
		
		System.out.println("ok");
	}
	
	public void addAula(){
		this.horaAula = new HoraAula();
		
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String diaSemanaParam = params.get("diaSemanaParam");
	    String horarioParam = params.get("horarioParam");
	    
	    String[] horarioSplit = horarioParam.split("/");
	    
	    //get horario inicio string
	    String[] horarioInicioSplit = horarioSplit[0].split(":");
	    //get horario fim string
	    String[] horarioFimSplit = horarioSplit[1].split(":");
	    //get horario inicio date
	    this.getHoraAula().setHoraInicio(Datas.criarHora(Integer.parseInt(horarioInicioSplit[0]), Integer.parseInt(horarioInicioSplit[1]), 00));
	    //get horario fim date
	    this.getHoraAula().setHoraFim(Datas.criarHora(Integer.parseInt(horarioFimSplit[0]), Integer.parseInt(horarioFimSplit[1]), 00));
	    
	    //get dia da semana
	    for (DiasSemana dia : DiasSemana.values()) {
			if (dia.toString().equals(diaSemanaParam)) {
				this.getHoraAula().setDia(dia);
			}
		}
	    
	    this.getHoraAula().setStatus(StatusHoraAula.ATIVA);
	    
	    
	    RequestContext.getCurrentInstance().execute("exibeModalAula()");
	    
	}
	
	
	public void addHorario(){
		
		this.listaHorarios.add(getHorario());
		
		this.horario = null;
		
		//Assim, a lista de horarios não será atualizada.
		this.atualiza = false;
		
		RequestContext.getCurrentInstance().execute("fechaModalHorario()");
		
	}
	
	/**
	 * Redireciona para a página de criação de grade de horários de aulas.
	 */
	public void redirectIndex(){
		try {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/grade-aula/index.xhtml");	

		} catch (IOException e) {
			
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	
	

	public List<Horario> getListaHorarios() {

		if (this.atualiza == true || this.listaHorarios == null) {
			this.listaHorarios = new ArrayList<Horario>();
			for (HoraAulaHTTP ha : this.getListaHoraAulaHTTP()) {

				if (this.listaHorarios.size() > 0) {

					for (Horario ho : this.listaHorarios) {

						Horario horario = new Horario();
						horario.setHoraInicio(Datas.convertStringTimeToDate(ha.getHoraInicio()));
						horario.setHoraFim(Datas.convertStringTimeToDate(ha.getHoraFim()));

						if (!ho.equals(horario)) {

							this.listaHorarios.add(horario);

						}

					}

				} else {

					Horario horario = new Horario();
					horario.setHoraInicio(Datas.convertStringTimeToDate(ha.getHoraInicio()));
					horario.setHoraFim(Datas.convertStringTimeToDate(ha.getHoraFim()));

					this.listaHorarios.add(horario);

				}

			} 
		}
		return listaHorarios;

	}



	public void setListaHorarios(List<Horario> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}
	
	public void exibeModalHorario(){
		RequestContext.getCurrentInstance().execute("exibeModalHorario()");
	}

	public Horario getHorario() {
		
		if (this.horario == null) {
			this.horario = new Horario();
		}
		
		return horario;
	}



	public void setHorario(Horario horario) {
		this.horario = horario;
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


	public Aula getAula() {
		
		if (this.aula == null) {
			this.aula = new Aula();
		}
		
		return aula;
	}


	public void setAula(Aula aula) {
		this.aula = aula;
	}


	public HoraAula getHoraAula() {
		
		if (this.horaAula == null) {
			this.horaAula = new HoraAula();
		}
		
		return horaAula;
	}


	public void setHoraAula(HoraAula horaAula) {
		this.horaAula = horaAula;
	}
	
	

	public HoraAulaHTTP getHoraAulaHTTP() {
		return horaAulaHTTP;
	}

	public void setHoraAulaHTTP(HoraAulaHTTP horaAulaHTTP) {
		this.horaAulaHTTP = horaAulaHTTP;
	}

	public List<HoraAulaHTTP> getListaHoraAulaHTTP() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.BUSCAR_HORAAULA_POR_ID_TURMA + this.getTurma().getId());
		String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			HoraAulaHTTP[] lista = gson.fromJson(jsonResult, HoraAulaHTTP[].class);
			this.listaHoraAulaHTTP = Arrays.asList(lista);
		}else{
			this.listaHoraAulaHTTP = new ArrayList<HoraAulaHTTP>();
		}
		
		return listaHoraAulaHTTP;
	}

	public void setListaHoraAulaHTTP(List<HoraAulaHTTP> listaHoraAulaHTTP) {
		this.listaHoraAulaHTTP = listaHoraAulaHTTP;
	}

	public List<HoraAula> getListaHoraAulas() {
		
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.BUSCAR_HORAAULA_POR_ID_TURMA + this.getTurma().getId());
		String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			HoraAula[] lista = gson.fromJson(jsonResult, HoraAula[].class); //PROBLEMA AQUI, AO TENTAR CONVERTER AS HORAS.
			this.listaHoraAulas = Arrays.asList(lista);
		}else{
			this.listaHoraAulas = new ArrayList<HoraAula>();
		}
		
		return listaHoraAulas;
	}


	public void setListaHoraAulas(List<HoraAula> listaHoraAulas) {
		this.listaHoraAulas = listaHoraAulas;
	}
	
	public Sala getSala() {
		
		if (this.sala == null) {
			this.sala = new Sala();
		}
		
		return sala;
	}
	
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public List<Sala> getListaSalas() {
		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_SALAS);
		String jsonResult = wr.get(String.class);
	    if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();
			
			Sala[] lista = gson.fromJson(jsonResult, Sala[].class);
			this.listaSalas = Arrays.asList(lista);
		}	
		return listaSalas;
	}
	
	public void setListaSalas(List<Sala> listaSalas) {
		this.listaSalas = listaSalas;
	}
	
	public List<Disciplina> getListaDisciplinas() {

		Client c = new Client();
		WebResource wr = c.resource(URLUtil.LISTAR_DISCIPLINAS);
		String jsonResult = wr.get(String.class);
		if (!jsonResult.equalsIgnoreCase("null")) {
			Gson gson = new Gson();

			Disciplina[] lista = gson.fromJson(jsonResult, Disciplina[].class);
			this.listaDisciplinas = Arrays.asList(lista);
		}

		return listaDisciplinas;
	}
	
	public void setListaDisciplinas(List<Disciplina> listaDisciplina) {
		this.listaDisciplinas = listaDisciplina;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public Professor getProfessor() {
		if (this.professor == null) {
			this.professor = new Professor();
		}
		
		if (this.professor.getListDisciplinas() == null) {
			this.professor.setListDisciplinas(new ArrayList<Disciplina>());
		}
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Professor> getListaProfessoresPorDisciplina() {
		
		if (this.disciplina != null) {
			
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.LISTAR_PROFESSORES_POR_DISCIPLINA + this.getDisciplina().getId());
			String jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();

				Professor[] lista = gson.fromJson(jsonResult, Professor[].class);
				this.listaProfessoresPorDisciplina = Arrays.asList(lista);
			} 
			
		}else{
			
			this.listaProfessoresPorDisciplina = new ArrayList<Professor>();
			
		}
		
		
		return listaProfessoresPorDisciplina;
		
	}

	public void setListaProfessoresPorDisciplina(List<Professor> listaProfessoresPorDisciplina) {
		this.listaProfessoresPorDisciplina = listaProfessoresPorDisciplina;
	}

	
	
	
}
