package dgtic.core.proyecto.controller.usuario.compras;


import com.fasterxml.jackson.annotation.JsonFormat;
import dgtic.core.proyecto.entity.*;
import dgtic.core.proyecto.repository.CompraLibroRepository;
import dgtic.core.proyecto.service.Libro.LibroService;
import dgtic.core.proyecto.service.compra.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user/compras")
public class ComprasUserController {

    @Autowired
    CompraService compraService;

    @Autowired
    CompraLibroRepository compraLibroRepository;





    @GetMapping("destalle-compra/{id}")
    public String detalleCompra(@PathVariable("id") Integer id, Model model) {
        Compra compra = compraService.buscarPorId(id);
        List<CompraLibro> compraLibros=compraLibroRepository.findById_IdCompra(id);
        model.addAttribute("compraLibros", compraLibros);
        model.addAttribute("compra", compra);
        model.addAttribute("contenido","Detalle Compra");
        return "user/compras/destalle-compra";
    }
    







}
