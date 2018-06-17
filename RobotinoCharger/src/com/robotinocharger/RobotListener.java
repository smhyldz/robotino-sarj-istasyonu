package com.robotinocharger;
import java.awt.Image;

/**
 * This class is used to listen for incoming events in the Robot class.
 */
public interface RobotListener
{
	void onConnected();
	void onDisconnected();
	void onError(rec.robotino.com.Com.Error error);
	void onImageReceived(Image img);
}
