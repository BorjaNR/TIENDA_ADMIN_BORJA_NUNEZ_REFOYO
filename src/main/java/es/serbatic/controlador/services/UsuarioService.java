package es.serbatic.controlador.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.serbatic.modelo.DAO.UsuarioDAO;
import es.serbatic.modelo.VO.UsuarioVO;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO dao;
	
	public boolean validUsuarioLogin(UsuarioVO u) {
		boolean esValido = false;
		
		if(u != null) {
			if(!u.getEmail().isBlank() || !u.getEmail().isEmpty()) {
				esValido = true;
			}else {
				esValido= false;
			}
			if(!u.getClave().isBlank() || !u.getClave().isEmpty()) {
				esValido = true;
			}else {
				esValido= false;
			}
		}	
		
		return esValido;
	}
	
	public UsuarioVO getUsuarioByEmail(String email) {
		UsuarioVO u = null;

		u = dao.findByEmailAndClave(email);
		
		return u;
	}
}
