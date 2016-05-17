package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.StatusMatricula;

@FacesConverter(value="statusMatriculaConverter", forClass=StatusMatricula.class)
public class StatusMatriculaConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("String -> Object");
		if(arg2 == null || arg2.trim().equals(""))
		{
			return null;
		}
		try {
			if (arg2.equalsIgnoreCase("Ativa")) {
				return StatusMatricula.ATIVA;
			}
			if (arg2.equalsIgnoreCase("Inativa")) {
				return StatusMatricula.INATIVA;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		System.out.println("Object -> String");
		if(arg2 == null)
		{
			return null;
		}
		StatusMatricula status = (StatusMatricula) arg2;
		if (status.equals(StatusMatricula.ATIVA)) {
			return StatusMatricula.ATIVA.toString();
		}
		if (status.equals(StatusMatricula.INATIVA)) {
			return StatusMatricula.INATIVA.toString();
		}
		return null;
	}
}