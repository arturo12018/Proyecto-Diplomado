package dgtic.core.proyecto.entity;


import java.util.HashMap;
import java.util.Map;

public class Carrito {
    private Map<Long,Integer> lista=new HashMap<>();

    public Map<Long, Integer> getLista() {
        return lista;
    }

    public void setLista(Long idProducto, int cantidad) {
        // Verificar si el producto ya está en el carrito
        if (lista.containsKey(idProducto)) {
            // Si el producto ya está en el carrito, simplemente actualiza la cantidad
            lista.put(idProducto, lista.get(idProducto) + cantidad);
        } else {
            // Si el producto no está en el carrito, agrégalo como un nuevo elemento
            lista.put(idProducto, cantidad);
        }
    }

    public void eliminarProducto(Long idProducto) {
        // Eliminar el producto del carrito
        lista.remove(idProducto);
    }

    public void actualizarLibro(Long isbn, Integer cantidad) {
        lista.put(isbn, cantidad);
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "lista=" + lista +
                '}';
    }
}
