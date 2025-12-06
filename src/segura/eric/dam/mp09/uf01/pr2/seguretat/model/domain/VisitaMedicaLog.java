package segura.eric.dam.mp09.uf01.pr2.seguretat.model.domain;

import java.time.LocalDate;

/**
 * Entitat utilitzada per a la persistència JSON,
 * ja que conté els camps clau encriptats.
 */
public class VisitaMedicaLog {

    private int idVisita;
    // Aquests camps es guardaran encriptats amb AES.
    private String nomPacientAES;
    private String diagnòsticAES;

    // Aquests camps no s'encripten.
    private String nomMetge;
    private LocalDate data;

    // Camps de Hash per guardar els hashs de verificació.
    private String nomPacientMD5;
    private String nomPacientSHA256;
    private String diagnòsticMD5;
    private String diagnòsticSHA256;

    // --- Constructors, Getters i Setters ---
    // (Pots generar-los automàticament al teu IDE)

    public VisitaMedicaLog() {
    }

    // Constructor per facilitar la creació des d'una VisitaMedica i les dades encriptades
    public VisitaMedicaLog(int idVisita, String nomPacientAES, String diagnòsticAES, String nomMetge, LocalDate data, String nomPacientMD5, String nomPacientSHA256, String diagnòsticMD5, String diagnòsticSHA256) {
        this.idVisita = idVisita;
        this.nomPacientAES = nomPacientAES;
        this.diagnòsticAES = diagnòsticAES;
        this.nomMetge = nomMetge;
        this.data = data;
        this.nomPacientMD5 = nomPacientMD5;
        this.nomPacientSHA256 = nomPacientSHA256;
        this.diagnòsticMD5 = diagnòsticMD5;
        this.diagnòsticSHA256 = diagnòsticSHA256;
    }
    
    // ... (resta de getters i setters) ...
    // A continuació només s'inclouen els necessaris per claredat, genera la resta.

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public String getNomPacientAES() {
        return nomPacientAES;
    }

    public void setNomPacientAES(String nomPacientAES) {
        this.nomPacientAES = nomPacientAES;
    }

    public String getDiagnòsticAES() {
        return diagnòsticAES;
    }

    public void setDiagnòsticAES(String diagnòsticAES) {
        this.diagnòsticAES = diagnòsticAES;
    }

    public String getNomMetge() {
        return nomMetge;
    }

    public void setNomMetge(String nomMetge) {
        this.nomMetge = nomMetge;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNomPacientMD5() {
        return nomPacientMD5;
    }

    public void setNomPacientMD5(String nomPacientMD5) {
        this.nomPacientMD5 = nomPacientMD5;
    }

    public String getNomPacientSHA256() {
        return nomPacientSHA256;
    }

    public void setNomPacientSHA256(String nomPacientSHA256) {
        this.nomPacientSHA256 = nomPacientSHA256;
    }

    public String getDiagnòsticMD5() {
        return diagnòsticMD5;
    }

    public void setDiagnòsticMD5(String diagnòsticMD5) {
        this.diagnòsticMD5 = diagnòsticMD5;
    }

    public String getDiagnòsticSHA256() {
        return diagnòsticSHA256;
    }

    public void setDiagnòsticSHA256(String diagnòsticSHA256) {
        this.diagnòsticSHA256 = diagnòsticSHA256;
    }
}