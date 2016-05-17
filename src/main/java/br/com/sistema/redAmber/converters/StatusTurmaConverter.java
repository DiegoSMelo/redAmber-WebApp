package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.StatusTurma;

@FacesConverter(value="statusTurmaConverter", forClass=StatusTurma.class)
public class StatusTurmaConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("String -> Object");
		if(arg2 == null || arg2.trim().equals(""))
		{
			return null;
		}
		try {
			if (arg2.equalsIgnoreCase("Pendente")) {
				return StatusTurma.PENDENTE;
			}
			if (arg2.equalsIgnoreCase("Ativa")) {
				return StatusTurma.ATIVA;
			}
			if (arg2.equalsIgnoreCase("Inativa")) {
				return StatusTurma.INATIVA;
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
		StatusTurma status = (StatusTurma) arg2;
		if (status.equals(StatusTurma.PENDENTE)) {
			return StatusTurma.PENDENTE.toString();
		}
		if (status.equals(StatusTurma.ATIVA)) {
			return StatusTurma.ATIVA.toString();
		}
		if (status.equals(StatusTurma.INATIVA)) {
			return StatusTurma.INATIVA.toString();
		}
		return null;
	}
}