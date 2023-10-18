package org.cibertec.pe.controller;

import java.util.List;
import java.util.Optional;

import org.cibertec.pe.interfacesServices.IArticuloService;
import org.cibertec.pe.modelo.Articulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticulosController {

	@Autowired
	private IArticuloService servicio;
	
	//Metodo para listar
	@GetMapping("/listar")
	public String Listar(Model m) {
		List<Articulos> LArticulos = servicio.Listado();
		m.addAttribute("listArt",LArticulos);
		return "index";
	}
	
	//Metodo para buscar 
	@GetMapping("/ver/{IdArticulo}")
	public String ver(@PathVariable int IdArticulo, Model m) {
		Optional<Articulos> ObjA = servicio.ver(IdArticulo);
		m.addAttribute("articulos",ObjA);
		return "view";
	}
	
	//Metodo para agregar 
	@GetMapping("/nuevo")
	public String agrega(Model m) {
		m.addAttribute("articulos", new Articulos());
		return "form";
	}
	
	//Metodo para grabar 
	@PostMapping("/grabar")
	public String grabar(Articulos a, Model m ) {
		servicio.grabar(a);
		return "redirect:/listar";
	}
	
	//Metodo para editar 
	@GetMapping("/editar/{IdArticulo}")
	public String editar(@PathVariable int IdArticulo,Model m) {
		Optional<Articulos> ObjA = servicio.ver(IdArticulo);
		m.addAttribute("articulos",ObjA);
		return "edit";
	}
	
	@PostMapping("/eliminar/{IdArticulo}")
	public String eliminar(@PathVariable int IdArticulo,Model m) {
		servicio.suprimir(IdArticulo);
		return "redirect:/listar";
	}
	
	
}

