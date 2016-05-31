package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.StatusReserva;

@FacesConverter(value="statusReservaConverter", forClass=StatusReserva.class)
public class StatusReservaConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("String -> Object");
		if(arg2 == null || arg2.trim().equals("") || arg2.trim().equalsIgnoreCase("Escolha o Status") || 
				arg2.trim().equalsIgnoreCase("Todos"))
		{
			return null;
		}
		try {
			if (arg2.equalsIgnoreCase("Pendente")) {
				return StatusReserva.PENDENTE;
			}
			if (arg2.equalsIgnoreCase("Aprovada")) {
				return StatusReserva.APROVADA;
			}
			if (arg2.equalsIgnoreCase("Negada")) {
				return StatusReserva.NEGADA;
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
		StatusReserva status = (StatusReserva) arg2;
		if (status.equals(StatusReserva.PENDENTE)) {
			return StatusReserva.PENDENTE.toString();
		}
		if (status.equals(StatusReserva.APROVADA)) {
			return StatusReserva.APROVADA.toString();
		}
		if (status.equals(StatusReserva.NEGADA)) {
			return StatusReserva.NEGADA.toString();
		}
		return null;
	}
}