package dgtic.core.proyecto.controller.compras;

import dgtic.core.proyecto.entity.Compra;
import dgtic.core.proyecto.entity.Editorial;
import dgtic.core.proyecto.service.compra.CompraService;
import dgtic.core.proyecto.util.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("compras")
public class ComprasController {

    @Autowired
    CompraService compraService;

    @GetMapping("lista-compras")
    public String listaCompras(@RequestParam(name="page",defaultValue = "0") int page, Model model){
        Pageable pagReq= PageRequest.of(page,10);
        Page<Compra> compra=compraService.findAll(pagReq);
        RenderPagina<Compra> render=new RenderPagina<>("lista-compras",compra);
        model.addAttribute("page",render);
        model.addAttribute("compra",compra);

        model.addAttribute("contenido","Lista Compras");
        return "compras/lista-compras";
    }
}
