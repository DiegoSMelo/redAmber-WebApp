package br.com.sistema.redAmber.beans;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import br.com.sistema.redAmber.basicas.http.AulaHTTP;
import br.com.sistema.redAmber.basicas.http.AulaPKHTTP;
import br.com.sistema.redAmber.basicas.http.HoraAulaHTTP;
import br.com.sistema.redAmber.basicas.http.HoraAulaPKHTTP;
import br.com.sistema.redAmber.basicas.http.ProfessorHTTP;
import br.com.sistema.redAmber.util.Datas;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class GradeAulaMB {
	
	public Gson gson = new Gson();
	public boolean atualiza = true;
	public boolean atualizaListaHorariosHTTP = true;
	
	public Turma turma;
	public List<String> colunas;
	public int numColunas;
	public List<Horario> listaHorarios;
	
	public Horario horario;
	public Horario horario2;
	
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
	
	public void carregaResumos(){
		
		if (this.listaHoraAulaHTTP == null) {
			this.atualizaListaHorariosHTTP = true;
			this.getListaHoraAulaHTTP();
		}
		
		for (HoraAulaHTTP horaAulaHTTP : this.listaHoraAulaHTTP) {
			
			String resumo = "";
			String horaInicio = Datas.convertDateToStringTime(Datas.convertStringTimeToDate(horaAulaHTTP.getId().getHoraInicio()));
			String horaFim = Datas.convertDateToStringTime(Datas.convertStringTimeToDate(horaAulaHTTP.getId().getHoraFim()));
			String dia = horaAulaHTTP.getId().getDia().toString();
			
			String styleClass = horaInicio + "/" + horaFim + "**" + dia;
			styleClass = styleClass.replace(":", "\\\\:");
			styleClass = styleClass.replace("/", "\\\\/");
			styleClass = styleClass.replace("*", "\\\\*");
			
			resumo = horaAulaHTTP.getId().getAula().getId().getDisciplina().getTitulo() + " (" + horaAulaHTTP.getId().getAula().getId().getSala().getDescricao() + ")" + horaAulaHTTP.getId().getAula().getId().getProfessor().getNome();
			
			RequestContext.getCurrentInstance().execute("exibeResumoGradeAula('" + styleClass + "', '"+ resumo +"');");
			
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
	

	public void salvarGradeAula(){
		
		try {
			
			this.removerHoraAulaPorTurmaHTTP(this.getTurma());
			
			for (HoraAula ha : this.listaHoraAulas) {
				
				this.salvarHoraAulaHTTP(ha);
				
			}
			
			this.listaHoraAulaHTTP = null;//para atualizar novamente
			this.atualizaListaHorariosHTTP = true;//para atualizar novamente
			this.carregaResumos();
			
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m20 + "');");
		
		} catch (UniformInterfaceException e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
		} catch (ClientHandlerException e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m3 + "');");
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
		
		AulaPK aulaPK = new AulaPK();
		
		aulaPK.setSala(this.getSala());
		aulaPK.setDisciplina(this.getDisciplina());
		aulaPK.setProfessor(this.getProfessor());
		
		this.getAula().setId(aulaPK);
		
		this.getHoraAula().getId().setTurma(this.getTurma());
		this.getHoraAula().getId().setAula(this.getAula());
		
		
		
		//convert horaaulahttp
		
		HoraAulaHTTP hah = new HoraAulaHTTP();
		HoraAulaPKHTTP hahPK = new HoraAulaPKHTTP();
		
		AulaHTTP aulaHTTP = new AulaHTTP();
		AulaPKHTTP aulaPKhttp = new AulaPKHTTP();
		
		ProfessorHTTP pHTTP = new ProfessorHTTP();
		
		hah.setStatus(this.getHoraAula().getStatus());
		
		aulaPKhttp.setDisciplina(this.getHoraAula().getId().getAula().getId().getDisciplina());
		
		pHTTP.setDataNascimento(this.getHoraAula().getId().getAula().getId().getProfessor().getDataNascimento());
		pHTTP.setEmail(this.getHoraAula().getId().getAula().getId().getProfessor().getEmail());
		pHTTP.setId(this.getHoraAula().getId().getAula().getId().getProfessor().getId());
		pHTTP.setListaDisciplinas(this.getHoraAula().getId().getAula().getId().getProfessor().getListDisciplinas());
		pHTTP.setNome(this.getHoraAula().getId().getAula().getId().getProfessor().getNome());
		pHTTP.setRg(this.getHoraAula().getId().getAula().getId().getProfessor().getRg());
		pHTTP.setStatus(this.getHoraAula().getId().getAula().getId().getProfessor().getStatus());
		pHTTP.setTelefone(this.getHoraAula().getId().getAula().getId().getProfessor().getTelefone());
		pHTTP.setUsuario(this.getHoraAula().getId().getAula().getId().getProfessor().getUsuario());
		
		aulaPKhttp.setProfessor(pHTTP);
		aulaPKhttp.setDisciplina(this.getHoraAula().getId().getAula().getId().getDisciplina());
		aulaPKhttp.setSala(this.getHoraAula().getId().getAula().getId().getSala());
		
		aulaHTTP.setId(aulaPKhttp);
		
		hahPK.setAula(aulaHTTP);
		hahPK.setTurma(this.getHoraAula().getId().getTurma());
		hahPK.setDia(this.getHoraAula().getId().getDia());
		
		
		DateFormat df = new SimpleDateFormat("hh:mm:ss a");
		
		hahPK.setHoraInicio(df.format(this.getHoraAula().getId().getHoraInicio()));
		hahPK.setHoraFim(df.format(this.getHoraAula().getId().getHoraFim()));
		
		hah.setId(hahPK);
		
		if (this.listaHoraAulaHTTP == null) {
			this.getListaHoraAulaHTTP();
		}
		this.listaHoraAulaHTTP.add(hah);
		this.atualizaListaHorariosHTTP = false;
		//convert horaaulahttp
		
		if (this.listaHoraAulas == null) {
			this.getListaHoraAulas();
		}
		this.listaHoraAulas.add(this.getHoraAula()); //se liga nessa chamada
		
		RequestContext.getCurrentInstance().execute("fechaModalAula()");
		
		this.carregaResumos();
		
		this.setAula(null);
		this.setDisciplina(null);
		this.setSala(null);
		this.setProfessor(null);
		this.setHoraAula(null);
		
	}
	
	public void addAula(){
		this.horaAula = new HoraAula();
		this.horaAula.setId(new HoraAulaPK());
		
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String diaSemanaParam = params.get("diaSemanaParam");
	    String horarioParam = params.get("horarioParam");
	    
	    String[] horarioSplit = horarioParam.split("/");
	    
	    //get horario inicio string
	    String[] horarioInicioSplit = horarioSplit[0].split(":");
	    //get horario fim string
	    String[] horarioFimSplit = horarioSplit[1].split(":");
	    //get horario inicio date
	    this.getHoraAula().getId().setHoraInicio(Datas.criarHora(Integer.parseInt(horarioInicioSplit[0]), Integer.parseInt(horarioInicioSplit[1]), 00));
	    //get horario fim date
	    this.getHoraAula().getId().setHoraFim(Datas.criarHora(Integer.parseInt(horarioFimSplit[0]), Integer.parseInt(horarioFimSplit[1]), 00));
	    
	    //get dia da semana
	    for (DiasSemana dia : DiasSemana.values()) {
			if (dia.toString().equals(diaSemanaParam)) {
				this.getHoraAula().getId().setDia(dia);
			}
		}
	    
	    this.getHoraAula().setStatus(StatusHoraAula.ATIVA);
	    
	    this.carregaResumos();
	    
	    RequestContext.getCurrentInstance().execute("exibeModalAula()");
	    
	}
	
	
	public void addHorario(){
		
		this.getListaHorarios().add(getHorario());
		
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
			this.listaHorarios = null;
			FacesContext.getCurrentInstance().getExternalContext().redirect("/redAmber-WebApp/grade-aula/index.xhtml");	
			
		} catch (IOException e) {
			
			RequestContext.getCurrentInstance().execute("alert('"+e.getMessage()+"');");
		}
	}
	
	
	

	public List<Horario> getListaHorarios() {

		
		if (this.atualiza == true) {
			
			this.listaHorarios = new ArrayList<Horario>();
			
			for (HoraAulaHTTP ha : this.getListaHoraAulaHTTP()) {
				
					int gat = 0;
					Horario horario = new Horario();
					horario.setHoraInicio(Datas.convertStringTimeToDate(ha.getId().getHoraInicio()));
					horario.setHoraFim(Datas.convertStringTimeToDate(ha.getId().getHoraFim()));
					
					for (Horario ho : this.listaHorarios) {
						
						if (ho.equals(horario)) {

							gat = 1;

						}

					}
					
					if (gat == 0) {
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
		
		if (this.atualizaListaHorariosHTTP == true) {
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_HORAAULA_POR_ID_TURMA + this.getTurma().getId());
			String jsonResult = wr.get(String.class);
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();

				HoraAulaHTTP[] lista = gson.fromJson(jsonResult, HoraAulaHTTP[].class);
				this.listaHoraAulaHTTP = Arrays.asList(lista);
				this.listaHoraAulaHTTP = new ArrayList<>(this.listaHoraAulaHTTP);
			} else {
				this.listaHoraAulaHTTP = new ArrayList<HoraAulaHTTP>();
			} 
		}
		
		return listaHoraAulaHTTP;
	}

	public void setListaHoraAulaHTTP(List<HoraAulaHTTP> listaHoraAulaHTTP) {
		this.listaHoraAulaHTTP = listaHoraAulaHTTP;
	}

	public List<HoraAula> getListaHoraAulas() {
		this.listaHoraAulas = new ArrayList<HoraAula>();
		
		for (HoraAulaHTTP haHTTP : this.listaHoraAulaHTTP) {
			
			HoraAula ha = new HoraAula();
			Aula a = new Aula();
			
			HoraAulaPK haPk = new HoraAulaPK();
			AulaPK aulaPK = new AulaPK();
			
			aulaPK.setSala(haHTTP.getId().getAula().getId().getSala());
			aulaPK.setDisciplina(haHTTP.getId().getAula().getId().getDisciplina());
			
			Professor p = new Professor();
			p.setDataNascimento(haHTTP.getId().getAula().getId().getProfessor().getDataNascimento());
			p.setEmail(haHTTP.getId().getAula().getId().getProfessor().getEmail());
			p.setId(haHTTP.getId().getAula().getId().getProfessor().getId());
			p.setListDisciplinas(haHTTP.getId().getAula().getId().getProfessor().getListaDisciplinas());
			p.setNome(haHTTP.getId().getAula().getId().getProfessor().getNome());
			p.setRg(haHTTP.getId().getAula().getId().getProfessor().getRg());
			p.setStatus(haHTTP.getId().getAula().getId().getProfessor().getStatus());
			p.setTelefone(haHTTP.getId().getAula().getId().getProfessor().getTelefone());
			p.setUsuario(haHTTP.getId().getAula().getId().getProfessor().getUsuario());
			
			aulaPK.setProfessor(p);
			
			a.setId(aulaPK);
			
			haPk.setTurma(haHTTP.getId().getTurma());
			haPk.setAula(a);
			
			haPk.setDia(haHTTP.getId().getDia());
			haPk.setHoraInicio(Datas.convertStringTimeToDate(haHTTP.getId().getHoraInicio()));
			haPk.setHoraFim(Datas.convertStringTimeToDate(haHTTP.getId().getHoraFim()));
			
			ha.setId(haPk);	
			
			this.listaHoraAulas.add(ha);
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
		WebResource wr = c.resource(URLUtil.LISTAR_DISCIPLINAS_POR_CURSO + 
				String.valueOf(this.turma.getCurso().getId()));
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

	public Horario getHorario2() {
		if (this.horario2 == null) {
			this.horario2 = new Horario();
		}
		return horario2;
	}

	public void setHorario2(Horario horario2) {
		this.horario2 = horario2;
	}

	
	
	
}
