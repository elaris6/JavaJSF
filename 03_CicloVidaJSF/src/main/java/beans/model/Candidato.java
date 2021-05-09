package beans.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named //Notación Beans de CDI (context and Dependency Injection
@RequestScoped // Notación para indicar el scope asociado
public class Candidato {

    private String nombre = "Introduce tu nombre";

    // Creación del objeto "log", que permitirá interactuar con el log.
    Logger log = LogManager.getRootLogger();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        // Se escribe mensaje en el log a nivel info.
        log.info("Modificando la propiedad nombre: " + this.nombre);
    }

}
