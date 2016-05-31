package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.TipoAvisoProfessor;

@FacesConverter(value="tipoAvisoProfessorConverter", forClass=TipoAvisoProfessor.class)
public class TipoAvisoProfessorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		System.out.println("String -> Object");
		
		if(arg2 == null || arg2.trim().equals(""))
		{
			return null;
		}
		try {
			if (arg2.equalsIgnoreCase("Ausência")) {
				return TipoAvisoProfessor.AUSENCIA;
			}
			if (arg2.equalsIgnoreCase("Atraso")) {
				return TipoAvisoProfessor.ATRASO;
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
		TipoAvisoProfessor tipo = (TipoAvisoProfessor) arg2;
		if (tipo.equals(TipoAvisoProfessor.AUSENCIA)) {
			return TipoAvisoProfessor.AUSENCIA.toString();
		}
		if (tipo.equals(TipoAvisoProfessor.ATRASO)) {
			return TipoAvisoProfessor.ATRASO.toString();
		}
		return null;
	}
}