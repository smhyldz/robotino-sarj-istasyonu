package com.robotinocharger;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * This widget provides basic controls to drive Robotino.
 */
public class DriveWidget extends JComponent
{
	protected static final float speed = 200.0f;
	protected static final float rotSpeed = 50.0f;
	
	protected final Robot robot;
	
	public DriveWidget(Robot robot)
	{
		this.robot = robot;
		
		setLayout(new GridLayout(3, 5));
		
		JButton buttonUp = new JButton(getIcon("n"));
		JButton buttonDown = new JButton(getIcon("s"));
		JButton buttonLeft = new JButton(getIcon("o"));
		JButton buttonRight = new JButton(getIcon("w"));
		JButton buttonCL = new JButton(getIcon("cl"));
		JButton buttonCCL = new JButton(getIcon("ccl"));
		JButton buttonStop = new JButton(getIcon("stop"));
		
		buttonUp.addActionListener( new ButtonListener(speed, 0.0f, 0.0f) );
		buttonDown.addActionListener( new ButtonListener(-speed, 0.0f, 0.0f) );
		buttonLeft.addActionListener( new ButtonListener(0.0f, -speed, 0.0f) );
		buttonRight.addActionListener( new ButtonListener(0.0f, speed, 0.0f) );
		buttonCL.addActionListener( new ButtonListener(0.0f, 0.0f, -rotSpeed) );
		buttonCCL.addActionListener( new ButtonListener(0.0f, 0.0f, rotSpeed) );
		buttonStop.addActionListener( new ButtonListener(0.0f, 0.0f, 0.0f) );

		add(new JLabel());
		add(new JLabel());
		add(buttonUp);
		add(new JLabel());
		add(new JLabel());
		add(buttonCCL);
		add(buttonRight);
		add(buttonStop);
		add(buttonLeft);
		add(buttonCL);
		add(new JLabel());
		add(new JLabel());
		add(buttonDown);
		
		setMinimumSize( new Dimension(60, 30) );
		setPreferredSize( new Dimension(200, 120) );
		setMaximumSize( new Dimension(Short.MAX_VALUE, Short.MAX_VALUE) );
	}
	
	private Icon getIcon(String name)
	{
		return new ImageIcon("./icons/" + name + ".png");
	}
	
	private class ButtonListener implements ActionListener
	{
		private final float vx;
		private final float vy;
		private final float omega;
		
		public ButtonListener(float vx, float vy, float omega)
		{
			this.vx = vx;
			this.vy = vy;
			this.omega = omega;
		}

		public void actionPerformed(ActionEvent e) {
			robot.setVelocity( vx, vy, omega );
		}
	
	}
}
