package br.com.sistema.redAmber.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public abstract class URLUtil {
	private static Properties prop;
	
	static{
		try {
			
			prop = new Properties();			
			prop.load(new FileInputStream("C:/Users/Diego Melo/workspace/redAmber-WebApp/src/main/resources/url.properties"));
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	
	public static final String BASE = prop.getProperty("url.base");
	
	/*
	 * param ID
	 */
	public static final String LISTAR_ALUNOS = BASE + prop.getProperty("url.listar.alunos");
	
	public static final String BUSCAR_ALUNO_POR_ID = BASE + prop.getProperty("url.buscar.aluno.por.id");
	
	public static final String SALVAR_ALUNO = BASE + prop.getProperty("url.salvar.aluno");
	
	public static final String LOGIN_ALUNO = BASE + prop.getProperty("url.login.aluno");
	
	public static final String LOGIN_FUNCIONARIO = BASE + prop.getProperty("url.login.funcionario");
	
	/*
	 * param LOGIN
	 */
	public static final String BUSCAR_FUNCIONARIO_POR_LOGIN = BASE + prop.getProperty("url.buscar.funcionario.por.login");
	
	public static final String BUSCAR_ALUNO_POR_RG = BASE + prop.getProperty("url.buscar.aluno.por.rg");
	
	/*
	 * param ID ALUNO
	 */
	public static final String BUSCAR_MATRICULAS_POR_ALUNO = BASE + prop.getProperty("url.buscar.matriculas.por.aluno");
	
	public static final String SALVAR_MATRICULA = BASE + prop.getProperty("url.matricula.cadastrar");
}
