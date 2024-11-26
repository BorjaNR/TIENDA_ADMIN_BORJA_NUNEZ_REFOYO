package es.serbatic.controlador.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.serbatic.modelo.DAO.CategoriaDAO;
import es.serbatic.modelo.DAO.ProductoDAO;
import es.serbatic.modelo.VO.CategoriaVO;
import es.serbatic.modelo.VO.ProductoVO;
import es.serbatic.modelo.VO.UsuarioVO;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoDAO dao;
	@Autowired
	private CategoriaDAO cd;
	
	public ProductoVO obtenerProducto(int id) {
		ProductoVO p = new ProductoVO();
		
		p = dao.findById(id).orElse(null);
		
		return p;
	}
	
	public List<ProductoVO> getListado(String filtro){
		List<ProductoVO> listado = new ArrayList<ProductoVO>();
		
		if(filtro.equals("") || filtro.equals("0")) {
			listado = dao.findAll();
		}else {
			listado = dao.findByCategoriaId(Integer.parseInt(filtro));
		}

		return listado;
	}
	
	public void darBajaAltaProducto(int id) {
		ProductoVO p = new ProductoVO();
		
		p = dao.findById(id).orElse(null);
		
		if(p.getBaja() == 0) {
			p.setBaja(1);
		}else {
			p.setBaja(0);
		}

		dao.save(p);
	}
	
	public void eliminarProducto(int id) {
		 ProductoVO producto = dao.findById(id).orElse(null);

		 producto.setCategoria(null);

		 dao.delete(producto);
	}
	
	public boolean validProduct(ProductoVO p) {
	    if (p == null) {
	        return false;  // Si el objeto ProductoVO es nulo, devolvemos false
	    }

	    // Validamos que todos los campos esenciales no estén vacíos o nulos
	    boolean esValido = !p.getNombre().isBlank() && !p.getDescripcion().isBlank()
	                        && p.getPrecio() > 0 && p.getImpuesto() >= 0 && p.getStock() >= 0
	                        && p.getRuta() != null && !p.getRuta().isBlank();

	    // Devolvemos el resultado de la validación
	    return esValido;
	}
	
	public void crearProducto(ProductoVO p) {
		if (p.getCategoria() != null && p.getCategoria().getId() > 0) {
            CategoriaVO categoria = cd.findById(p.getCategoria().getId()).orElse(null);

            if (categoria != null) {
                p.setCategoria(categoria);

                dao.save(p);
            }
        }
	}
}
