import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.VideoInputFrameGrabber;

import static org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_legacy.*;




public class Camera implements Runnable { 
	//final int INTERVAL=1000;///you may use interval 
	
	Thread webcam;

    static CvScalar rgba_min = cvScalar(0, 0, 130, 0);
    static CvScalar rgba_max = cvScalar(80, 80, 255, 0);
	IplImage image; 
    int posX = 0;
    int posY = 0;
    int ii = 0;
    
	static BufferedImage cam;
	public Camera() { 
		
		webcam = new Thread (this);
		 webcam.start() ; 

		} 
	
	@Override 
	public void run() { 
		FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera 
		int i=0; 
		try { 
			
		grabber.start();
		IplImage img; 
		while (true) {
			img = grabber.grab(); 
			if (img != null) { 
				cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise 

				cam = img.getBufferedImage();
				
				
                IplImage detectThrs = getThresholdImage(img);
				CvMoments moments = new CvMoments();
                cvMoments(detectThrs, moments, 1);
                double mom10 = cvGetSpatialMoment(moments, 1, 0);
                double mom01 = cvGetSpatialMoment(moments, 0, 1);
                double area = cvGetCentralMoment(moments, 0, 0);
                posX = (int) (mom10 / area);
                posY = (int) (mom01 / area);


				} 
				

				
		} } catch (Exception e) { } 
		
	
	


	}
	
	private IplImage getThresholdImage(IplImage orgImg) {
        IplImage imgThreshold = cvCreateImage(cvGetSize(orgImg), 8, 1);
        //
        cvInRangeS(orgImg, rgba_min, rgba_max, imgThreshold);// red

        //cvSaveImage(++ii + "dsmthreshold.jpg", imgThreshold);
        return imgThreshold;
    }
	
	public IplImage Equalize(BufferedImage bufferedimg) {
        IplImage iploriginal = IplImage.createFrom(bufferedimg);
        IplImage srcimg = IplImage.create(iploriginal.width(), iploriginal.height(), IPL_DEPTH_8U, 1);
        IplImage destimg = IplImage.create(iploriginal.width(), iploriginal.height(), IPL_DEPTH_8U, 1);
        cvCvtColor(iploriginal, srcimg, CV_BGR2GRAY);
        cvEqualizeHist(srcimg, destimg);
        return destimg;
    }
		
	public int getPosX() {
		return posX;

    }
	
	public int getPosY() {
		return posY;

    }
	
	public BufferedImage getValue() {
		return cam;

    }
	
		}
	


	


