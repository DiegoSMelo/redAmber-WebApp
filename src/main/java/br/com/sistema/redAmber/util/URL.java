package br.com.sistema.redAmber.util;

public abstract class URL {
	
	public static final String BASE = "http://localhost:8089";
	
	public static final String LISTAR_ALUNOS = BASE + "/redAmber-Service/redamberws/aluno/listar";
	
	public static final String BUSCAR_ALUNO_POR_ID = BASE + "/redAmber-Service/redamberws/aluno/";
	
	public static final String SALVAR_ALUNO = BASE + "/redAmber-Service/redamberws/aluno/salvar";
	
	public static final String LOGIN = BASE + "/redAmber-Service/redamberws/login";
}
