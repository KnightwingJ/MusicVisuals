package ie.tudublin;

// Navy Blue		Blue Grotto			Blue Green			Baby Blue
// (5, 68, 94)		(24, 154, 180)		(117, 230, 218)		(212, 241, 244)

import C21718369.*;
import C2175505.*;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
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
		fullScreen(P3D);
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
		float halfH = height / 2;
		float halfW = width / 2;

		off += 1;
		background(0);
		// Calculate sum and average of the samples
		// Also lerp each element of buffer;
		/*
		 * for (int i = 0; i < ab.size(); i++) {
		 * sum += abs(ab.get(i));
		 * }
		 * average = sum / (float) ab.size();
		 * 
		 * smoothedAmplitude = lerp(smoothedAmplitude, average, 0.2f);
		 */
		calculateAverageAmplitude();

		float cx = width / 2;
		float cy = height / 2;

		// float seconds = millis() / 1000.0f;
		System.out.println(timer.seconds());

		// Intro
		if (timer.seconds() >= 2 && timer.seconds() < 6) {

			speed = map(50, 0, width, 0, 50);
			background(0);

			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			strokeWeight(1);

			rectMode(PApplet.CENTER);

			fill(0, 0, 255);
			textSize(200);
			text("E", width / 2 - 350, height / 2 + 80);

		}

		else if (timer.seconds() >= 6 && timer.seconds() < 9) {

			speed = map(50, 0, width, 0, 50);
			background(0);

			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			strokeWeight(1);

			rectMode(PApplet.CENTER);

			fill(5, 92, 242);
			textSize(200);
			text("EY", width / 2 - 350, height / 2 + 80);

		}

		else if (timer.seconds() >= 9 && timer.seconds() < 12) {

			speed = map(50, 0, width, 0, 50);
			background(0);

			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			strokeWeight(1);

			rectMode(PApplet.CENTER);

			fill(12, 71, 173);
			textSize(200);
			text("EYE", width / 2 - 350, height / 2 + 80);

		}

		else if (timer.seconds() >= 12 && timer.seconds() < 15) {

			speed = map(50, 0, width, 0, 50);
			background(0);

			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			strokeWeight(1);

			rectMode(PApplet.CENTER);
			fill(59, 127, 245);
			textSize(200);
			text("EYEL", width / 2 - 350, height / 2 + 80);

		}

		else if (timer.seconds() >= 15 && timer.seconds() < 18) {

			speed = map(50, 0, width, 0, 50);
			background(0);

			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			strokeWeight(1);

			rectMode(PApplet.CENTER);
			fill(14, 52, 117);
			textSize(200);
			text("EYELA", width / 2 - 350, height / 2 + 80);

		}
		// End Intro
		else if (timer.seconds() >= 18 && timer.seconds() < 20) {

			speed = map(50, 0, width, 0, 50);
			background(0);
			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			strokeWeight(1);

			rectMode(PApplet.CENTER);
			fill(12, 98, 245);
			textSize(200);
			text("EYELAR", width / 2 - 350, height / 2 + 80);

			// Solar System
		} else if (timer.seconds() >= 20 && timer.seconds() < 35) {
			float r = map(getSmoothedAmplitude(), 0, 0.5f, 100, 2000);
			background(0);
			stroke(0);
			speed = map(80, 0, width, 0, 50);

			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			// Translate to center of window to draw the sun.
			translate(width / 2, height / 2);
			fill(84, 143, 168);
			ellipse(0, 0, 320, 320);
			fill(0);
			ellipse(0, 0, ((r + 150) + (getSmoothedAmplitude() - 500)) / 2,
					((r + 150) + (getSmoothedAmplitude() - 500)) / 2);

			// The earth rotates around the sun
			pushMatrix();
			rotate(theta);
			translate(250, 0);
			fill(5, 68, 94);
			ellipse(0, 0, 160, 160);

			// Moon #1 rotates around the earth
			// pushMatrix() is called to save the transformation state before drawing moon
			// #1.
			// This way we can pop and return to earth before drawing moon #2.
			// Both moons rotate around the earth (which itself is rotating around the sun).
			// pushMatrix();
			rotate(-theta * 4);
			translate(150, 0);
			fill(24, 154, 180);
			ellipse(0, 0, 60, 60);
			// popMatrix();

			// Moon #2 also rotates around the earth
			// pushMatrix();
			rotate(theta * 2);
			translate(100, 0);
			fill(212, 241, 244);
			ellipse(0, 0, 30, 30);
			// popMatrix();

			// pushMatrix();
			rotate(-theta * 4);
			translate(230, 0);
			fill(50, 255, 200);
			ellipse(0, 0, 45, 45);
			// popMatrix();

			// pushMatrix();
			rotate(-theta * 4);
			translate(285, 0);
			fill(117, 230, 218);
			ellipse(0, 0, 90, 90);
			// popMatrix();

			popMatrix();

			theta += 0.01;

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
