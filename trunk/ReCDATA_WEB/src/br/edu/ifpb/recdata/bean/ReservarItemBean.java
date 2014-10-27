package br.edu.ifpb.recdata.bean;

import javax.faces.bean.ManagedBean;

import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.entidades.ReservaItem;

@ManagedBean
public class ReservarItemBean extends ReservaItem{

	public ReservarItemBean(Item item) {
		super.setItem(item);
	}
	
	public void redirecionarReservaItem() {
		GenericBean.sendRedirect(PathRedirect.reservarItem);
	}
}
