package es.serbatic.controlador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.serbatic.modelo.DAO.CategoriaDAO;
import es.serbatic.modelo.VO.CategoriaVO;

@Service
public class CategoriaService {

	@Autowired
	CategoriaDAO cd;
	
	public List<CategoriaVO> getListado(){
		 List<CategoriaVO> listado = cd.findAll();
		 
		 return listado;
	}
}
