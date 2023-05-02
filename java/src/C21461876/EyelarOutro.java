package C21461876;

import java.util.ArrayList;
import ie.tudublin.*;


import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

import processing.core.PApplet; 
import processing.core.PVector;



public class EyelarOutro extends PApplet {

    Audio1 a1;
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

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

    public EyelarOutro(Audio1 a1)
    {
        this.a1 = a1;
        for(int i=0;i<800;i++)
        {
            stars.add(new Star());
        }
    }

    public void render()
    {
        // timer.update();
		float halfH = a1.height / 2;
		float average = 0;
		float sum = 0;
		// Calculate sum and average of the samples
		// Also lerp each element of buffer;
		for (int i = 0; i < a1.getAudioBuffer().size(); i++) {
			sum += abs(a1.getAudioBuffer().get(i));
		}
		average = sum / (float) a1.getAudioBuffer().size();

		smoothedAmplitude = lerp(smoothedAmplitude, average, 0.2f);

		float cx = a1.width / 2;

		seconds = millis() / 1000.0f;
		System.out.println(seconds);

			a1.background(0);
			a1.strokeWeight(2);
			a1.noFill();
			a1.beginShape();
            speed = map(50, 0, a1.width, 0, 50);
			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}
			for (float i = 0; i < TWO_PI; i += 0.01f) {
				a1.stroke(0);
				a1.strokeWeight(15);
				float r1 = a1.width / 1.5f;
				float x2 = r1 * cos(i);
				float y1 = r1 * pow(sin(i), 3) * 0.2f;
				// puts the eye in the center of the screen
				a1.vertex(x2 + cx, y1 + halfH);

				a1.stroke(84, 143, 168);
				a1.strokeWeight(5);
				// make the ring go crazy and randomly but still on sync to music
				float r2 = a1.width / 9 + (smoothedAmplitude * random(5, 100) * 2);
				float x = r2 * cos(i);
				// center the ring
				a1.vertex(x + cx, 50 + halfH);
			}
			a1.endShape(CLOSE);
		
		
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

