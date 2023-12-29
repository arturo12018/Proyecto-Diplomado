package dgtic.core.proyecto.controller.usuario;


import dgtic.core.proyecto.entity.Carrito;
import dgtic.core.proyecto.entity.Libro;
import dgtic.core.proyecto.service.Libro.LibroService;
import dgtic.core.proyecto.util.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@SessionAttributes("carrito")
public class ListaLibrosController {

    @Autowired
    LibroService libroService;

    @ModelAttribute("carrito")
    public Carrito initShoppingCart() {
        return new Carrito();
    }



    //--------------Pendiente
   @GetMapping(value = "buscar-libro/{dato}",produces = "application/json")
    public @ResponseBody List<String> buscarCliente(@PathVariable String dato){
        return libroService.busquedaPorPatron(dato);
    }

    @GetMapping("/agregarProductoCarritoMListado/{isbn}")
    public String agregarCarritoListado(@PathVariable Long isbn,@ModelAttribute("carrito") Carrito carrito, RedirectAttributes flash){

        // Agregar el producto al carrito
        carrito.setLista(isbn, 1);
        System.out.println(carrito);
        flash.addFlashAttribute("success","Se agrego al carrito");
        return "redirect:/principal";
    }





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
