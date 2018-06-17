package com.robotinocharger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

import javax.swing.JComponent;

import org.opencv.core.Mat;

import es.ava.aruco.Board;
import es.ava.aruco.Marker;

import rec.robotino.com.Com.Error;

/**
 * This widget listens for camera events and displays incoming images.
 */
public class CameraWidget extends JComponent implements OnDetection
{
	private volatile Image cameraImg;
	ArucoDetector detector = new ArucoDetector(this);
	
	public CameraWidget(Robot robot)
	{
		robot.addListener( new RobotListenerImpl() );
		
		setMinimumSize( new Dimension(32, 24) );
		setPreferredSize( new Dimension(320, 240) );
		setMaximumSize( new Dimension(Short.MAX_VALUE, Short.MAX_VALUE) );
		
		setDoubleBuffered( true );
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		if(cameraImg != null)
		{
			g.drawImage( cameraImg, 0, 0, getWidth(), getHeight(), null );
		}
		else
		{
			g.setColor( Color.BLACK );
			g.fillRect( 0, 0, getWidth(), getHeight() );
			g.setColor( Color.WHITE );
			g.drawString( "No camera image", getWidth() / 2 - 50, getHeight() / 2 - 5 );
		}
	}
	
	private class RobotListenerImpl implements RobotListener
	{
		
		public void onImageReceived(Image img)
		{
			cameraImg = img;
			detector.onImageChanged(img, getWidth(), getHeight());
			repaint();
		}	
		

		public void onConnected()
		{
		}


		public void onDisconnected()
		{
		}


		public void onError(Error error)
		{
		}
	}
	/** TAG BURAYA GELIYOR **/
	@Override
	public void onDetection(Mat mFrame, Vector<Marker> mDetectedMarkers,
			int mIdSelected) {
		// TODO Auto-generated method stub
		System.out.println("On Detection");
		
	}

	@Override
	public void onBoardDetection(Mat mFrame, Board mBoardDetected, float prob) {
		// TODO Auto-generated method stub
		System.out.println("On onBoardDetection");
	}
}
