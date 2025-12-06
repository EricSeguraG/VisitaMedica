package segura.eric.dam.mp09.uf01.pr2.seguretat.model.service;

import segura.eric.dam.mp09.uf01.pr2.seguretat.model.domain.VisitaMedica;
import segura.eric.dam.mp09.uf01.pr2.seguretat.model.domain.VisitaMedicaLog;

/**
 * Servei de lògica de negoci per a VisitaMedica.
 * Inclou validacions i la transformació d'entitats (encriptació/hash).
 */
public class VisitaMedicaService {

    /**
     * Valida que cap de les propietats clau d'una VisitaMedica sigui nul·la o buida.
     * @param visita La VisitaMedica a validar.
     * @return true si la visita és vàlida, false en cas contrari.
     */
    public boolean validarDades(VisitaMedica visita) {
        if (visita == null) {
            System.err.println("Error de validació: La visita és nul·la.");
            return false;
        }
        if (visita.getNomPacient() == null || visita.getNomPacient().trim().isEmpty()) {
            System.err.println("Error de validació: El nom del pacient no pot estar buit.");
            return false;
        }
        if (visita.getNomMetge() == null || visita.getNomMetge().trim().isEmpty()) {
            System.err.println("Error de validació: El nom del metge no pot estar buit.");
            return false;
        }
        if (visita.getData() == null) {
            System.err.println("Error de validació: La data no pot ser nul·la.");
            return false;
        }
        if (visita.getDiagnòstic() == null || visita.getDiagnòstic().trim().isEmpty()) {
            System.err.println("Error de validació: El diagnòstic no pot estar buit.");
            return false;
        }
        // Totes les dades estan informades
        return true;
    }

    /**
     * Transforma una VisitaMedica a VisitaMedicaLog aplicant tots els algorismes de seguretat.
     * @param visita L'entitat amb les dades originals.
     * @return VisitaMedicaLog amb els camps encriptats/hashejats.
     */
    public VisitaMedicaLog crearVisitaMedicaLog(VisitaMedica visita) {
        if (!validarDades(visita)) {
            return null; // No crear Log si no és vàlida
        }

        // 1. Encriptació AES (bidireccional) per als camps sensibles
        String nomPacientAES = AESEncryptor.encrypt(visita.getNomPacient());
        String diagnòsticAES = AESEncryptor.encrypt(visita.getDiagnòstic());

        // 2. Hash MD5 i SHA256 (unidireccional) per verificació
        String nomPacientMD5 = MD5Encryptor.encrypt(visita.getNomPacient());
        String nomPacientSHA256 = SHA256Encryptor.encrypt(visita.getNomPacient());
        String diagnòsticMD5 = MD5Encryptor.encrypt(visita.getDiagnòstic());
        String diagnòsticSHA256 = SHA256Encryptor.encrypt(visita.getDiagnòstic());

        // 3. Creació de l'objecte Log
        return new VisitaMedicaLog(
                visita.getIdVisita(),
                nomPacientAES,
                diagnòsticAES,
                visita.getNomMetge(),
                visita.getData(),
                nomPacientMD5,
                nomPacientSHA256,
                diagnòsticMD5,
                diagnòsticSHA256
        );
    }
    
    /**
     * Converteix un VisitaMedicaLog a l'entitat original (VisitaMedica) desencriptant els camps.
     * @param log L'entitat Log amb les dades encriptades.
     * @return L'entitat VisitaMedica original.
     */
    public VisitaMedica convertirLog(VisitaMedicaLog log) {
         if (log == null) {
            return null;
        }
        
        // Desencriptació AES
        String nomPacientOriginal = AESEncryptor.decrypt(log.getNomPacientAES());
        String diagnosticOriginal = AESEncryptor.decrypt(log.getDiagnòsticAES());
        
        // Retornar l'entitat original
        return new VisitaMedica(
            log.getIdVisita(),
            nomPacientOriginal,
            log.getNomMetge(),
            log.getData(),
            diagnosticOriginal
        );
    }

}