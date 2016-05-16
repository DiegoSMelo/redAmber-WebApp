package br.com.sistema.redAmber.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

import br.com.sistema.redAmber.basicas.integracao.AlunoIntegracao;
import br.com.sistema.redAmber.basicas.integracao.Alunos;
import br.com.sistema.redAmber.basicas.integracao.Integracao;
import br.com.sistema.redAmber.util.Mensagens;
import br.com.sistema.redAmber.util.URLUtil;

@ManagedBean
@SessionScoped
public class TesteIntegracaoMB {

	private List<AlunoIntegracao> listaIntegracao;
	private List<Alunos> listaAlunos;
	
	public void init() {
		listaIntegracao = new ArrayList<AlunoIntegracao>();
		listaAlunos = new ArrayList<Alunos>();
	}
	
	/*
	 * Getters and setters
	 */
	public List<AlunoIntegracao> getListaIntegracao() {
		
		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		
		String jsonResult;
		try {
			WebResource webResourcePost = client.resource(URLUtil.LISTAR_ALUNOS_EDUCA_MAIS);
			ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class);
			if (response.getStatus() != 200) {
				RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m23 + "');");
			}
			jsonResult = response.getEntity(String.class);
			jsonResult = jsonResult.substring(271);
			//System.out.println("JSON:" + jsonResult);
		    if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				
				Integracao integ = gson.fromJson(jsonResult, Integracao.class);
				
				listaIntegracao = new ArrayList<AlunoIntegracao>();
				for (Alunos as : integ.getAlunos()) {
					listaIntegracao.add(as.getAlunoIntegracao());
				}
			}
		} catch (UniformInterfaceException e1) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m23 + "');");
			e1.printStackTrace();
		} catch (ClientHandlerException e1) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m23 + "');");
			e1.printStackTrace();
		} catch (Exception e) {
			RequestContext.getCurrentInstance().execute("alert('" + Mensagens.m23 + "');");
			e.printStackTrace();
		}
		return listaIntegracao;
	}
	public void setListaIntegracao(List<AlunoIntegracao> listaIntegracao) {
		this.listaIntegracao = listaIntegracao;
	}
	public List<Alunos> getListaAlunos() {
		return listaAlunos;
	}
	public void setListaAlunos(List<Alunos> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}
}