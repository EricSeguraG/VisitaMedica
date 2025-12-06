package segura.eric.dam.mp09.uf01.pr2.seguretat.view.console;

import segura.eric.dam.mp09.uf01.pr2.seguretat.controller.VisitaMedicaController;
import segura.eric.dam.mp09.uf01.pr2.seguretat.model.domain.VisitaMedica;
import segura.eric.dam.mp09.uf01.pr2.seguretat.model.domain.VisitaMedicaLog;
import segura.eric.dam.mp09.uf01.pr2.seguretat.model.repository.VisitaMedicaRepository;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Vista de consola per interacció amb l'usuari.
 */
public class VisitaMedicaConsoleView {

    private final VisitaMedicaController controller;
    private final Scanner scanner;

    public VisitaMedicaConsoleView() {
        this.controller = new VisitaMedicaController();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int opcio;
        do {
            mostrarMenu();
            opcio = llegirOpcio();

            switch (opcio) {
                case 1:
                    crearIDesarVisita();
                    break;
                case 2:
                    carregarIVisualitzarVisita();
                    break;
                case 0:
                    System.out.println("👋 Sortint de l'aplicació. Fins aviat!");
                    break;
                default:
                    System.out.println("⚠️ Opció no vàlida. Torna a intentar-ho.");
            }
        } while (opcio != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n================================================");
        System.out.println("   🔐 Gestió de Visites Mèdiques (JSON/Crypto)");
        System.out.println("================================================");
        System.out.println("1. Crear, Encriptar, Hashejar i Desar Visita (JSON)");
        System.out.println("2. Carregar, Desencriptar i Visualitzar Visita");
        System.out.println("0. Sortir");
        System.out.println("================================================");
        System.out.print("Tria una opció: ");
    }

    private int llegirOpcio() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Opció no vàlida
        }
    }

    private void crearIDesarVisita() {
        System.out.println("\n--- Nova Visita Mèdica ---");
        
        // Simulem dades per la tasca. Podries demanar-les a l'usuari amb el scanner
        // Per simplicitat i evitar la complexitat de tractar les dates per consola, les hardcodegem
        VisitaMedica novaVisita = new VisitaMedica(
            1, // IdVisita
            "Pau Gasol", // Nom Pacient (S'encripta)
            "Dr. Joan Gordi", // Nom Metge (No s'encripta)
            LocalDate.now(),
            "Fractura de l'escafoide amb necessitat d'immobilització." // Diagnòstic (S'encripta)
        );
        
        System.out.println("Dades originals a processar:");
        System.out.println(novaVisita.toString());
        
        if (controller.processarIDesarVisita(novaVisita)) {
            System.out.println("✅ Visita processada i desada amb èxit!");
        } else {
            System.err.println("❌ La visita NO s'ha pogut desar.");
        }
    }

    private void carregarIVisualitzarVisita() {
        System.out.println("\n--- Carregant i Desencriptant Dades ---");
        
        // Per visualitzar les dades encriptades que es guarden al JSON:
        VisitaMedicaRepository tempRepository = new VisitaMedicaRepository();
        VisitaMedicaLog logCarregat = tempRepository.load();

        if (logCarregat != null) {
            System.out.println("\n[Dades Encriptades/Hashejades carregades des del JSON (VisitaMedicaLog)]");
            System.out.println("Id: " + logCarregat.getIdVisita());
            System.out.println("Metge: " + logCarregat.getNomMetge());
            System.out.println("Data: " + logCarregat.getData());
            System.out.println("Pacient AES: " + logCarregat.getNomPacientAES());
            System.out.println("Diagnòstic AES: " + logCarregat.getDiagnòsticAES());
            
            // Mostrem els hashs
            controller.mostrarHashejat(logCarregat);
            
            // Desencriptem per obtenir la dada original
            VisitaMedica visitaDesencriptada = controller.carregarIDesencriptarVisita();
            
            if (visitaDesencriptada != null) {
                System.out.println("\n[Dades Desencriptades (VisitaMedica Original)]");
                System.out.println(visitaDesencriptada.toString());
                System.out.println("✅ Desencriptació exitosa.");
            } else {
                System.err.println("❌ Error en la desencriptació. La clau pot ser incorrecta o el fitxer JSON invàlid.");
            }
        } else {
            System.out.println("No hi ha dades guardades per carregar.");
        }
    }
}