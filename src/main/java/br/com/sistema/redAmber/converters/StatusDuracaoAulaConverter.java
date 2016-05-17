package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.StatusDuracaoAula;

@FacesConverter(value="statusDuracaoAulaConverter", forClass=StatusDuracaoAula.class)
public class StatusDuracaoAulaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("String -> Object");
		if(arg2 == null || arg2.trim().equals(""))
		{
			return null;
		}
		try {
			if (arg2.equalsIgnoreCase("Ativa")) {
				return StatusDuracaoAula.ATIVA;
			}
			if (arg2.equalsIgnoreCase("Inativa")) {
				return StatusDuracaoAula.INATIVA;
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
		StatusDuracaoAula status = (StatusDuracaoAula) arg2;
		if (status.equals(StatusDuracaoAula.ATIVA)) {
			return StatusDuracaoAula.ATIVA.toString();
		}
		if (status.equals(StatusDuracaoAula.INATIVA)) {
			return StatusDuracaoAula.INATIVA.toString();
		}
		return null;
	}
}