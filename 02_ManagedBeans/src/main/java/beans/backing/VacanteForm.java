package beans.backing;

import beans.model.Candidato;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named //Notación Beans de CDI (Context and Dependency Injection)
@RequestScoped // Notación para indicar el scope asociado
public class VacanteForm {

    @Inject //Notación Beans de CDI para injectar para poder usar el Bean del Modelo
    private Candidato candidato;

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public String enviar() {
        if (this.candidato.getNombre().equals("Israel")) {
            /* Se usará como lógica de navegación el retorno de una cadena de
            texto que mediante navegación Explícita, se tratará como si fuese
            el nombre de la página xhtml para redirigir. */
            return "exito";
        } else {
            return "fallo";
        }
    }
}
