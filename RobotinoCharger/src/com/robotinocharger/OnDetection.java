package com.robotinocharger;

import java.util.Vector;

import org.opencv.core.Mat;

import es.ava.aruco.Board;
import es.ava.aruco.Marker;

public interface OnDetection {

	void onDetection(Mat mFrame, Vector<Marker> mDetectedMarkers,
			int mIdSelected);

	void onBoardDetection(Mat mFrame, Board mBoardDetected, float prob);

}
