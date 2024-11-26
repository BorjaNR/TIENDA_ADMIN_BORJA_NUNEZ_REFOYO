package es.serbatic.controlador.controllers;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.serbatic.controlador.services.UsuarioService;
import es.serbatic.modelo.VO.UsuarioVO;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	UsuarioService us;
	
	@GetMapping("")
	public String inicio() {
		return "index";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String pass, Model model, 
			HttpSession sesion) {
		
		String pagina = "redirect:/";		
		UsuarioVO u = null;
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		u = us.getUsuarioByEmail(email);
		
		if(us.validUsuarioLogin(u) && u.getRol_id()==2) {
			if(passwordEncryptor.checkPassword(pass, u.getClave())) {
				sesion.setAttribute("usuario", u);
				sesion.setAttribute("idUsuario", u.getId());
				pagina = "redirect:/pedidosPendientes";
			}
		}else {
			model.addAttribute("error", "login incorrecto");
		}
		
		return pagina;
	}
}
