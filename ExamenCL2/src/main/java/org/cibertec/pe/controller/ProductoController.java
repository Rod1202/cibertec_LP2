package org.cibertec.pe.controller;

import org.cibertec.pe.model.Detalle;
import org.cibertec.pe.model.Producto;
import org.cibertec.pe.repository.IDetalleRepository;
import org.cibertec.pe.repository.IProductoRepository;
import org.cibertec.pe.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"carrito", "total"})
public class ProductoController {

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private IVentaRepository ventaRepository;
    @Autowired
    private IDetalleRepository detalleRepository;

    @GetMapping("/index")
    public String listado(Model model) {
        List<Producto> lista = productoRepository.findAll();
        model.addAttribute("productos", lista);
        return "index";
    }

    @ModelAttribute("carrito")
    public List<Detalle> initializeCarrito() {
        return new ArrayList<>();
    }

    @ModelAttribute("total")
    public double initializeTotal() {
        return 0.0;
    }

    @GetMapping("/agregar/{idProducto}")
    public String agregar(@PathVariable(name = "idProducto") int idProducto, Model model) {
        Producto producto = productoRepository.findById(idProducto).orElse(null);

        if (producto != null) {
            List<Detalle> carrito = (List<Detalle>) model.getAttribute("carrito");

            Detalle detalle = carrito.stream()
                    .filter(d -> d.getProducto().getIdProducto() == idProducto)
                    .findFirst()
                    .orElse(null);

            if (detalle == null) {
                detalle = new Detalle();
                detalle.setProducto(producto);
                detalle.setCantidad(1);
                carrito.add(detalle);
            } else {
                detalle.setCantidad(detalle.getCantidad() + 1);
            }

            detalle.setSubtotal(detalle.getProducto().getPrecio() * detalle.getCantidad());
            double total = carrito.stream().mapToDouble(Detalle::getSubtotal).sum();

            model.addAttribute("total", total);
        }

        return "redirect:/index";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String eliminarProducto(@PathVariable(name = "idProducto") int idProducto, Model model) {
        List<Detalle> carrito = (List<Detalle>) model.getAttribute("carrito");

        carrito.removeIf(detalle -> detalle.getProducto().getIdProducto() == idProducto);

        double total = carrito.stream().mapToDouble(Detalle::getSubtotal).sum();

        model.addAttribute("total", total);

        return "redirect:/carrito";
    }

    @PostMapping("/calcularEnvioYDescuento")
    public String calcularEnvioYDescuento(Model model) {
        double total = updateCarrito(model);

        double descuento = calcularDescuento(total);
        double envio = total * 0.075;

        model.addAttribute("descuento", descuento);
        model.addAttribute("envio", envio);

        return "carrito";
    }

    @PostMapping("/actualizarCarrito")
    public String actualizarCarrito(Model model, @ModelAttribute("carrito") List<Detalle> carrito) {
        double total = updateCarrito(model);
        return "redirect:/carrito";
    }

    @PostMapping("/realizarPago")
    public String realizarPago(Model model) {
        updateCarrito(model);

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

        model.addAttribute("total", total);
        model.addAttribute("descuento", descuento);
        model.addAttribute("envio", envio);

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
