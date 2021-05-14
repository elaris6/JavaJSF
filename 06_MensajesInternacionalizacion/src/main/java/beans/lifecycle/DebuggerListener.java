package beans.lifecycle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

/* Clase para gestionar el logging de eventos con log4j */
public class DebuggerListener implements javax.faces.event.PhaseListener {

    // Creación del objeto "log", que permitirá interactuar con el log.
    Logger log = LogManager.getRootLogger();

    @Override
    public void beforePhase(PhaseEvent pe) {
        if (log.isInfoEnabled()) {
            log.info("Antes de la fase: " + pe.getPhaseId().toString());
        }
    }

    @Override
    public void afterPhase(PhaseEvent pe) {
        if (log.isInfoEnabled()) {
            // Se recomienda omitir acentos en los mensajes de log
            log.info("Despues de la fase: " + pe.getPhaseId().toString());
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
