package segura.eric.dam.mp09.uf01.pr2.seguretat.model.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entitat principal que representa una Visita Mèdica.
 */
public class VisitaMedica {

    private int idVisita; // [cite: 18]
    private String nomPacient; // [cite: 19]
    private String nomMetge; // [cite: 20]
    private LocalDate data; // [cite: 21]
    private String diagnòstic; // [cite: 22]

    // --- Constructors ---

    public VisitaMedica() {
    }

    public VisitaMedica(int idVisita, String nomPacient, String nomMetge, LocalDate data, String diagnòstic) {
        this.idVisita = idVisita;
        this.nomPacient = nomPacient;
        this.nomMetge = nomMetge;
        this.data = data;
        this.diagnòstic = diagnòstic;
    }

    // --- Getters i Setters ---

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public String getNomPacient() {
        return nomPacient;
    }

    public void setNomPacient(String nomPacient) {
        this.nomPacient = nomPacient;
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

    public String getDiagnòstic() {
        return diagnòstic;
    }

    public void setDiagnòstic(String diagnòstic) {
        this.diagnòstic = diagnòstic;
    }

    // --- Altres mètodes útils ---

    @Override
    public String toString() {
        return "VisitaMedica{" +
                "idVisita=" + idVisita +
                ", nomPacient='" + nomPacient + '\'' +
                ", nomMetge='" + nomMetge + '\'' +
                ", data=" + data +
                ", diagnòstic='" + diagnòstic + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitaMedica that = (VisitaMedica) o;
        return idVisita == that.idVisita;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVisita);
    }
}