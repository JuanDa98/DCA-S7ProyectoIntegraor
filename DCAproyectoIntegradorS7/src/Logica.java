import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.FilenameFilter;

public class Logica {

	private PApplet app;

	int play = 0, modisize = 0, tam = 0, rotar = 0, y = 0;
	//int numIma = 4, posY = 80, var = 0;
	boolean aumento = true, full = false;
	private PImage fondo, image;
	private PImage[] ima = new PImage[20];
	private PImage[] imaPeq = new PImage[20];

	// JuanDavid======================================================================================================================

	public Logica(PApplet app) {
		this.app = app;
		// File que conecta a la carpeta
		File carpeta = new File("../data/imagenes");

		// Arreglo con los tipos de archivos
		String[] tipos = new String[] { "jpg", "png", "bmp" };

		// Identifica las extensiones de las imagenes
		FilenameFilter analiza = new FilenameFilter() {
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
		File[] archivos = carpeta.listFiles(analiza);

		// Me carga las imagenes
		for (int i = 0; i < archivos.length; i++) {
			ima[i] = app.loadImage(archivos[i].toString());
			imaPeq[i] = app.loadImage(archivos[i].toString());
		}
		// Carga el fondo
		fondo = app.loadImage("../data/interfaz/interfaz.png");

//		// recorre y escala las imagenes pequeñas
//		for (int i = var; i < imaPeq.length; i++) {
//			imaPeq[i].resize(120, 80);
//		}

		// Escala las imagenes
		image = ima[play].get();
		image.resize(412, 241);
	}

	// JuanDavid======================================================================================================================

	public void pintar() {
		// Carga la imagen de fondo
		app.image(fondo, 0, 0);

		// Carga la imagen del visualizador
		ima[play].resize(412 + modisize, 241 + modisize);

//		// carga la lista de imagenes pequeñas
//		for (int i = var; i < numIma; i++) {
//			app.image(imaPeq[i], 120, 120 * i + posY);
//		}

		app.pushMatrix();
		app.imageMode(app.CENTER);
		app.translate(670, 234);
		app.rotate(app.radians(rotar));
		app.image(image, 0, 0);
		app.imageMode(app.CORNER);
		app.popMatrix();

		// me posiciona la imagen para el fullscreen
		if (full) {
			app.image(image, 0, 0);
		}
	}

	// JuanDavid======================================================================================================================

	public void mouse() {

		// Adelante en el visualizador
		if (app.mouseX < 771 && app.mouseX > 736 && app.mouseY < 445 && app.mouseY > 403) {
			play += 1;
			tam = 0;
			rotar = 0;

		} else
		// Atras en el visualizador
		if (app.mouseX < 609 && app.mouseX > 575 && app.mouseY < 445 && app.mouseY > 406) {
			play -= 1;
			tam = 0;
			rotar = 0;
		}

		// me permite seguir cambiando imagenes infinitamente
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

//		// correr imagenes pequeñas
//		if (app.mouseX > 207 && app.mouseX < 153 && app.mouseY > 581 && app.mouseY < 543) {
//			posY -= 120;
//			numIma += 1;
//			var += 1;
//		}
//
//		// indicaciones para recorrer las imagenes pequeñas
//		if (numIma < 0) {
//			numIma = 19;
//		}
//
//		if (var < 0) {
//			var = 19;
//		}
//
//		if (numIma >= 20) {
//			numIma = 4;
//			posY += 1917;
//		}
//
//		if (var >= 16) {
//			var = 0;
//		}

		System.out.println("Pos X " + app.mouseX + " pos Y " + app.mouseY);
		// System.out.println(tam);
		// System.out.println(rotar);

	}

	// Angeles======================================================================================================================

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

			image.resize(image.width + tam, image.height + tam);
		}
	}

	// Angeles======================================================================================================================

	public void rotar() {

		if (app.mouseX < 471 && app.mouseX > 429 && app.mouseY < 444 && app.mouseY > 405) {
			rotar -= 90;
		}

		if (app.mouseX < 543 && app.mouseX > 501 && app.mouseY < 442 && app.mouseY > 406) {
			rotar += 90;
		}

	}

	// Angeles======================================================================================================================

	public void fullScreen() {
		if (app.mouseX < 692 && app.mouseX > 653 && app.mouseY < 444 && app.mouseY > 406) {
			full = !full;

			image = ima[play].get();
			image.resize(412, 241);
			// re-escala la imagen a pantalla completa
			image.resize(1004, 604);

		}
	}

}