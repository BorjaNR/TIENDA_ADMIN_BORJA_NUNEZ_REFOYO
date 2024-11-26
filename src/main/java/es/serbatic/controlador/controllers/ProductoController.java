package es.serbatic.controlador.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.serbatic.controlador.services.CategoriaService;
import es.serbatic.controlador.services.ProductoService;
import es.serbatic.modelo.VO.CategoriaVO;
import es.serbatic.modelo.VO.ProductoVO;

@Controller
public class ProductoController {
	
	@Autowired
	ProductoService ps;
	@Autowired
	CategoriaService cs;

	@GetMapping("/verProductos")
	public String verProductos(Model model) {
		List<ProductoVO> listadoProducto = new ArrayList<ProductoVO>();
		
		listadoProducto = ps.getListado("");		
		
		model.addAttribute("productos", listadoProducto);
		
		return "productos";
	}
	
	@PostMapping("/bajaProducto")
	public String bajaProducto(@RequestParam("productoId") String productoId) {
		int id = Integer.parseInt(productoId);
		
		ps.darBajaAltaProducto(id);
		

		return "redirect:/verProductos";
	}
	
	@PostMapping("/eliminarProducto")
	public String eliminarProducto(@RequestParam("productoId") String productoId) {
		int id = Integer.parseInt(productoId);
		
		ps.eliminarProducto(id);
		
		return "redirect:/verProductos";
	}
	
	@GetMapping("/verNuevoProducto")
	public String eliminarProducto(Model model) {	
		List<CategoriaVO> listado = cs.getListado();
		
		model.addAttribute("categorias", listado);
		
		return "nuevoProducto";
	}
	
	@PostMapping("/verNuevoProducto/nuevo")
	public String añadirProducto(@ModelAttribute ProductoVO p, Model model) {
		String pagina = "redirect:/verNuevoProducto";
		
		if(ps.validProduct(p)) {
			ps.crearProducto(p);
			pagina = "redirect:/verProductos";
		}else {
			model.addAttribute("error", "producto no valido");
		}
		
		return pagina;
	}
}
