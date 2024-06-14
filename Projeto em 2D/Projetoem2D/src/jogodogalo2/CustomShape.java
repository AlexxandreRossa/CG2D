package jogodogalo;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CustomShape implements Shape {
	
	GeneralPath path;
	
	public CustomShape(float x, float y, float w, float h) {
		path = new GeneralPath();
		
		//(220, 650, 300, 200); w  271  h  698
		
		float x0 = x + 0.17f*w - 65;
	    float y0 = y + 0.24f*h - 25;
	    float x1 = x + 0.17f*w + 11; 
	    float y1 = y + 0.24f*h - 53;
	    float x2 = x + 0.17f*w + 49;
	    float y2 = y + 0.24f*h - 14;  
	    float x3 = x + 0.17f*w + 37;
	    float y3 = y + 0.24f*h - 8;
	    float x4 = x + 0.17f*w + 82;
	    float y4 = y + 0.24f*h + 26;
	    float x5 = x + 0.17f*w + 68;
	    float y5 = y + 0.24f*h + 31;
		float x6 = x + 0.17f*w + 130;
	    float y6 = y + 0.24f*h + 90;
		float x7 = x + 0.17f*w + 27;
	    float y7 = y + 0.24f*h + 46;
		float x8 = x + 0.17f*w + 44;
	    float y8 = y + 0.24f*h + 38;
		float x9 = x + 0.17f*w - 19;
	    float y9 = y + 0.24f*h + 10;
		float x10 = x + 0.17f*w + 1;
	    float y10 = y + 0.24f*h + 1;
		float x11 = x + 0.17f*w - 65;
	    float y11 = y + 0.24f*h - 25;
	    
	    path.moveTo(x0, y0);
	    path.lineTo(x1, y1);
	    path.lineTo(x2, y2);
	    path.lineTo(x3, y3);
	    path.lineTo(x4, y4);
	    path.lineTo(x5, y5);
	    path.lineTo(x6, y6);
	    path.lineTo(x7, y7);
	    path.lineTo(x8, y8);
	    path.lineTo(x9, y9);
	    path.lineTo(x10, y10);
	    path.lineTo(x11, y11);
	    path.closePath();


	    		
	}

	@Override
	public Rectangle getBounds() {
		return path.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return path.getBounds2D();
	}

	@Override
	public boolean contains(double x, double y) {
		return path.contains(x, y);
	}

	@Override
	public boolean contains(Point2D p) {
		return path.contains(p);
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return path.intersects(x, y, w, h);
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return path.intersects(r);
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return path.contains(x, y, w, h);
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return path.contains(r);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return path.getPathIterator(at);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return path.getPathIterator(at, flatness);
	}

}
