package dgtic.core.proyecto.controller.usuario;


import dgtic.core.proyecto.entity.Administrador;
import dgtic.core.proyecto.entity.Libro;
import dgtic.core.proyecto.service.Libro.LibroService;
import dgtic.core.proyecto.util.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ListaLibrosController {

    @Autowired
    LibroService libroService;

/*    @GetMapping("/principal")
    public String principal(@RequestParam(name="page",defaultValue = "0") int page, Model model){
        Pageable pagReq= PageRequest.of(page,3);
        Page<Libro> libros=libroService.findAll(pagReq);
        RenderPagina<Libro> render=new RenderPagina<>("principal",libros);
        model.addAttribute("page",render);
        model.addAttribute("libros",libros);
        return "principal";
    }*/


/*   @GetMapping(value = "buscar-libro/{dato}",produces = "application/json")
    public @ResponseBody List<Libro> buscarCliente(@PathVariable String dato){
        return libroService.busquedaPorPatron(dato);
    }*/

    @GetMapping("/principal")
    public String principal(@RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="titulo", required = false) String titulo, Model model){
        Pageable pagReq = PageRequest.of(page, 12);
        Page<Libro> libros;
        RenderPagina<Libro> render;

        if(titulo != null && !titulo.isEmpty()){
            libros = libroService.busquedaPorTitulo(titulo, pagReq);
                render= new RenderPagina<>("principal?titulo="+titulo, libros);
        } else {
            libros = libroService.findAll(pagReq);
            render= new RenderPagina<>("principal", libros);
        }


        model.addAttribute("page", render);
        model.addAttribute("libros", libros);
        return "principal";
    }





}
