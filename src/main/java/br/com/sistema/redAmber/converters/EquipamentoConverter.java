package br.com.sistema.redAmber.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.sistema.redAmber.basicas.Equipamento;
import br.com.sistema.redAmber.util.URLUtil;

@FacesConverter(value="equipamentoConverter", forClass=Equipamento.class)
public class EquipamentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("String -> Object");
		
		if (arg2.trim().equalsIgnoreCase("Escolha o Equipamento") || arg2.trim().equals("") || 
				arg2 == null || arg2.isEmpty()) {
			return null;
		}
		try {
			Equipamento equipamento = null;
			Client c = new Client();
			WebResource wr = c.resource(URLUtil.BUSCAR_EQUIPAMENTO_POR_ID + arg2);
			String jsonResult = wr.get(String.class);
			
			if (!jsonResult.equalsIgnoreCase("null")) {
				Gson gson = new Gson();
				equipamento = gson.fromJson(jsonResult, Equipamento.class);
				return equipamento;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		System.out.println("Object -> String");
		Equipamento equipamento = (Equipamento) arg2;
		if (equipamento == null || equipamento.getId() == null) {
			return null;
		} else {
			return String.valueOf(equipamento.getId());
		}
	}
}