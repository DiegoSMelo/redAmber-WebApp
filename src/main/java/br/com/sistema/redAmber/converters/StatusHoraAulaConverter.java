package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.StatusHoraAula;

@FacesConverter(value="statusHoraAulaConverter", forClass=StatusHoraAula.class)
public class StatusHoraAulaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("String -> Object");
		if(arg2 == null || arg2.trim().equals(""))
		{
			return null;
		}
		try {
			if (arg2.equalsIgnoreCase("Ativa")) {
				return StatusHoraAula.ATIVA;
			}
			if (arg2.equalsIgnoreCase("Inativa")) {
				return StatusHoraAula.INATIVA;
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
		StatusHoraAula status = (StatusHoraAula) arg2;
		if (status.equals(StatusHoraAula.ATIVA)) {
			return StatusHoraAula.ATIVA.toString();
		}
		if (status.equals(StatusHoraAula.INATIVA)) {
			return StatusHoraAula.INATIVA.toString();
		}
		return null;
	}
}