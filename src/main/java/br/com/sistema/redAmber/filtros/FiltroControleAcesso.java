package br.com.sistema.redAmber.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sistema.redAmber.beans.LoginMB;

@WebFilter(servletNames="Faces Servlet", filterName="/FiltroControleAcesso")
public class FiltroControleAcesso implements Filter{

	private static final String[] arquivosEscape = { 
		"javax.faces.resource",
		"login.xhtml"
	};
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse)response; 
		HttpSession sessao = req.getSession();

		if (isPaginaInicial(request)) {
			chain.doFilter(request, response);
		} else {
			if (sessao == null || sessao.getAttribute("loginMB") == null
					|| ((LoginMB) sessao.getAttribute("loginMB")).getUsuarioLogado() == null) {


				res.sendRedirect("/redAmber-WebApp/login.xhtml");
				
			} else {
				chain.doFilter(request, response);
			}
		}
		 
		
		 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
	protected boolean isPaginaInicial(ServletRequest req) {
		
		String resource = ((HttpServletRequest) req).getRequestURI();
		for (String esc : arquivosEscape) {
			if (resource.contains(esc)) {
				return true;
			}
		}
		
		return false;
	}
}
