
import processing.core.*;

public class MainAppS7 extends PApplet {
	Logica app;

	public void setup() {
		size(1004, 604);
		app = new Logica(this);
	}

	public void draw() {
		background(255);
		app.pintar();
	}
	
	public void mousePressed(){
		app.mouse();
		app.aumentar();
		app.rotar();
		app.fullScreen();
	}
}

