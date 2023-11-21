package org.cibertec.pe.controller;

import org.cibertec.pe.model.Ciudad;
import org.cibertec.pe.model.DetalleVenta;
import org.cibertec.pe.model.Venta;
import org.cibertec.pe.model.VentaCompleta;
import org.cibertec.pe.repository.ICiudadRepository;
import org.cibertec.pe.repository.IDetalleRepository;
import org.cibertec.pe.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller

public class VentaController {

	@Autowired
    private ICiudadRepository ciudadRepository;

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IDetalleRepository detalleRepository;

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {

        List<Ciudad> ciudades = ciudadRepository.findAll();
        model.addAttribute("ciudades", ciudades);

        return "index";
    }

    @PostMapping("/agregarVenta")
    public String agregarVenta(
        Venta venta,
        HttpSession session,
        @RequestParam int codigo_postal_origen_id,
        @RequestParam int codigo_postal_destino_id,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_viaje,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_retorno,
        @RequestParam int cantidad
    ) {

        List<VentaCompleta> ventasEnSesion = (List<VentaCompleta>) session.getAttribute("ventas");

        if (ventasEnSesion == null) {
            ventasEnSesion = new ArrayList<>();
        }

        DetalleVenta detalleVenta = new DetalleVenta();

        venta.setFecha_venta(new Date());

        detalleVenta.setCodigo_postal_origen(ciudadRepository.findById(codigo_postal_origen_id).orElse(null));
        detalleVenta.setCodigo_postal_destino(ciudadRepository.findById(codigo_postal_destino_id).orElse(null));
        detalleVenta.setIdVenta(venta);
        detalleVenta.setFecha_viaje(fecha_viaje);
        detalleVenta.setFecha_retorno(fecha_retorno);
        detalleVenta.setCantidad(cantidad);
        double precioUnitario = 50; 
        double subTotal = cantidad * precioUnitario;
        detalleVenta.setSub_total(subTotal);

        VentaCompleta ventaCompleta = new VentaCompleta(venta, detalleVenta);
        ventasEnSesion.add(ventaCompleta);
        session.setAttribute("ventas", ventasEnSesion);
        return "redirect:/listado";
    }

    @PostMapping("/pagar")
    public String pagar(HttpSession session) {
        List<VentaCompleta> ventasEnSesion = (List<VentaCompleta>) session.getAttribute("ventas");

        if (ventasEnSesion != null && !ventasEnSesion.isEmpty()) {

            double montoTotal = ventasEnSesion.stream().mapToDouble(vc -> vc.getDetalleVenta().getSub_total()).sum();

            Venta venta = new Venta();
            venta.setNombre_comprador(ventasEnSesion.get(0).getVenta().getNombre_comprador()); // Supongo que todos tienen el mismo comprador
            venta.setFecha_venta(new Date());
            venta.setMonto_total(montoTotal);
            ventaRepository.save(venta);

            for (VentaCompleta ventaCompleta : ventasEnSesion) {
                ventaCompleta.getDetalleVenta().setIdVenta(venta);
                detalleRepository.save(ventaCompleta.getDetalleVenta());
            }

            session.removeAttribute("ventas");
        }

        return "redirect:/formulario";
    }
    
    @GetMapping("/listado")
    public String mostrarListado(Model model, HttpSession session) {     
        List<Venta> ventasEnSesion = (List<Venta>) session.getAttribute("ventas");
        model.addAttribute("ventas", ventasEnSesion);
        return "listado";
    }
}
