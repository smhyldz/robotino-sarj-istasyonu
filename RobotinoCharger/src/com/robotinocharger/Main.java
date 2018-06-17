package com.robotinocharger;
import javax.swing.UIManager;

import org.opencv.core.Core;

public class Main
{	
    public static void main(String[] args)
    {    
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Try to use the Look & Feel of the native Operating System
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) { }
    
		//MainFrame frame = new MainFrame(new Robot());
		//frame.setTitle( "Robotino Examples - GUI" );
	
		Robot robot= new Robot();
		robot.connect("127.0.0.1:8080", false);
		{
		

    }


		//robot.setVelocity(200, 200, 0);
		//robot.omniDrive.setVelocity( -10000, 0, 0 );
	
	
        //System.out.println("Welcome to the GUI example.");
	  
    }	
}
