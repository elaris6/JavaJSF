package beans.backing;

import beans.model.Candidato;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named //Notación Beans de CDI (Context and Dependency Injection)
@RequestScoped // Notación para indicar el scope asociado
public class VacanteForm {

    @Inject //Notación Beans de CDI para injectar para poder usar el Bean del Modelo
    private Candidato candidato;

    // Creación del objeto "log", que permitirá interactuar con el log.
    Logger log = LogManager.getRootLogger();

    public VacanteForm(){
        log.info("Creando objeto VacanteForm");
    }
    
    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public String enviar() {
        if (this.candidato.getNombre().equals("Israel")) {
            /* Se hace una comprobación adicional de ejemplo, para sacar un mensaje de error en ese caso. */
            if (this.candidato.getApellido().equals("Balboa")){
                /* Se define un mensaje de error, se obtiene el contexto actual y se asigna a un componente
                en caso de seado. Finalmente con el método "addMessage", se añade el mensaje. */
                String msg = "Ya existe un empleado Israel Balboa.";
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
                
                /* Para definir el idioma de los mensajes se podría especificar a nivel global en el fichero
                de configuración "faces-config.xml", o directamente al generarlos, por ejemplo en esta misma
                clase, mediante la siguiente instrucción:
                "FacesContext.getCurrentInstance().getViewRoot().setLocale("es_US");" */
                
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String componentId = null; /* En este atributo se asignaría el mensaje a un componente, pero en este caso será global. */
                facesContext.addMessage(componentId, facesMessage);
                return "index";                
            }
            // Se escribe mensaje en el log a nivel de info.
            log.info("Entrando al caso de exito");
            /* Se usará como lógica de navegación el retorno de una cadena de
            texto que mediante navegación Explícita, se tratará como si fuese
            el nombre de la página xhtml para redirigir. */
            return "exito";
        } else {
            // Se escribe mensaje en el log a nivel de info.
            log.info("Entrando al caso de fallo");
            return "fallo";
        }
    }
}
