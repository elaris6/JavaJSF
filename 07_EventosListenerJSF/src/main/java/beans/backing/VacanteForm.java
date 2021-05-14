package beans.backing;

import beans.model.Candidato;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named //Notación Beans de CDI (Context and Dependency Injection)
@RequestScoped // Notación para indicar el scope asociado
public class VacanteForm {

    @Inject //Notación Beans de CDI para injectar para poder usar el Bean del Modelo
    private Candidato candidato;

    private boolean comentarioEnviado;

    // Creación del objeto "log", que permitirá interactuar con el log.
    Logger log = LogManager.getRootLogger();

    public VacanteForm() {
        log.info("Creando objeto VacanteForm");
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public String enviar() {
        if (this.candidato.getNombre().equals("Israel")) {
            /* Se hace una comprobación adicional de ejemplo, para sacar un mensaje de error en ese caso. */
            if (this.candidato.getApellido().equals("Balboa")) {
                /* Se define un mensaje de error, se obtiene el contexto actual y se asigna a un componente
                en caso de seado. Finalmente con el método "addMessage", se añade el mensaje. */
                String msg = "Ya existe un empleado Israel Balboa.";
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);

                /* Para definir el idioma de los mensajes se podría especificar a nivel global en el fichero
                de configuración "faces-config.xml", o directamente al generarlos, por ejemplo en esta misma
                clase, mediante la siguiente instrucción:
                "FacesContext.getCurrentInstance().getViewRoot().setLocale("es_US");" */
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String componentId = null;
                /* En este atributo se asignaría el mensaje a un componente, pero en este caso será global. */
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

    public void codigoPostalListener(ValueChangeEvent valueChangeEvent) {

        /* Se declara una variable de tipo "facesContext" para gestionar la instancia en curso. */
        FacesContext facesContext = FacesContext.getCurrentInstance();

        /* Se define una variable de tipo "UIViewRoot" para poder acceder a un componente dentro
        del formulario JSF. Para ello tendremos que asignar un ID a cada uno de los composnentes. */
        UIViewRoot uiViewRoot = facesContext.getViewRoot();

        /* Se obtiene el nuevo valor del objeto listener asignado al componente, que en este
        ejemplo ya se sabe que es un cambio en el InputText de código postal. */
        String nuevoCodigoPostal = (String) valueChangeEvent.getNewValue();

        /* Se añade lógica de ejemplo en la aplicación. En este caso comprobar si el CP es uno concreto.
        Lo normal en una aplicación real sería recuperarlo de una servicio o de una BBDDD. */
        if (nuevoCodigoPostal.equals("28703")) {
            /* Se recupera el componente con ID de provincia del formulario mediante el siguiente
            método y clase. En este caso se usa la clase UIInput pues el tipo de componente a
            recuperar es un componente de tipo "InputText". */
            UIInput provinciaInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:provincia");

            /* Se define un nuevo valor para el campo provincia del formulario en base al CP detectado.
            Se asigna el nuevo valro al componente y se requiere adicionalmente forzar el refresco del
            mismo con el método "setSubmittedValue()", para que se aprecien los cambios. */
            String nuevaProvincia = "Madrid";
            provinciaInputText.setValue(nuevaProvincia);
            provinciaInputText.setSubmittedValue(nuevaProvincia);

            /* Se repite la misma operación para el componente del formulario de la cuidad. */
            UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:ciudad");
            String nuevaCiudad = "San Sebasti´na de los Reyes";
            ciudadInputText.setValue(nuevaCiudad);
            ciudadInputText.setSubmittedValue(nuevaCiudad);

            // Se envían los cambios a la vista
            facesContext.renderResponse();
        }
    }
    
    public void ocultarComentario(ActionEvent actionEvent){
        this.comentarioEnviado = !this.comentarioEnviado;
    }

    public boolean isComentarioEnviado() {
        return comentarioEnviado;
    }

    public void setComentarioEnviado(boolean comentarioEnviado) {
        this.comentarioEnviado = comentarioEnviado;
    }

}
