package beans.backing;

import beans.model.Candidato;
import javax.enterprise.context.RequestScoped;
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

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public String enviar() {
        if (this.candidato.getNombre().equals("Israel")) {
            /* Se usará como lógica de navegación el retorno de una cadena de
            texto que mediante navegación Explícita, se tratará como si fuese
            el nombre de la página xhtml para redirigir. */
            // Se escribe mensaje en el log a nivel de info.
            log.info("Entrando al caso de exito");
            return "exito";
        } else {
            // Se escribe mensaje en el log a nivel de info.
            log.info("Entrando al caso de fallo");
            return "fallo";
        }
    }
}
