package ie.tudublin;

//import example.AudioBandsVisual;
//import example.CubeVisual;
import example.MyVisual;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisual());		
	}

	public void Audio1()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio1());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.Audio1();	
		//main.startUI();;
	
		
	}
}