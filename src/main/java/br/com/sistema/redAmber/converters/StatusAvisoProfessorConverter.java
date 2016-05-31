package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.StatusAvisoProfessor;

@FacesConverter(value="statusAvisoProfessorConverter", forClass=StatusAvisoProfessor.class)
public class StatusAvisoProfessorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		System.out.println("String -> Object");
		
		if(arg2 == null || arg2.trim().equals("") || arg2.trim().equalsIgnoreCase("Escolha o Status") ||
				arg2.trim().equalsIgnoreCase("Ambos"))
		{
			return null;
		}
		try {
			if (arg2.equalsIgnoreCase("Enviado")) {
				return StatusAvisoProfessor.ENVIADO;
			}
			if (arg2.equalsIgnoreCase("Recebido")) {
				return StatusAvisoProfessor.RECEBIDO;
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
		StatusAvisoProfessor status = (StatusAvisoProfessor) arg2;
		if (status.equals(StatusAvisoProfessor.ENVIADO)) {
			return StatusAvisoProfessor.ENVIADO.toString();
		}
		if (status.equals(StatusAvisoProfessor.RECEBIDO)) {
			return StatusAvisoProfessor.RECEBIDO.toString();
		}
		return null;
	}
}