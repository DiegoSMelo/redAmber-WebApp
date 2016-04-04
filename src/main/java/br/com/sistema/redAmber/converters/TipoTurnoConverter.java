package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.TipoSala;

@FacesConverter(value="tipoSalaConverter", forClass=TipoSala.class)
public class TipoTurnoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		System.out.println("String -> Object");
		if(value == null || value.trim().equals(""))
		{
			return null;
		}
		try {
			if (value.equalsIgnoreCase("Laboratório")) {
				return TipoSala.LAB;
			}
			if (value.equalsIgnoreCase("Sala de aula")) {
				return TipoSala.AULA;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		System.out.println("Object -> String");
		if(value == null)
		{
			return null;
		}
		TipoSala tipo = (TipoSala) value;
		if (tipo.equals(TipoSala.LAB)) {
			return TipoSala.LAB.toString();
		}
		if (tipo.equals(TipoSala.AULA)) {
			return TipoSala.AULA.toString();
		}
		return null;
	}
}