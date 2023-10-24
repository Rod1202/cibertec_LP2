package org.cibertec.pe.controller;

import java.util.ArrayList;
import java.util.List;

import org.cibertec.pe.model.Detalle;
import org.cibertec.pe.model.Producto;
import org.cibertec.pe.repository.IDetalleRepository;
import org.cibertec.pe.repository.IProductoRepository;
import org.cibertec.pe.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"carrito","total"})
public class ProductoController {
	
	@ModelAttribute("carrito")
	public List<Detalle> getCarrito(){
		return new ArrayList<>();
	}
	
	@ModelAttribute("total")
	public double getTotal() {
		return 0.0;
	}
	
	@Autowired
	private IProductoRepository productoRepository;
	@Autowired
	private IVentaRepository ventaRepository;
	@Autowired
	private IDetalleRepository detalleRepository;
	
	@GetMapping("/index")
	public String listado(Model model) {
		List<Producto> lista = new ArrayList<>();
		lista= productoRepository.findAll();
		model.addAttribute("productos",lista);
		return "index";
	}
	
	// Método para agregar productos al carrito
	@GetMapping("/agregar/{idProducto}")
	public String agregar(Model model,@PathVariable(name="idProducto",required = true) int idProducto) {
		Producto p = productoRepository.findById(idProducto).orElse(null);
		List<Detalle> carrito = (List<Detalle>)model.getAttribute("carrito");
		double total = 0.0;
		boolean existe = false;
		Detalle detalle = new Detalle();
		if(p != null) {
			detalle.setProducto(p);
			detalle.setCantidad(1);
			detalle.setSubtotal(detalle.getProducto().getPrecio() * detalle.getCantidad());
		}
		// Si el carrito esta vacio
		if(carrito.size() == 0) {
			carrito.add(detalle);
		}else {
				for(Detalle d : carrito) {
					if(d.getProducto().getIdProducto() == p.getIdProducto()) {
						d.setCantidad(d.getCantidad() + 1);
						d.setSubtotal(d.getProducto().getPrecio() * d.getCantidad());
						existe = true;
					}
				}
				if(!existe)carrito.add(detalle);
		}

		for(Detalle d : carrito)total += d.getSubtotal();

		model.addAttribute("total", total);
		model.addAttribute("carrito", carrito);
		updateCarrito(model);
		return "redirect:/index";
	}


	    @PostMapping("/actualizar-carrito")
	    public String actualizarCarrito(Model model, @ModelAttribute("carrito") List<Detalle> carrito) {
	        updateCarrito(model);

	        return "redirect:/index";
	    }

	    @PostMapping("/realizar-pago")
	    public String realizarPago(Model model) {

	        double total = updateCarrito(model);

	        model.addAttribute("mensaje", "Pago procesado con éxito.");

	        model.addAttribute("carrito", new ArrayList<Detalle>());
	        model.addAttribute("total", 0.0);

	        return "redirect:/index";
	    }

	    private double updateCarrito(Model model) {
	        List<Detalle> carrito = (List<Detalle>) model.getAttribute("carrito");

	        double total = carrito.stream().mapToDouble(Detalle::getSubtotal).sum();

	        double descuento = calcularDescuento(total);

	        double envio = total * 0.075;

	        total -= descuento;
	        total += envio;

	        model.addAttribute("total", total);

	        return total;
	    }

	    private double calcularDescuento(double total) {
	        if (total >= 500 && total <= 1000) {
	            return total * 0.02;
	        } else if (total > 1000 && total <= 2000) {
	            return total * 0.03;
	        } else if (total > 2000 && total <= 3000) {
	            return total * 0.04;
	        } else if (total > 3000) {
	            return total * 0.05;
	        }
	        return 0.0;
	    }
	

	
	@GetMapping("/carrito")
	public String carrito() {
		return "carrito";
	}
}
