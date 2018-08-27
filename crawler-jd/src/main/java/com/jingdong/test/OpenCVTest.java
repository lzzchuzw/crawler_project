package com.jingdong.test;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCVTest {
	public static void main( String[] args ) {
		//imageShapeTest();
		matchTemplateTest();
		//matchTemplateTest();
	}
	
	public static void enTest() {
		 System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	      Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
	      System.out.println( "mat = " + mat.dump() );
	}
	
	
	public static void imageShapeTest() {
		String srcImg = "F:/src.png";
		String shapeImg = "F:/shape.png";
		String desImg = "F:/des.png";
		Double lowThresh = 100.0;
		Double highThresh = 300.0;
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		
		Mat srcMat = Imgcodecs.imread(srcImg);
		Mat srcGrayMat = new Mat();
		
		
		
		Imgproc.cvtColor(srcMat, srcGrayMat, Imgproc.COLOR_BGR2GRAY);
		
		Imgproc.blur(srcGrayMat, srcGrayMat, new Size(3,3));
		
		Imgproc.Canny(srcGrayMat, srcGrayMat, lowThresh, highThresh);
		
		Imgcodecs.imwrite(desImg, srcGrayMat);
		
		//Imgproc.matchShapes(contour1, contour2, method, parameter)
		
		//Imgproc.matchTemplate(image, templ, result, method);
	}
	
	public static void matchTemplateTest() {
		String backImg = "F:/src.png";
		String shapeImg = "F:/shape.png";
		String desImg = "F:/des.png";
		Double lowThresh = 100.0;
		Double highThresh = 300.0;
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		//读入
		Mat srcMat = Imgcodecs.imread(backImg,0);
		Mat shapeMat = Imgcodecs.imread(shapeImg, 0);

		Size srcSize = srcMat.size();
		
		//临时重写
		String tmp = "F:/tmp.png";
		String targ = "F:/targ.png";
		Imgcodecs.imwrite(tmp, srcMat);
		Imgcodecs.imwrite(targ, shapeMat);
		//再次读入targ
		Mat target = Imgcodecs.imread(targ);
		Mat mTarget = new Mat();
		Imgproc.cvtColor(target, mTarget, Imgproc.COLOR_BGR2GRAY);
		
		Mat m = Mat.ones(mTarget.size(), 0);
		
		
		Mat m2 = new Mat();
		Core.convertScaleAbs(m, m2, 255, 0);
		
		/*for(int i=0;i<m2.height();i++) {
			for(int j=0;j<m2.width();j++) {
				double[] val = m2.get(i, j);
				
				System.out.println("val["+i+"]["+j+"] = "+val[0]);
				
			}
		}*/
		
		
		//Core.add(m2, mTarget, target);
		Core.addWeighted(m2, 1, mTarget, -1, 0, target);
		/*for(int i=0;i<target.height();i++) {
			for(int j=0;j<target.width();j++) {
				double[] val = target.get(i, j);
				
				System.out.println("val["+i+"]["+j+"] = "+val[0]);
				
			}
		}*/
		
		
		Imgcodecs.imwrite(targ, target);
		target = Imgcodecs.imread(targ);
		Mat template = Imgcodecs.imread(tmp);
		Mat result = new Mat();
		Imgproc.matchTemplate(target, template, result,Imgproc.TM_CCOEFF);
		MinMaxLocResult ret = Core.minMaxLoc(result);
		Point maxPoint = ret.maxLoc;
		Point minPoint = ret.minLoc;
		System.out.println("maxPoint.x = "+maxPoint.x+"---maxPoint.y = "+maxPoint.y);
		//System.out.println("minPoint.x = "+minPoint.x+"---minPoint.y = "+minPoint.y);
		
	}
	
	public static void cannyTest() {
		String backImg = "F:/src.png";
		String shapeImg = "F:/shape.png";
		String desImg = "F:/des.png";
		Double lowThresh = 100.0;
		Double highThresh = 300.0;
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		//读入
		Mat backMat = Imgcodecs.imread(backImg);
		Mat shapeMat = Imgcodecs.imread(shapeImg);
		//处理backImg
		Mat tbackMat = new Mat();
		Imgproc.GaussianBlur(backMat, tbackMat, new Size(3,3), 0);
		Imgproc.cvtColor(tbackMat,tbackMat,Imgproc.COLOR_BGR2GRAY);
		Imgproc.Canny(tbackMat, backMat, 100, 300);
		//初始化shapeImg
		Mat tshapeMat = new Mat();
		Mat hierarchy = new Mat();
		List<MatOfPoint> contoursList = new ArrayList<MatOfPoint>();
		Imgproc.cvtColor(shapeMat, tshapeMat, Imgproc.COLOR_BGR2GRAY);
		Imgproc.threshold(tshapeMat, tshapeMat, 127, 255, Imgproc.THRESH_BINARY);
		//获取边界
		Imgproc.findContours(tshapeMat, contoursList, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
		//获取最外层边界
		MatOfPoint outPoint = contoursList.get(contoursList.size()-1);
		//Operator矩阵		
		Mat operator = Mat.zeros(shapeMat.size(),0);
		List<Point> pList = outPoint.toList();
		for(Point p:pList) {
			//operator.get((int)p.x,(int)p.y);
			operator.put((int)p.x,(int)p.y, 1);
		}
		Double maxValue = 0.0;
		int location = 0;
		//循环查找
		for(int x=(int) shapeMat.size().width;x<(backMat.size().width-shapeMat.size().width);x++) {
			Mat block = backMat.submat(0, backMat.width(), x, (int) (x+shapeMat.size().width-1));
			Mat mulMat = block.mul(operator);
			Size mulMatSize = mulMat.size();
			Double value = 0.0;
			for(int i=0;i<mulMatSize.width;i++) {
				for(int j=0;j<mulMatSize.height;j++) {
					double[] tmp = mulMat.get(i, j);
					//mulMat.
				}
			}
		}
	
		
	}


}
