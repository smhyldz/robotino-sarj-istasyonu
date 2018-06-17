package com.robotinocharger;

import java.awt.Image;
import java.util.Vector;

import org.opencv.core.CvException;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.VideoCapture;

import es.ava.aruco.Board;
import es.ava.aruco.BoardConfiguration;
import es.ava.aruco.BoardDetector;
import es.ava.aruco.CameraParameters;
import es.ava.aruco.Marker;
import es.ava.aruco.MarkerDetector;
import es.ava.aruco.OpenCvUtils;
import es.ava.aruco.Utils;
import es.ava.aruco.exceptions.CPException;
import es.ava.aruco.exceptions.ExtParamException;

public class ArucoDetector {
	BoardConfiguration mBC;
	private Mat mFrame;
	private int mIdSelected;
	public CameraParameters mCamParam;
	protected float markerSizeMeters;
	protected MarkerDetector mDetector;
	protected BoardDetector mBDetector;
	protected OnDetection mRenderer;
	protected Vector<Marker> mDetectedMarkers = new Vector();	
	protected Board	mBoardDetected = new Board();
	

	public ArucoDetector(OnDetection renderer) {
	        mCamParam = new CameraParameters();
	        mDetector = new MarkerDetector();
	        mBDetector = new BoardDetector();
	        mRenderer = renderer;
	     
	        mIdSelected = -1;
	        init();
	}
	
	
	/*
	 * https://terpconnect.umd.edu/~jwelsh12/enes100/markergen.html
	 */
	private void init(){
		int[] ids = {200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224};
		int[][] markersId = new int[5][5];
		int index = 0;
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++){
				markersId[i][j] = ids[index];
				index++;
			}
	
		mBC = new BoardConfiguration(5,5,markersId,100,20);
		markerSizeMeters= 0.034f;

	}
	
	public void onImageChanged(Image image,int image_width,int image_height){
		mFrame = new Mat();
    
		//double[] proj_matrix = new double[16];
		/*try {
			Utils.myProjectionMatrix(mCamParam, new Size(image_width,image_width), proj_matrix, 0.05, 10);
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExtParamException e){
			e.getMessage();
		}*/
		//mRenderer.setProjMatrix(proj_matrix); TODO look after
		mFrame = OpenCvUtils.imageToMat(image);
		processFrame(image_width,image_height);
	}
	
    protected void processFrame(int width, int height) {
   
		mDetector.detect(mFrame, mDetectedMarkers, mCamParam, markerSizeMeters,mFrame);
		
		mRenderer.onDetection(mFrame, mDetectedMarkers, mIdSelected);
		float prob=0;
		try{
			prob = mBDetector.detect(mDetectedMarkers, mBC, mBoardDetected, mCamParam, markerSizeMeters);
			System.out.println("boarddetection took: ");
		} catch (CvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mRenderer.onBoardDetection(mFrame, mBoardDetected, prob);
	}
  

     
  


	
}
