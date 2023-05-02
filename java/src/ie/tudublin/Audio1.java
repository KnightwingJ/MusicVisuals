package ie.tudublin;

// Navy Blue		Blue Grotto			Blue Green			Baby Blue
// (5, 68, 94)		(24, 154, 180)		(117, 230, 218)		(212, 241, 244)

import C21718369.*;
import C2175505.*;
import C21397083.*;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PVector;

import java.util.ArrayList;

public class Audio1 extends Visual {
	Minim minim;
	AudioPlayer ap;
	AudioInput ai;
	AudioBuffer ab;
	public Timer timer;
	Horizon hz;
	Eye ey;
	SolarSystem ss;
	introduction intro;

	int mode = 0;
	float seconds = 0;
	float theta = 0;

	float y = 0;
	float smoothedY = 0;
	float smoothedAmplitude = 0;
	ArrayList<Star> stars = new ArrayList<Star>();
	float speed;

	PVector[][] globe;
	int r = 200;
	int total = 25;
	float angleX = 0;
	float angleY = 0;

	public void settings() {
		// size(2560 ,1600);
		fullScreen(P3D,SPAN);
	}

	public void setup() {
		// minim = new Minim(this);

		timer = new Timer(this);
		// Load Music file
		// ap = minim.loadFile("Eyelar.mp3", 1024);
		// ap.play();
		// ab = ap.mix;
		startMinim();
		hz = new Horizon(this);
		ey = new Eye(this);
		ss=new SolarSystem(this);
		intro=new introduction(this);

		loadAudio("Eyelar.mp3");
		y = height / 2;
		smoothedY = y;

		for (int i = 0; i < 800; i++) {
			stars.add(new Star());
		}

		globe = new PVector[total + 1][total + 1];
		for (int i = 0; i < total + 1; i++) {
			float lat = map(i, 0, total, 0, PI);
			for (int j = 0; j < total + 1; j++) {
				float lon = map(j, 0, total, 0, TWO_PI);
				float x = r * sin(lat) * cos(lon);
				float y = r * sin(lat) * sin(lon);
				float z = r * cos(lat);
				globe[i][j] = new PVector(x, y, z);
			}
		}

	}

	public void keyPressed() {
		if (key == ' ') {
			getAudioPlayer().cue(0);
			getAudioPlayer().play();
			timer.start();
		}
	}

	float off = 0;

	void star(float x, float y, float radius1, float radius2, int npoints) {
		float angle = TWO_PI / npoints;
		float halfAngle = (float) (angle / 2.0);
		beginShape();
		for (float a = 0; a < TWO_PI; a += angle) {
			float sx = x + cos(a) * radius2;
			float sy = y + sin(a) * radius2;
			vertex(sx, sy);
			sx = x + cos(a + halfAngle) * radius1;
			sy = y + sin(a + halfAngle) * radius1;
			vertex(sx, sy);
		}
		endShape(CLOSE);
	}

	public void draw() {
		// timer.update();
		off += 1;
		background(0);
		calculateAverageAmplitude();
		// float seconds = millis() / 1000.0f;
		System.out.println(timer.seconds());

		// Intro
		if (timer.seconds() >= 2 && timer.seconds() < 20) {
			intro.render();
			
			// Solar System
		} else if (timer.seconds() >= 20 && timer.seconds() < 35) {
			ss.render();
			
		} // else if (timer.seconds() >= 25 && timer.seconds() < 35) {

		// }

		// Horizon vertex animation
		else if (timer.seconds() >= 35 && timer.seconds() < 45) {
			hz.render();
		}

		else if (timer.seconds() >= 45 && timer.seconds() < 48) {
			background(0);
		}

		// eye animation
		else if (timer.seconds() >= 48 && timer.seconds()<66) {
			ey.render();
		}
		else if(timer.seconds() >=66){
			System.exit(0);
		}

	}

	class Star {
		float x;
		float y;
		float z;
		float pz;

		Star() {
			x = random(-width / 2, width / 2);
			y = random(-height / 2, height / 2);
			z = random(width / 2);
			pz = z;
		}

		void update() {
			z = z - speed;
			if (z < 1) {
				z = width / 2;
				x = random(-width / 2, width / 2);
				y = random(-height / 2, height / 2);
				pz = z;
			}
		}

		void show() {
			fill(255);
			noStroke();

			float sx = map(x / z, 0, 1, 0, width);
			float sy = map(y / z, 0, 1, 0, height);

			float r = map(z, 0, width / 2, 8, 0);
			ellipse(sx, sy, r, r);

			float px = map(x / pz, 0, 1, 0, width);
			float py = map(y / pz, 0, 1, 0, height);

			stroke(255);
			line(px, py, sx, sy);

			pz = z;
		}
	}
}
