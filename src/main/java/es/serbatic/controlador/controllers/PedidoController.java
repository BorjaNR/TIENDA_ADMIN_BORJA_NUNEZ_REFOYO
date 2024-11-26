package es.serbatic.controlador.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.serbatic.controlador.services.DireccionService;
import es.serbatic.controlador.services.PedidoService;
import es.serbatic.modelo.VO.DetallePedidoVO;
import es.serbatic.modelo.VO.DireccionVO;
import es.serbatic.modelo.VO.PedidoVO;
import es.serbatic.modelo.VO.ProductoVO;
import jakarta.servlet.http.HttpSession;

@Controller
public class PedidoController {
	
	@Autowired
	PedidoService ps;
	
	@Autowired
	DireccionService ds;
	
	@GetMapping("/pedidosPendientes")
	String pedidosPendientes(Model model, HttpSession sesion) {
		String pagina = "redirect:/";
		
		if(sesion.getAttribute("idUsuario")!=null){
			List<PedidoVO> lista = ps.getListadoPendientes();
			List<DireccionVO> listaDirecciones = new ArrayList<DireccionVO>();
			
			 for (PedidoVO pedido : lista) {
			     DireccionVO d = ds.getDirecion(pedido.getDireccion_id());
			     listaDirecciones.add(d);
			 }
			 
			if(!lista.isEmpty()) {
				model.addAttribute("pedidos", lista);
				model.addAttribute("direcciones", listaDirecciones);
			}
			
			pagina = "admin";
		}
		
		return pagina;
	}
	
	@PostMapping("/cancelarPedido")
	String cancelarPedido(@RequestParam("pedidoId") String idPedido) {
		int id = Integer.parseInt(idPedido);
		
		ps.cancelarPedido(id);
		
		return "redirect:/pedidosPendientes";
	}
}
