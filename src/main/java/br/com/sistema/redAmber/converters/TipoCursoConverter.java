package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.TipoCurso;

@FacesConverter(value="tipoCursoConverter", forClass=TipoCurso.class)
public class TipoCursoConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		System.out.println("String -> Object");
		
		if(value.trim().equalsIgnoreCase("Escolha o Tipo") || value.trim().equalsIgnoreCase("Ambos") || 
				value.trim().equalsIgnoreCase("") || value == null)
		{
			return null;
		}
		try {
			if (value.equalsIgnoreCase("Superior")) {
				return TipoCurso.SUPERIOR;
			}
			if (value.equalsIgnoreCase("Técnico")) {
				return TipoCurso.TECNICO;
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
		TipoCurso tipo = (TipoCurso) value;
		if (tipo.equals(TipoCurso.SUPERIOR)) {
			return TipoCurso.SUPERIOR.toString();
		}
		if (tipo.equals(TipoCurso.TECNICO)) {
			return TipoCurso.TECNICO.toString();
		}
		return null;
	}
}