import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.FilenameFilter;

public class Logica {

	private PApplet app;

	int play = 0, modisize = 0, tam = 0, rotar = 0, y = 0;
	boolean aumento = true, full = false;
	private PImage fondo;
	private PImage[] ima = new PImage[20];
	private PImage image;

	// ======================================================================================================================

	public Logica(PApplet app) {
		this.app = app;
		// file que conecta a la carpeta
		File carpeta = new File("../data/imagenes");

		// arreglo con los tipos de archivos
		String[] tipos = new String[] { "jpg", "png", "bmp" };

		// identifica las extensiones de las imagenes
		FilenameFilter filtroIma = new FilenameFilter() {

			public boolean accept(File carpeta, String nombre) {
				for (final String ext : tipos) {
					if (nombre.endsWith("." + ext)) {
						return true;
					}
				}
				return false;
			}
		};

		// arreglo de tipo File que guarda la ubicacion de las imagenes
		File[] archivos = carpeta.listFiles(filtroIma);

		// Me carga las imagenes
		for (int i = 0; i < archivos.length; i++) {
			ima[i] = app.loadImage(archivos[i].toString());
		}

		fondo = app.loadImage("../data/interfaz/interfaz.png");

		image = ima[play].get();
		image.resize(412, 241);
	}

	// ======================================================================================================================

	public void pintar() {
		app.image(fondo, 0, 0);

		// carga la imagen del visualizador

		ima[play].resize(412 + modisize, 241 + modisize);

		app.pushMatrix();
		app.imageMode(app.CENTER);
		app.translate(670, 234);
		app.rotate(app.radians(rotar));
		app.image(image, 0, 0);
		app.imageMode(app.CORNER);
		app.popMatrix();

		if (full) {
			app.image(image, 0, 0);
		}
	}

	// ======================================================================================================================

	public void mouse() {

		// Adelante en el visualizador
		if (app.mouseX < 771 && app.mouseX > 736 && app.mouseY < 445 && app.mouseY > 403) {
			play += 1;
			tam = 0;

		} else
		// Atras en el visualizador
		if (app.mouseX < 609 && app.mouseX > 575 && app.mouseY < 445 && app.mouseY > 406) {
			play -= 1;
			tam = 0;
		}

		if (play > 19) {
			play = 0;

		}
		if (play < 0) {
			play = 19;
		}
		image = ima[play].get();
		image.resize(412, 241);

		if (full)
			full = false;
		System.out.println("Pos X " + app.mouseX + " pos Y " + app.mouseY);
		// System.out.println(tam);
		// System.out.println(rotar);

	}

	// ======================================================================================================================

	public void aumentar() {

		if (modisize >= 100) {
			aumento = false;
		} else if (modisize <= -180) {
			aumento = false;
		}

		if (aumento == true) {
			if (app.mouseX < 911 && app.mouseX > 871 && app.mouseY < 442 && app.mouseY > 402 && tam < 80) {
				tam += 5;
				y -= 5;
			}

			if (app.mouseX < 839 && app.mouseX > 800 && app.mouseY < 442 && app.mouseY > 402 && tam > -170) {
				tam -= 5;
				y += 5;
			}

			image = ima[play].get();
			image.resize(412, 241);
			image.resize(image.width + tam, image.height + tam);
		}
	}

	// ======================================================================================================================

	public void rotar() {

		if (app.mouseX < 471 && app.mouseX > 429 && app.mouseY < 444 && app.mouseY > 405) {
			rotar -= 90;
		}

		if (app.mouseX < 543 && app.mouseX > 501 && app.mouseY < 442 && app.mouseY > 406) {
			rotar += 90;
		}

	}

	// ======================================================================================================================

	public void fullScreen() {
		if (app.mouseX < 692 && app.mouseX > 653 && app.mouseY < 444 && app.mouseY > 406) {
			full = !full;

			image = ima[play].get();
			image.resize(412, 241);
			image.resize(1004, 604);

		}
	}

}