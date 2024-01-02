package dgtic.core.proyecto.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

public class ContraseniaNueva {

    private String antigua;


    @Size(min = 8,max = 60, message = "La contraseña debe ser entre 8 y 24 caracteres")
    private String nueva;


    public String getAntigua() {
        return antigua;
    }

    public void setAntigua(String antigua) {
        this.antigua = antigua;
    }

    public String getNueva() {
        return nueva;
    }

    public void setNueva(String nueva) {
        this.nueva = nueva;
    }

    public ContraseniaNueva() {
    }

    public ContraseniaNueva(String antigua, String nueva) {
        this.antigua = antigua;
        this.nueva = nueva;
    }
}
