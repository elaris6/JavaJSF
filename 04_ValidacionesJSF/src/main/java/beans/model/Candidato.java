package beans.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named //Notación Beans de CDI (context and Dependency Injection
@RequestScoped // Notación para indicar el scope asociado
public class Candidato {

    private String nombre;
    private String apellido;
    private double salarioDeseado;

    // Creación del objeto "log", que permitirá interactuar con el log.
    Logger log = LogManager.getRootLogger();

    public Candidato(){
        log.info("Creando el objeto Candidato");
        this.setNombre("Introduce tu nombre");
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        // Se escribe mensaje en el log a nivel info.
        log.info("Modificando la propiedad nombre: " + this.nombre);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
        log.info("Modificando la propiedad apellido: "+this.apellido);
    }

    public double getSalarioDeseado() {
        return salarioDeseado;
    }

    public void setSalarioDeseado(double salarioDeseado) {
        this.salarioDeseado = salarioDeseado;
        log.info("Modificando la propiedad salarioDeseado: "+this.salarioDeseado);
    }
}
