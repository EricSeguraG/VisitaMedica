package segura.eric.dam.mp09.uf01.pr2.seguretat.controller;

import segura.eric.dam.mp09.uf01.pr2.seguretat.model.domain.VisitaMedica;
import segura.eric.dam.mp09.uf01.pr2.seguretat.model.domain.VisitaMedicaLog;
import segura.eric.dam.mp09.uf01.pr2.seguretat.model.repository.VisitaMedicaRepository;
import segura.eric.dam.mp09.uf01.pr2.seguretat.model.service.VisitaMedicaService;

/**
 * Controlador de l'aplicació. Coordina la interacció entre la vista, el servei i el repositori.
 */
public class VisitaMedicaController {

    private final VisitaMedicaService service;
    private final VisitaMedicaRepository repository;

    public VisitaMedicaController() {
        this.service = new VisitaMedicaService();
        this.repository = new VisitaMedicaRepository();
    }

    /**
     * Processa les dades d'una VisitaMedica, valida, encripta/haxeja i la desa en JSON.
     * @param visita La visita a processar.
     * @return true si el procés s'ha completat amb èxit, false en cas contrari.
     */
    public boolean processarIDesarVisita(VisitaMedica visita) {
        if (!service.validarDades(visita)) {
            System.err.println("Procés avortat: La visita conté dades no informades.");
            return false;
        }

        // 1. Transformar i encriptar/haxejar a VisitaMedicaLog
        VisitaMedicaLog log = service.crearVisitaMedicaLog(visita);
        if (log == null) {
            System.err.println("Procés avortat: Error en la creació del Log de seguretat.");
            return false;
        }
        
        // 2. Guardar el log encriptat al repositori JSON
        return repository.save(log);
    }
    
    /**
     * Carrega les dades encriptades des del JSON i les desencripta.
     * @return L'objecte VisitaMedica desencriptat, o null si hi ha un error.
     */
    public VisitaMedica carregarIDesencriptarVisita() {
        // 1. Carregar el log encriptat des del repositori JSON
        VisitaMedicaLog log = repository.load();
        
        if (log == null) {
            return null;
        }
        
        // 2. Desencriptar i convertir a VisitaMedica
        return service.convertirLog(log);
    }

    /**
     * Mostra els hashs de verificació. Aquesta funció simula un procés de validació
     * on podríem comprovar si els hashs guardats coincideixen amb els hashs generats
     * a partir de la dada desencriptada (no implementat aquí, només mostra).
     * @param log El log amb els hashs guardats.
     */
    public void mostrarHashejat(VisitaMedicaLog log) {
        if (log == null) return;
        System.out.println("\n--- Dades de Hash (Per verificació) ---");
        System.out.println("Pacient MD5: " + log.getNomPacientMD5());
        System.out.println("Pacient SHA256: " + log.getNomPacientSHA256());
        System.out.println("Diagnòstic MD5: " + log.getDiagnòsticMD5());
        System.out.println("Diagnòstic SHA256: " + log.getDiagnòsticSHA256());
        System.out.println("-------------------------------------\n");
    }
}