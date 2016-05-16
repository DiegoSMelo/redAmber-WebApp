package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.sistema.redAmber.basicas.integracao.MatriculaIntegracao;
import br.com.sistema.redAmber.util.URLUtil;

@FacesConverter(value="matriculaIntegracaoConverter", forClass=MatriculaIntegracao.class)
public class MatriculaIntegracaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		System.out.println("String -> Object");
		
		if (arg2.trim().equals("") || arg2 == null || arg2.isEmpty()) {
			return null;
		}
		try {
			MatriculaIntegracao matricula = null;
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_MATRICULA_INTEGRACAO_POR_ID + arg2);
			String jsonResult = wr.get(String.class);
			
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				matricula = gson.fromJson(jsonResult, MatriculaIntegracao.class);
				return matricula;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		System.out.println("Object -> String");
		
		MatriculaIntegracao matricula = (MatriculaIntegracao) arg2;
		if (matricula == null || matricula.getId() == null) {
			return null;
		} else {
			return String.valueOf(matricula.getId());
		}
	}
}