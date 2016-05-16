package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.StatusGrade;

@FacesConverter(value="statusGradeConverter", forClass=StatusGrade.class)
public class StatusGradeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("String -> Object");
		if(arg2 == null || arg2.trim().equals(""))
		{
			return null;
		}
		try {
			if (arg2.equalsIgnoreCase("Pendente")) {
				return StatusGrade.PENDENTE;
			}
			if (arg2.equalsIgnoreCase("Ativa")) {
				return StatusGrade.ATIVA;
			}
			if (arg2.equalsIgnoreCase("Inativa")) {
				return StatusGrade.INATIVA;
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
		StatusGrade status = (StatusGrade) arg2;
		if (status.equals(StatusGrade.PENDENTE)) {
			return StatusGrade.PENDENTE.toString();
		}
		if (status.equals(StatusGrade.ATIVA)) {
			return StatusGrade.ATIVA.toString();
		}
		if (status.equals(StatusGrade.INATIVA)) {
			return StatusGrade.INATIVA.toString();
		}
		return null;
	}
}