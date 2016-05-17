package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.StatusUsuario;

@FacesConverter(value="statusUsuarioConverter", forClass=StatusUsuario.class)
public class StatusUsuarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("String -> Object");
		if(arg2 == null || arg2.trim().equals(""))
		{
			return null;
		}
		try {
			if (arg2.equalsIgnoreCase("Ativo")) {
				return StatusUsuario.ATIVO;
			}
			if (arg2.equalsIgnoreCase("Inativo")) {
				return StatusUsuario.INATIVO;
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
		StatusUsuario status = (StatusUsuario) arg2;
		if (status.equals(StatusUsuario.ATIVO)) {
			return StatusUsuario.ATIVO.toString();
		}
		if (status.equals(StatusUsuario.INATIVO)) {
			return StatusUsuario.INATIVO.toString();
		}
		return null;
	}
}