package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.sistema.redAmber.basicas.Grade;
import br.com.sistema.redAmber.util.URLUtil;

@FacesConverter(value="gradeConverter", forClass=Grade.class)
public class GradeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		
		if (arg2.trim().equalsIgnoreCase("Escolha a grade") || arg2.trim().equals("") ||
				arg2 == null || arg2.isEmpty()) {
			return null;
		}
		try {
			Grade grade = null;
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_GRADE_POR_ID + arg2);
			String jsonResult = wr.get(String.class);
			
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				grade = gson.fromJson(jsonResult, Grade.class);
				return grade;
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
		
		
		Grade grade = (Grade) arg2;
		if (grade == null || grade.getId() == null) {
			return null;
		} else {
			return String.valueOf(grade.getId());
		}
	}
}