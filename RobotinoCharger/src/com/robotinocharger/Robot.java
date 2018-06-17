package com.robotinocharger;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import rec.robotino.com.Bumper;
import rec.robotino.com.Com;
import rec.robotino.com.DistanceSensor;
import rec.robotino.com.Motor;
import rec.robotino.com.OmniDrive;
import rec.robotino.com.PowerManagement;
import rec.robotino.com.Camera;


/**
 * The class Robot demonstrates the usage of the most common robot component classes.
 * Furthermore it shows how to handle events and receive incoming camera images.
 */
public class Robot
{
	
	protected final PowerManagement Power ;
	protected final CopyOnWriteArrayList<RobotListener> listeners;
	protected final Com com;
	protected final Motor motor1;
	protected final Motor motor2;
	protected final Motor motor3;
	protected final OmniDrive omniDrive;
	protected final Bumper bumper;
	protected final Camera camera;
	protected final DistanceSensor distance0Id;
	protected final DistanceSensor distance1Id;
	protected final DistanceSensor distance2Id;
	protected final DistanceSensor distance3Id;
	protected final DistanceSensor distance4Id;
	protected final DistanceSensor distance5Id;
	protected final DistanceSensor distance6Id;
	protected final DistanceSensor distance7Id;
	protected final DistanceSensor distance8Id;
	
	
	
	

	public Robot()
	{
		
		Power = new PowerManagement() ;
		com = new MyCom();
		motor1 = new Motor();
		motor2 = new Motor();
		motor3 = new Motor();
		omniDrive = new OmniDrive();
		bumper = new Bumper();
		camera = new MyCamera();
		distance0Id = new DistanceSensor();
		distance1Id = new DistanceSensor();
		distance2Id = new DistanceSensor();
		distance3Id = new DistanceSensor();
		distance4Id = new DistanceSensor();
		distance5Id = new DistanceSensor();
		distance6Id = new DistanceSensor();
		distance7Id = new DistanceSensor();
		distance8Id = new DistanceSensor();
		
		listeners = new CopyOnWriteArrayList<RobotListener>();
		
		init();
	}

	protected void init()
	{
		motor1.setComId( com.id() );
		motor1.setMotorNumber( 0 );

		motor2.setComId( com.id() );
		motor2.setMotorNumber( 1 );

		motor3.setComId( com.id() );
		motor3.setMotorNumber( 2 );

		omniDrive.setComId( com.id() );

		bumper.setComId( com.id() );

		Power.setComId(com.id());

		camera.setComId( com.id() );
		camera.setStreaming( true );
		
		distance0Id.setComId( com.id() );
		distance0Id.setSensorNumber (0);
		
		distance1Id.setComId( com.id() );
		distance1Id.setSensorNumber (1);
		
		distance2Id.setComId( com.id() );
		distance2Id.setSensorNumber (2);
		
		distance3Id.setComId( com.id() );
		distance3Id.setSensorNumber (3);
		
		distance4Id.setComId( com.id() );
		distance4Id.setSensorNumber (4);
		
		distance5Id.setComId( com.id() );
		distance5Id.setSensorNumber (5);
		
		distance6Id.setComId( com.id() );
		distance6Id.setSensorNumber (6);
		
		distance7Id.setComId( com.id() );
		distance7Id.setSensorNumber (7);
		
		distance8Id.setComId( com.id() );
		distance8Id.setSensorNumber (8);
		
		
		
		
		
	}
	
	public void addListener(RobotListener listener)
	{
		listeners.add( listener );
	}
	
	public void removeListener(RobotListener listener)
	{
		listeners.remove( listener );
	}
	
	public boolean isConnected()
	{
		return com.isConnected();
	}

	public void connect(String hostname, boolean block)
	{
		com.setAddress( hostname );
		com.connect(block);
	}

	public void disconnect()
	{
		com.disconnect();
	}
	
	public void setVelocity(float vx, float vy, float omega)
	{
		omniDrive.setVelocity( vx, vy, omega );
	}
	
	/**
	 * The class MyCom derives from rec.robotino.com.Com and implements some of the virtual event handling methods.
	 * This is the standard approach for handling these Events.
	 */
	class MyCom extends Com
	{

		@Override
		public void connectedEvent()
		{
			System.out.println( "Connected" );
			for(RobotListener listener : listeners)
				listener.onConnected();
		}

		@Override
		public void errorEvent(Error e, String errorStr)
		{
			System.err.println( "Error: " + e.toString() );
			for(RobotListener listener : listeners)
				listener.onError( e );
		}

		@Override
		public void connectionClosedEvent()
		{
			System.out.println( "Disconnected" );
			for(RobotListener listener : listeners)
				listener.onDisconnected();
		}
	}

	/**
	 * Class MyCamera inherits from rec.robotino.com.Camera and implements some of the virtual event handling methods.
	 * This is the standard approach for handling these Events.
	 */
	class MyCamera extends Camera
	{
		@Override
		public void imageReceivedEvent(Image img, long width, long height, long numChannels, long bitsPerChannel, long step)
		{
			for(RobotListener listener : listeners)
				listener.onImageReceived( img );
		}
	}
}
