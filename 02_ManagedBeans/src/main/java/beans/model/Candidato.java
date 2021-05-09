package beans.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named //Notación Beans de CDI (context and Dependency Injection
@RequestScoped // Notación para indicar el scope asociado
public class Candidato {

    private String nombre = "Introduce tu nombre";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
