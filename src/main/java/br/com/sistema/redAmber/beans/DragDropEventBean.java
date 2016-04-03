package br.com.sistema.redAmber.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.richfaces.event.DropEvent;
import org.richfaces.event.DropListener;

import br.com.sistema.redAmber.basicas.Disciplina;

@ManagedBean
@RequestScoped
public class DragDropEventBean implements DropListener {
   
    private PeriodoMB periodoMB;
    
    public DragDropEventBean(){
    	this.periodoMB = new PeriodoMB();
    }

    public void setDragDropBean(PeriodoMB periodoMB) {
        this.periodoMB = periodoMB;
    }

    public void processDrop(DropEvent event) {
    	periodoMB.moveDisciplina((Disciplina) event.getDragValue());
    }
}