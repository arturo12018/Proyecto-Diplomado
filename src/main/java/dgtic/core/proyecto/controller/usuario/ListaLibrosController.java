package dgtic.core.proyecto.controller.usuario;


import dgtic.core.proyecto.entity.Carrito;
import dgtic.core.proyecto.entity.Libro;
import dgtic.core.proyecto.service.Libro.LibroService;
import dgtic.core.proyecto.service.compra.CompraService;
import dgtic.core.proyecto.util.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@SessionAttributes("carrito")
public class ListaLibrosController {

    @Autowired
    LibroService libroService;

    @Autowired
    CompraService compraService;




    @ModelAttribute("carrito")
    public Carrito initShoppingCart() {
        return new Carrito();
    }



    //--------------Pendiente
   @GetMapping(value = "buscar-libro/{dato}",produces = "application/json")
    public @ResponseBody List<String> buscarCliente(@PathVariable String dato){
        return libroService.busquedaPorPatron(dato);
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


        //IMportarnte compra, muestra el rol
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication.getAuthorities());*/


        model.addAttribute("page", render);
        model.addAttribute("libros", libros);
        return "principal";
    }

    @GetMapping("/agregarProductoCarritoMListado/{isbn}")
    public String agregarCarritoListado(@PathVariable Long isbn,@ModelAttribute("carrito") Carrito carrito, RedirectAttributes flash){

        // Agregar el producto al carrito
        carrito.setLista(isbn, 1);
        System.out.println(carrito);
        flash.addFlashAttribute("success","Se agrego al carrito");
        return "redirect:/principal";
    }

    @GetMapping("/detalles-libro-agregar/{isbn}")
    public String agregarCarritoDetalle(@PathVariable Long isbn,@ModelAttribute("carrito") Carrito carrito,Model model, RedirectAttributes flash){
        // Agregar el producto al carrito
        carrito.setLista(isbn, 1);
        Libro libro=libroService.buscarPorId(isbn);
        model.addAttribute("libro", libro);
        System.out.println(carrito);
        flash.addFlashAttribute("success","Se agrego al carrito");
        return "redirect:/detalles-libro/"+isbn;
    }


    @GetMapping("/detalles-libro/{isbn}")
    public String detalleLibro(@PathVariable Long isbn,Model model){
        Libro libro=libroService.buscarPorId(isbn);
        model.addAttribute("libro", libro);
        return "detalles-libro";
    }



    @PostMapping("/calificar-libro/{isbn}")
    public String calificarLibro(@PathVariable Long isbn, @ModelAttribute("calificacion") Integer calificacionNueva, Model model, RedirectAttributes flash){
        Libro libro=libroService.buscarPorId(isbn);
        Float califFinal=(calificacionNueva+libro.getValoracion())/ 2;
        libroService.actualizarValoracionLibro(isbn,califFinal);
        Libro libroActualizado=libroService.buscarPorId(isbn);
        model.addAttribute("libro", libroActualizado);
        flash.addFlashAttribute("success","Calificado correctamente");
        return "redirect:/detalles-libro/"+isbn;
    }


    @GetMapping("/carrito")
    public String carrito(Model model,@ModelAttribute("carrito") Carrito carrito){


       List<Libro> libroList=libroService.listadoLibro(carrito);

       Float total= compraService.total(carrito,libroList);


        model.addAttribute("total", total);
        model.addAttribute("carrito", carrito);
        model.addAttribute("libroList", libroList);
        return "carrito";
    }


    @PostMapping("/actualizar-carrito/{isbn}")
    public String actualizarCarrito(@PathVariable Long isbn,
                                    @RequestParam("numeroLib") Integer numeroLib,
                                    @ModelAttribute("carrito") Carrito carrito) {

        // Tu lógica para actualizar el carrito con el número de libros
        System.out.println(isbn);
        System.out.println(numeroLib);
        carrito.actualizarLibro(isbn, numeroLib);

        return "redirect:/carrito"; // O la vista que desees
    }


    @GetMapping("/eliminar-carrito/{isbn}")
    public String eliminarProductoCarrito(@PathVariable Long isbn,@ModelAttribute("carrito") Carrito carrito, RedirectAttributes flash){
        carrito.eliminarProducto(isbn);
        flash.addFlashAttribute("success","Se elimino del carrito");
        return "redirect:/carrito";
    }



    @GetMapping("/user/compras/datos-pago")
    public String detallesPago(Model model,@ModelAttribute("carrito") Carrito carrito){


        List<Libro> libroList=libroService.listadoLibro(carrito);

        Float total= compraService.total(carrito,libroList);


        model.addAttribute("total", total);
        model.addAttribute("carrito", carrito);
        model.addAttribute("libroList", libroList);
        return "user/compras/datos-pago";
    }







}
