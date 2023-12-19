package dgtic.core.proyecto.controller.admin.compras;

import dgtic.core.proyecto.entity.*;
import dgtic.core.proyecto.repository.CompraLibroRepository;
import dgtic.core.proyecto.service.compra.CompraService;
import dgtic.core.proyecto.util.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("admin/compras")
public class ComprasController {

    @Autowired
    CompraService compraService;

    @Autowired
    CompraLibroRepository compraLibroRepository;

    @GetMapping("lista-compras")
    public String listaCompras(@RequestParam(name="page",defaultValue = "0") int page, Model model){
        Pageable pagReq= PageRequest.of(page,10);
        Page<Compra> compra=compraService.findAll(pagReq);
        RenderPagina<Compra> render=new RenderPagina<>("lista-compras",compra);
        model.addAttribute("page",render);
        model.addAttribute("compra",compra);

        model.addAttribute("contenido","Lista Compras");
        return "admin/compras/lista-compras";
    }

    @GetMapping("detalle-compra/{id}")
    public String saltoModificar(@PathVariable("id") Integer id, Model model) {
        Compra compra = compraService.buscarPorId(id);
        List<CompraLibro> compraLibros=compraLibroRepository.findById_IdCompra(id);
        model.addAttribute("compraLibros", compraLibros);
        model.addAttribute("compra", compra);
        model.addAttribute("contenido","Detalle Compra");
        return "admin/compras/detalle-compra";
    }

}
