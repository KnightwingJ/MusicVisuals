package C21718369;

import java.util.ArrayList;

//import ddf.minim.AudioBuffer;
import ie.tudublin.*;

import processing.core.PApplet;

public class Horizon extends PApplet {
    Audio1 a1;

    float speed;
    ArrayList<Star> stars = new ArrayList<Star>();
    public Horizon(Audio1 a1) {
        this.a1 = a1;
        for (int i=0;i<800;i++){
            stars.add(new Star());
        }
    }

    public void render() {
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        a1.colorMode(RGB);

        a1.background(0);
        speed = map(50, 0, a1.width, 0, 50);
        a1.background(0);
        for (int i = 0; i < stars.size(); i++) {
			stars.get(i).update();
			stars.get(i).show();
		}

        float cx = a1.width / 2;
        float cy = a1.height / 2;


        a1.strokeWeight(2);
        a1.noFill();
        a1.beginShape();
        for (float i = 0; i < TWO_PI; i += 0.01f) {
            a1.stroke(0);
            a1.strokeWeight(15);
            float r1 = a1.width / 1.5f;
            float x2 = r1 * cos(i);
            float y1 = r1 * pow(sin(i), 3) * 0.5f;
            // puts the eye in the center of the screen
            a1.vertex(x2 + cx, y1 + cy);

            a1.stroke(84, 143, 168);
            a1.strokeWeight(5);
            float r2 = a1.width / 9 + (a1.getSmoothedAmplitude() * random(10, 150) * 2);
            float x = r2 * cos(i);
            // center the ring
            a1.vertex(x + cx, 50 + cy);
        }
        a1.endShape(CLOSE);

			float r = map(a1.getSmoothedAmplitude(), 0, 0.5f, 100, 2000);
			a1.stroke(84, 143, 168);
			a1.circle(cx, cy, r + (a1.getSmoothedAmplitude() - 500));
			a1.circle(cx, cy, (r + 150) + (a1.getSmoothedAmplitude() - 500));
			a1.circle(cx, cy, (r - 150) + (a1.getSmoothedAmplitude() - 500));
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
