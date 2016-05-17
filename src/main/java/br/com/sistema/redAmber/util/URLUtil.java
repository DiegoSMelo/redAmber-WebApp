package br.com.sistema.redAmber.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public abstract class URLUtil {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	//                                           SUMÁRIO                                         //
	///////////////////////////////////////////////////////////////////////////////////////////////
	// URL BASE    -> LINHAS:  37 ~  39 / EQUIPAMENTO                       -> LINHAS: 246 ~ 263 //
	// ALUNO       -> LINHAS:  41 ~  66 / SALA                              -> LINHAS: 265 ~ 282 //
	// FUNCIONÁRIO -> LINHAS:  68 ~  97 / DURAÇÃO DE AULA                   -> LINHAS: 284 ~ 301 //
	// PROFESSOR   -> LINHAS:  99 ~ 124 / RESERVA DE EQUIPAMENTO            -> LINHAS: 303 ~ 324 //
	// MATRÍCULA   -> LINHAS: 126 ~ 149 / RESERVA DE SALA                   -> LINHAS: 326 ~ 347 //
	// CURSO       -> LINHAS: 151 ~ 168 / AVISO DO PROFESSOR                -> LINHAS: 349 ~ 364 //
	// DISCIPLINA  -> LINHAS: 170 ~ 187 / AVISO AO ALUNO                    -> LINHAS: 366 ~ 379 //
	// TURMA       -> LINHAS: 189 ~ 206 / MATRÍCULA-INTEGRAÇÃO (EDUCA MAIS) -> LINHAS: 381 ~ 406 //
	// GRADE       -> LINHAS: 208 ~ 244 / HORÁRIO DAS AULAS                 -> LINHAS: 408 ~ 443 // 
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static Properties prop;
	static {
		try {
			prop = new Properties();
			prop.load(new FileInputStream(
					"C:/Config/properties/url.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	///////////////// URL BASE /////////////////
	public static final String BASE = prop.getProperty("url.base");
	///////////////////////////////////////////

	///////////////// ALUNO /////////////////

	/*
	 * param ID_ALUNO (GET)
	 */
	public static final String BUSCAR_ALUNO_POR_ID = BASE + prop.getProperty("url.buscar.aluno.por.id");
	/*
	 * param RG_ALUNO (POST)
	 */
	public static final String BUSCAR_ALUNO_POR_RG = BASE + prop.getProperty("url.buscar.aluno.por.rg");
	/*
	 * param BUSCA_ALUNO (POST)
	 */
	public static final String BUSCAR_ALUNO_POR_NOME_RG = BASE + prop.getProperty("url.buscar.aluno.por.nome.rg");
	/*
	 * param LOGIN & SENHA (GET)
	 */
	public static final String LOGIN_ALUNO = BASE + prop.getProperty("url.login.aluno");
	/*
	 * param ALUNO (POST)
	 */
	public static final String SALVAR_ALUNO = BASE + prop.getProperty("url.salvar.aluno");
		
	public static final String LISTAR_ALUNOS = BASE + prop.getProperty("url.listar.alunos");
	
	////////////////////////////////////////
	
	///////////////// FUNCIONÁRIO /////////////////
	
	/*
	 * param ID_FUNCIONARIO (GET)
	 */
	public static final String BUSCAR_FUNCIONARIO_POR_ID = BASE + prop.getProperty("url.buscar.funcionario.por.id");
	/*
	 * param RG_FUNCIONARIO (GET)
	 */
	public static final String BUSCAR_FUNCIONARIO_POR_RG = BASE + prop.getProperty("url.buscar.funcionario.por.rg");	
	/*
	 * param BUSCA_FUNCIONARIO (POST)
	 */
	public static final String BUSCAR_FUNCIONARIO_POR_NOME_RG = BASE + prop.getProperty("url.buscar.funcionario.por.nome.rg");
	/*
	 * param LOGIN_FUNCIONARIO (GET)
	 */
	public static final String BUSCAR_FUNCIONARIO_POR_LOGIN = BASE + prop.getProperty("url.buscar.funcionario.por.login");
	/*
	 * param LOGIN & SENHA (POST)
	 */
	public static final String LOGIN_FUNCIONARIO = BASE + prop.getProperty("url.login.funcionario");
	/*
	 * param FUNCIONARIO (POST)
	 */
	public static final String SALVAR_FUNCIONARIO = BASE + prop.getProperty("url.salvar.funcionario");

	public static final String LISTAR_FUNCIONARIOS = BASE + prop.getProperty("url.listar.funcionarios");
	
	//////////////////////////////////////////////

	///////////////// PROFESSOR /////////////////

	/*
	 * param ID_PROFESSOR (GET)
	 */
	public static final String BUSCAR_PROFESSOR_POR_ID = BASE + prop.getProperty("url.buscar.professor.por.id");	
	/*
	 * param RG PROFESSOR
	 */
	public static final String BUSCAR_PROFESSOR_POR_RG = BASE + prop.getProperty("url.buscar.professor.por.rg");
	/*
	 * param BUSCA_PROFESSOR (POST)
	 */
	public static final String BUSCAR_PROFESSOR_POR_NOME_RG = BASE + prop.getProperty("url.buscar.professor.por.nome.rg");
	/*
	 * param PROFESSOR (POST)
	 */
	public static final String SALVAR_PROFESSOR = BASE + prop.getProperty("url.salvar.professor");
	/*
	 * param ID_DISCIPLINA (GET)
	 */
	public static final String LISTAR_PROFESSORES_POR_DISCIPLINA = BASE + prop.getProperty("url.lista.professores.por.disciplina");
	
	public static final String LISTAR_PROFESSORES = BASE + prop.getProperty("url.listar.professores");
	
	/////////////////////////////////////////////

	///////////////// MATRÍCULA /////////////////

	/*
	 * param ID_MATRICULA (GET)
	 */
	public static final String BUSCAR_MATRICULA_POR_ID = BASE + prop.getProperty("url.buscar.matricula.por.id");
	/*
	 * param CODIGO_MATRICULA (GET)
	 */
	public static final String BUSCAR_MATRICULA_POR_CODIGO = BASE + prop.getProperty("url.buscar.matricula.por.codigo");
	/*
	 * param ID_ALUNO (GET)
	 */
	public static final String BUSCAR_MATRICULAS_POR_ALUNO = BASE + prop.getProperty("url.buscar.matriculas.por.aluno");
	/*
	 * param ID_ALUNO & ID_CURSO (GET)
	 */
	public static final String BUSCAR_MATRICULA_POR_ALUNO_CURSO = BASE + prop.getProperty("url.buscar.matricula.por.aluno.curso");	
	/*
	 * param MATRICULA (POST)
	 */
	public static final String SALVAR_MATRICULA = BASE + prop.getProperty("url.matricula.cadastrar");
	
	/////////////////////////////////////////////

	///////////////// CURSO /////////////////
	
	/*
	 * param ID_CURSO (GET)
	 */
	public static final String BUSCAR_CURSO_POR_ID = BASE + prop.getProperty("url.buscar.curso.por.id");
	/*
	 * param NOME_CURSO & SIGLA_CURSO (GET)
	 */
	public static final String BUSCAR_CURSO_POR_NOME_E_SIGLA = BASE + prop.getProperty("url.buscar.curso.por.nome.e.sigla");
	/*
	 * param CURSO (POST)
	 */
	public static final String SALVAR_CURSO = BASE + prop.getProperty("url.salvar.curso");
	
	public static final String LISTAR_CURSOS = BASE + prop.getProperty("url.listar.cursos");
	
	/////////////////////////////////////////

	///////////////// DISCIPLINA /////////////////
	
	/*
	 * param ID_DISCIPLINA (GET)
	 */
	public static final String BUSCAR_DISCIPLINA_POR_ID = BASE + prop.getProperty("url.buscar.disciplina.por.id");
	/*
	 * param TITULO_DISCIPLINA (GET)
	 */
	public static final String BUSCAR_DISCIPLINA_POR_TITULO = BASE + prop.getProperty("url.buscar.disciplina.por.titulo");
	/*
	 * param DISCIPLINA (POST)
	 */
	public static final String SALVAR_DISCIPLINA = BASE + prop.getProperty("url.salvar.disciplina");

	public static final String LISTAR_DISCIPLINAS = BASE + prop.getProperty("url.lista.disciplinas");	
	
	//////////////////////////////////////////////

	///////////////// TURMA /////////////////
	
	/*
	 * param ID_TURMA (GET)
	 */
	public static final String BUSCAR_TURMA_POR_ID = BASE + prop.getProperty("url.buscar.turma.por.id");
	/*
	 * param ID_CURSO & TIPO_TURNO (GET)
	 */
	public static final String LISTAR_TURMAS_POR_CURSO_TURNO = BASE + prop.getProperty("url.buscar.turmas.por.curso.turno");
	/*
	 * param TURMA (POST)
	 */
	public static final String SALVAR_TURMA = BASE + prop.getProperty("url.salvar.turma");

	public static final String LISTAR_TURMAS = BASE + prop.getProperty("url.listar.turmas");	
	
	/////////////////////////////////////////

	///////////////// GRADE /////////////////
	
	/*
	 * param ID_GRADE (GET)
	 */
	public static final String BUSCAR_GRADE_POR_ID = BASE + prop.getProperty("url.buscar.grade.por.id");
	/*
	 * param ID_CURSO (GET)
	 */
	public static final String LISTAR_GRADES_POR_CURSO = BASE + prop.getProperty("url.listar.grades.por.curso");
	/*
	 * param GRADE (POST) 
	 */
	public static final String SALVAR_GRADE = BASE + prop.getProperty("url.salvar.grade");

	public static final String LISTAR_GRADES = BASE + prop.getProperty("url.listar.grades");
	
	//----- GRADE-DISCIPLINA -----//

	/*
	 * param ID_GRADE_DISCIPLINA (GET)
	 */
	public static final String LISTAR_GRADE_DISCIPLINA_POR_ID = BASE + prop.getProperty("url.buscar.grade-disciplina.por.id");
	/*
	 * param ID_GRADE (GET)
	 */
	public static final String LISTAR_GRADE_DISCIPLINA_POR_GRADE = BASE + prop.getProperty("url.listar.grade-disciplina.por.grade");
	/*
	 * param GRADE_DISCIPLINA (POST)
	 */
	public static final String SALVAR_GRADE_DISCIPLINA = BASE + prop.getProperty("url.salvar.grade-disciplina");
	/*
	 * param ???
	 */
	public static final String REMOVER_GRADE_DISCIPLINA = BASE + prop.getProperty("url.remover.grade-disciplina");	
	
	/////////////////////////////////////////

	///////////////// EQUIPAMENTO /////////////////
	
	/*
	 * param ID_EQUIPAMENTO (GET)
	 */
	public static final String BUSCAR_EQUIPAMENTO_POR_ID = BASE + prop.getProperty("url.buscar.equipamento.por.id");
	/*
	 * param TOMBO_EQUIPAMENTO (GET)
	 */
	public static final String BUSCAR_EQUIPAMENTO_POR_TOMBO = BASE + prop.getProperty("url.buscar.equipamento.por.tombo");
	/*
	 * param EQUIPAMENTO (POST)
	 */
	public static final String SALVAR_EQUIPAMENTO = BASE + prop.getProperty("url.salvar.equipamento");

	public static final String LISTAR_EQUIPAMENTOS = BASE + prop.getProperty("url.listar.equipamentos");	
	
	///////////////////////////////////////////////

	///////////////// SALA /////////////////
	
	/*
	 * param ID_SALA (GET)
	 */
	public static final String BUSCAR_SALA_POR_ID = BASE + prop.getProperty("url.buscar.sala.por.id");
	/*
	 * param DESCRICAO_SALA (GET)
	 */
	public static final String BUSCAR_SALA_POR_DESCRICAO = BASE + prop.getProperty("url.buscar.sala.por.descricao");
	/*
	 * param SALA (POST)
	 */
	public static final String SALVAR_SALA = BASE + prop.getProperty("url.salvar.sala");

	public static final String LISTAR_SALAS = BASE + prop.getProperty("url.listar.salas");	
	
	////////////////////////////////////////

	///////////////// DURAÇÃO DAS AULAS /////////////////
	
	/*
	 * param ID_DURACAO_AULA (GET)
	 */
	public static final String BUSCAR_DURACAO_AULA_POR_ID = BASE + prop.getProperty("url.buscar.duracaoaula.por.id");	
	/*
	 * param HORA_INICIO_AULA & HORA_FIM_AULA (GET) 
	 */
	public static final String BUSCAR_DURACAO_AULA_POR_HORA = BASE + prop.getProperty("url.buscar.duracaoaula.por.hora");
	/*
	 * param DURACAO_AULA (POST)
	 */
	public static final String SALVAR_DURACAO_AULA = BASE + prop.getProperty("url.salvar.duracaoaula");

	public static final String LISTAR_DURACOES_AULA = BASE + prop.getProperty("url.listar.duracoessaula");	
	
	/////////////////////////////////////////////////////
	
	///////////////// RESERVA DE EQUIPAMENTO /////////////////
	
	/*
	 * param ID_RESERVA_EQUIPAMENTO (GET)
	 */
	public static final String BUSCAR_RESERVA_EQUIPAMENTO_POR_ID = BASE + prop.getProperty("url.buscar.reserva.de.equipamento.por.id");
	/*
	 * param DATA_RESERVA & ID_HORARIO (GET)
	 */
	public static final String VERIFICAR_RESERVA_POR_DATA_RESERVA_HORARIO = BASE + prop.getProperty("url.verificar.reserva.por.data.horario");
	/*
	 * param RESERVA_EQUIPAMENTO (POST)
	 */
	public static final String SALVAR_RESERVA_EQUIPAMENTO = BASE + prop.getProperty("url.salvar.reserva.de.equipamento");

	public static final String LISTAR_RESERVAS_EQUIPAMENTO = BASE + prop.getProperty("url.listar.reservas.de.equipamentos");

	public static final String LISTAR_RESERVAS_EQUIPAMENTOS_PENDENTES = BASE + prop.getProperty("url.listar.reservas.de.equipamentos.pendentes");

	public static final String NUMERO_RESERVAS_EQUIPAMENTOS_HOJE = BASE + prop.getProperty("url.numero.de.reservas.de.equipamentos.do.dia");	
	
	//////////////////////////////////////////////////////////

	///////////////// RESERVA DE SALA /////////////////
	
	/*
	 * param ID_RESERVA_SALA (GET)
	 */
	public static final String BUSCAR_RESERVA_SALA_POR_ID = BASE + prop.getProperty("url.buscar.reserva.de.sala.por.id");
	/*
	 * param DATA_RESERVA & ID_HORARIO (GET)
	 */
	public static final String VERIFICAR_RESERVA_SALA_POR_DATA_RESERVA_HORARIO = BASE + prop.getProperty("url.verificar.reserva.sala.por.data.horario");
	/*
	 * param RESERVA_SALA (POST)
	 */
	public static final String SALVAR_RESERVA_SALA = BASE + prop.getProperty("url.salvar.reserva.de.sala");

	public static final String LISTAR_RESERVAS_SALA = BASE + prop.getProperty("url.listar.reservas.de.salas");

	public static final String LISTAR_RESERVAS_SALAS_PENDENTES = BASE + prop.getProperty("url.listar.reservas.de.salas.pendentes");

	public static final String NUMERO_RESERVAS_SALAS_HOJE = BASE + prop.getProperty("url.numero.de.reservas.de.salas.do.dia");	
	
	///////////////////////////////////////////////////

	///////////////// AVISO DO PROFESSOR /////////////////
	
	/*
	 * param ID_AVISO_PROFESSOR (GET)
	 */
	public static final String BUSCAR_AVISO_PROFESSOR_POR_ID = BASE + prop.getProperty("url.buscar.aviso.do.professor.por.id");
	/*
	 * param AVISO_PROFESSOR (POST)
	 */
	public static final String SALVAR_AVISO_PROFESSOR = BASE + prop.getProperty("url.salvar.aviso.do.professor");

	public static final String LISTAR_AVISOS_PROFESSORES = BASE + prop.getProperty("url.listar.avisos.do.professor");

	public static final String NUMERO_AVISOS_PROFESSORES_HOJE = BASE + prop.getProperty("url.numero.de.avisos.de.professores.do.dia");	
	
	//////////////////////////////////////////////////////

	///////////////// AVISO AO ALUNO /////////////////
	
	/*
	 * param ID_AVISO_ALUNO (GET)
	 */
	public static final String BUSCAR_AVISO_ALUNO_POR_ID = BASE + prop.getProperty("url.buscar.aviso.ao.aluno.por.id");
	/*
	 * param AVISO_ALUNO (POST)
	 */
	public static final String SALVAR_AVISO_ALUNO = BASE + prop.getProperty("url.salvar.aviso.ao.aluno");

	public static final String LISTAR_AVISOS_ALUNOS = BASE + prop.getProperty("url.listar.avisos.ao.aluno");	
	
	//////////////////////////////////////////////////
	
	///////////////// MATRÍCULA-INTEGRAÇÃO (EDUCA MAIS) /////////////////
	
	/*
	 * param ID_MATRICULA_INTEGRACAO (GET)
	 */
	public static final String BUSCAR_MATRICULA_INTEGRACAO_POR_ID = BASE + prop.getProperty("url.buscar.matricula.integracao.por.id");
	/*
	 * param CODIGO_MATRICULA_INTEGRACAO (GET)
	 */
	public static final String BUSCAR_MATRICULA_INTEGRACAO_POR_CODIGO = BASE + prop.getProperty("url.buscar.matricula.integracao.por.codigo");
	/*
	 * param ID_ALUNO (GET)
	 */
	public static final String BUSCAR_MATRICULAS_INTEGRACAO_POR_ALUNO = BASE + prop.getProperty("url.buscar.matriculas.integracao.por.aluno");	
	/*
	 * param ID_ALUNO e ID_CURSO (GET)
	 */
	public static final String BUSCAR_MATRICULA_INTEGRACAO_POR_ALUNO_CURSO = BASE + prop.getProperty("url.buscar.matricula.integracao.por.aluno.curso");
	/*
	 * param MATRICULA_INTEGRACAO (POST)
	 */
	public static final String SALVAR_MATRICULA_INTEGRACAO = BASE + prop.getProperty("url.matricula.integracao.cadastrar");

	public static final String LISTAR_ALUNOS_EDUCA_MAIS = prop.getProperty("url.listar.todos.alunos.educa.mais");
	
	/////////////////////////////////////////////////////////////////////

	///////////////// HORÁRIO DAS AULAS /////////////////
	
	/*
	 * param ID_TURMA (GET)
	 */
	public static final String BUSCAR_HORAAULA_POR_ID_TURMA = BASE + prop.getProperty("url.buscar.hora-aula.por.id.turma");
	/*
	 * param: Objeto do tipo HoraAulaPK preenchido
	 */
	public static final String BUSCAR_HORAAULA_POR_HORAAULAPK = BASE + prop.getProperty("url.buscar.hora-aula.por.horaaulapk");
	/*
	 * param HORA_AULA (POST) 
	 */
	public static final String ADD_HORA_AULA = BASE + prop.getProperty("url.add.hora-aula");
	/*
	 * param POST: Objeto do tipo HoraAulaHTTP preenchido
	 */
	public static final String REMOVER_HORA_AULA = BASE + prop.getProperty("url.remover.hora-aula");
	/*
	 * param POST: OBJ TURMA
	 */
	public static final String REMOVER_HORA_AULA_POR_TURMA = BASE + prop.getProperty("url.remover.hora.aula.por.turma");

	public static final String LISTAR_AULAS = BASE + prop.getProperty("url.listar.aulas");
	/*
	 * param: Objeto PK instanciado
	 */
	public static final String BUSCAR_AULA_POR_PK = BASE + prop.getProperty("url.buscar.aula.por.pk");

	public static final String ADD_AULA = BASE + prop.getProperty("url.add.aula");
	/*
	 * PARAM POST: Objeto do tipo Aula preenchido
	 */
	public static final String REMOVER_AULA = BASE + prop.getProperty("url.remover.aula");
	
	/////////////////////////////////////////////////////
}