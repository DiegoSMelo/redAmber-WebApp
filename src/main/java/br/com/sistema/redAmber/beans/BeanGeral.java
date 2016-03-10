package br.com.sistema.redAmber.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class BeanGeral {

	private String title;
	private String template;

	public String getTemplate() {
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		LoginMB loginMB = (LoginMB)session.getAttribute("loginMB");
		
		switch (loginMB.getUsuarioLogado().getTipoFuncionario().toString()) {

			case "Coordenador":

				template = "../WEB-INF/templates/baseCoordenador.xhtml";
				break;

			case "Secretário":

				template = "../WEB-INF/templates/baseSecretario.xhtml";
				break;

		}

		return template;
		
	}

	public String getTitle() {
		
		title = "Red Amber";
		
		return title;

	}

	public void setTitle(String title) {

		this.title = title;

	}
}
