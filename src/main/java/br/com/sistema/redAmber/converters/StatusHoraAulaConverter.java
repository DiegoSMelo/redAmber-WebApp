package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.TipoTurno;

@FacesConverter(value="tipoTurnoConverter", forClass=TipoTurno.class)
public class StatusHoraAulaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("String -> Object");
		if(arg2 == null || arg2.trim().equals(""))
		{
			return null;
		}
		try {
			if (arg2.equalsIgnoreCase("Manhã")) {
				return TipoTurno.MANHA;
			}
			if (arg2.equalsIgnoreCase("Tarde")) {
				return TipoTurno.TARDE;
			}
			if (arg2.equalsIgnoreCase("Noite")) {
				return TipoTurno.NOITE;
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
		TipoTurno tipo = (TipoTurno) arg2;
		if (tipo.equals(TipoTurno.MANHA)) {
			return TipoTurno.MANHA.toString();
		}
		if (tipo.equals(TipoTurno.TARDE)) {
			return TipoTurno.TARDE.toString();
		}
		if (tipo.equals(TipoTurno.NOITE)) {
			return TipoTurno.NOITE.toString();
		}
		return null;
	}
}