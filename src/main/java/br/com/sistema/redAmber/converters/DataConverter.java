package br.com.sistema.redAmber.converters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistema.redAmber.util.Datas;

@FacesConverter(value="dataConverter", forClass=Date.class)
public class DataConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		}
		String[] dataSplit = arg2.split("/");
		
		Calendar c = Datas.converterDateToCalendar(Datas.criarData(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2])));
		
		return c;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2 == null)
		{
			return null;
		}
		Calendar data = (Calendar) arg2;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data.getTime());
	}

}
