package C2175505;

import java.util.ArrayList;

import ie.tudublin.*;

import processing.core.PApplet;

public class introduction extends PApplet{
    Audio1 a1;
    float speed;
    ArrayList<Star> stars = new ArrayList<Star>();

    public introduction(Audio1 a1){
        this.a1=a1;
		for(int i=0;i<800;i++)
		{
			stars.add(new Star());
		}
    }

    
    public void render(){
        // Intro
		if (a1.timer.seconds() >= 2 && a1.timer.seconds() < 6) {

			speed = map(50, 0, a1.width, 0, 50);
			a1.background(0);

			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			a1.strokeWeight(1);

			a1.rectMode(PApplet.CENTER);

			a1.fill(0, 0, 255);
			a1.textSize(200);
			a1.text("E", a1.width / 2 - 350, a1.height / 2 + 80);

		}

		else if (a1.timer.seconds() >= 6 && a1.timer.seconds() < 9) {

			speed = map(50, 0, a1.width, 0, 50);
			a1.background(0);

			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			a1.strokeWeight(1);

			a1.rectMode(PApplet.CENTER);

			a1.fill(5, 92, 242);
			a1.textSize(200);
			a1.text("EY", a1.width / 2 - 350, a1.height / 2 + 80);

		}

		else if (a1.timer.seconds() >= 9 && a1.timer.seconds() < 12) {

			speed = map(50, 0, a1.width, 0, 50);
			a1.background(0);

			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			a1.strokeWeight(1);

			a1.rectMode(PApplet.CENTER);

			a1.fill(12, 71, 173);
			a1.textSize(200);
			a1.text("EYE", a1.width / 2 - 350, a1.height / 2 + 80);

		}

		else if (a1.timer.seconds() >= 12 && a1.timer.seconds() < 15) {

			speed = map(50, 0, a1.width, 0, 50);
			a1.background(0);

			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			a1.strokeWeight(1);

			a1.rectMode(PApplet.CENTER);
			a1.fill(59, 127, 245);
			a1.textSize(200);
			a1.text("EYEL", a1.width / 2 - 350, a1.height / 2 + 80);

		}

		else if (a1.timer.seconds() >= 15 && a1.timer.seconds() < 18) {

			speed = map(50, 0, a1.width, 0, 50);
			a1.background(0);

			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			a1.strokeWeight(1);

			a1.rectMode(PApplet.CENTER);
			a1.fill(14, 52, 117);
			a1.textSize(200);
			a1.text("EYELA", a1.width / 2 - 350, a1.height / 2 + 80);

		}
		// End Intro
		else if (a1.timer.seconds() >= 18 && a1.timer.seconds() < 20) {

			speed = map(50, 0, a1.width, 0, 50);
			a1.background(0);
			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}

			a1.strokeWeight(1);

			a1.rectMode(PApplet.CENTER);
			a1.fill(12, 98, 245);
			a1.textSize(200);
			a1.text("EYELAR", a1.width / 2 - 350, a1.height / 2 + 80);

		}
    }

    class Star {
		float x;
		float y;
		float z;
		float pz;

		Star() {
			x = random(-a1.width / 2, a1.width / 2);
			y = random(-a1.height / 2, a1.height / 2);
			z = random(a1.width / 2);
			pz = z;
		}

		void update() {
			z = z - speed;
			if (z < 1) {
				z = a1.width / 2;
				x = random(-a1.width / 2, a1.width / 2);
				y = random(-a1.height / 2, a1.height / 2);
				pz = z;
			}
		}

		void show() {
			a1.fill(255);
			a1.noStroke();

			float sx = map(x / z, 0, 1, 0, a1.width);
			float sy = map(y / z, 0, 1, 0, a1.height);

			float r = map(z, 0, a1.width / 2, 8, 0);
			a1.ellipse(sx, sy, r, r);

			float px = map(x / pz, 0, 1, 0, a1.width);
			float py = map(y / pz, 0, 1, 0, a1.height);

			a1.stroke(255);
			a1.line(px, py, sx, sy);

			pz = z;
		}
	}
}

