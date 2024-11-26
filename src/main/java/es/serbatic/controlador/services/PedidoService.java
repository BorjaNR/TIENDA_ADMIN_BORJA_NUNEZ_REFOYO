package es.serbatic.controlador.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.serbatic.modelo.DAO.PedidoDAO;
import es.serbatic.modelo.VO.PedidoVO;

@Service
public class PedidoService {

	@Autowired
	PedidoDAO pd;
	
	public List<PedidoVO> getListadoPendientes(){
		List<PedidoVO> listado = pd.findAll();
		List<PedidoVO> listadoPendientes = new ArrayList<PedidoVO>();
		
		for (PedidoVO pedido : listado) {
			if(pedido.getEstado().equals("Pendiente de cancelacion")) {
				listadoPendientes.add(pedido);
			}
		}
		
		return listadoPendientes;
	}
	
	public void cancelarPedido(int id) {
		PedidoVO p = pd.findById(id).orElse(null);
		p.setEstado("Cancelado");
		pd.save(p);
	}
}
