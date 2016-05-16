package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.basicas.enums.StatusEquipamento;

@FacesConverter(value="statusEquipamentoConverter", forClass=StatusEquipamento.class)
public class StatusEquipamentoConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("String -> Object");
		if(arg2 == null || arg2.trim().equals(""))
		{
			return null;
		}
		try {
			if (arg2.equalsIgnoreCase("Ativo")) {
				return StatusEquipamento.ATIVO;
			}
			if (arg2.equalsIgnoreCase("Inativo")) {
				return StatusEquipamento.INATIVO;
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
		StatusEquipamento status = (StatusEquipamento) arg2;
		if (status.equals(StatusEquipamento.ATIVO)) {
			return StatusEquipamento.ATIVO.toString();
		}
		if (status.equals(StatusEquipamento.INATIVO)) {
			return StatusEquipamento.INATIVO.toString();
		}
		return null;
	}
}