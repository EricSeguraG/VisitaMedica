package segura.eric.dam.mp09.uf01.pr2.seguretat.model.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import segura.eric.dam.mp09.uf01.pr2.seguretat.model.domain.VisitaMedicaLog;

import java.io.File;
import java.io.IOException;

/**
 * Repositori per gestionar la persistència de VisitaMedicaLog en format JSON.
 * Utilitza la llibreria Jackson.
 */
public class VisitaMedicaRepository {

    private static final String FILE_PATH = "visita_medica.json";
    private final ObjectMapper objectMapper;

    public VisitaMedicaRepository() {
        this.objectMapper = new ObjectMapper();
        // Configuració per permetre desar objectes Date (LocalDate en el nostre cas)
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // Configuració per formatar el JSON de manera més llegible
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Guarda un objecte VisitaMedicaLog a un fitxer JSON.
     * @param log L'objecte VisitaMedicaLog a guardar.
     * @return true si l'operació ha estat exitosa, false en cas contrari.
     */
    public boolean save(VisitaMedicaLog log) {
        if (log == null) {
            System.err.println("No es pot guardar un objecte VisitaMedicaLog nul.");
            return false;
        }
        try {
            // Guarda l'objecte al fitxer
            objectMapper.writeValue(new File(FILE_PATH), log);
            System.out.println("✅ Visita mèdica guardada correctament a " + FILE_PATH);
            return true;
        } catch (IOException e) {
            System.err.println("❌ Error en guardar el fitxer JSON: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carrega un objecte VisitaMedicaLog des d'un fitxer JSON.
     * @return L'objecte VisitaMedicaLog carregat, o null si el fitxer no existeix o hi ha un error.
     */
    public VisitaMedicaLog load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("⚠️ Fitxer de dades no trobat. Es crearà un nou arxiu en guardar.");
            return null;
        }

        try {
            // Llegeix i retorna l'objecte des del fitxer
            return objectMapper.readValue(file, VisitaMedicaLog.class);
        } catch (IOException e) {
            System.err.println("❌ Error en carregar el fitxer JSON. El fitxer podria estar corrupte. " + e.getMessage());
            return null;
        }
    }
}