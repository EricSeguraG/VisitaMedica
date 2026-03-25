package eric.segura.joc3d; 

import java.util.Random;

public class EventJoc implements Runnable { 
	
	private String nomEvent;
	private Integer maximSegonsEnPantalla;
	private int maxAmplePantalla;
	private int maxAlturaPantalla;
	private int midaProfunditatPantalla; // Requisito añadido

	// Constructor actualizado con el 5º parámetro
	public EventJoc(String nomEvent, Integer maximSegonsEnPantalla, int maxAmplePantalla, int maxAlturaPantalla, int midaProfunditatPantalla) {
		this.nomEvent = nomEvent;
		this.maximSegonsEnPantalla = maximSegonsEnPantalla;
		this.maxAmplePantalla = maxAmplePantalla;
		this.maxAlturaPantalla = maxAlturaPantalla;
		this.midaProfunditatPantalla = midaProfunditatPantalla;
	}

	@Override
	public void run() {
		aparicions();
	}

	public void aparicions() {
		int segonsEnPantallaActuals = 0;
		int x = 0;
		int y = 0;
		int z = 0; // Añadimos la variable Z

		while (maximSegonsEnPantalla == null || segonsEnPantallaActuals <= maximSegonsEnPantalla) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			
			x = getPosicio(maxAmplePantalla, (maxAmplePantalla) * -1);
			y = getPosicio(maxAlturaPantalla, (maxAlturaPantalla) * -1);
			z = getPosicio(midaProfunditatPantalla, (midaProfunditatPantalla) * -1); // Generamos Z
			
			// Imprimimos la posición 3D completa
			System.out.println(nomEvent + " X:" + x + " Y:" + y + " Z:" + z); 
			segonsEnPantallaActuals++;
		}
	}

	private int getPosicio(int max, int min) {
		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}
}