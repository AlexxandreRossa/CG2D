package jogodogalo;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PaginaInicial extends JFrame implements ActionListener {
	
	PrinterJob pj;
	public static JFrame frame = new PaginaInicial();
	static JPanel mainPanel;
	MyPanel panelMenu ;
	static CardLayout cardlayout;
	
	public static void main(String[] args) {
		
		frame.setTitle("Jogo do Galo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

	}
	
	public PaginaInicial() {
		
		
		cardlayout = new CardLayout();
		mainPanel = new JPanel(cardlayout);
		panelMenu = new MyPanel();

		mainPanel.add(panelMenu, "painter");
		add(mainPanel);

		pj = PrinterJob.getPrinterJob();
		pj.setPrintable(panelMenu);
		
		// Add menus
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
				
		//Menu
		JMenu menu = new JMenu("Menu");
		JMenuItem menuItem = new JMenuItem("Imprimir");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
				
		//Process menu
		menu = new JMenu("Processamento de Imagem");
		menuItem = new JMenuItem("Gray scale");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Gray scale 2");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Binarization");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Smooth");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Sharpen");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Edge");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Rotate");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Rescale");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if("Imprimir".equals(cmd) && pj.printDialog()) {
			try {
				pj.print();
			} catch(PrinterException ex){
				ex.printStackTrace();
			}
		}else if("Exit".equals(cmd)) {
			System.exit(0);
		}else if("Gray scale".equals(cmd)) {
			MyPanel.processGrayScale();
			repaint();
		}else if("Sharpen".equals(cmd)) {
			MyPanel.processSharpen();
			repaint();
		}else if("Smooth".equals(cmd)) {
			MyPanel.processSharpen();
			repaint();
		}else if("Edge".equals(cmd)) {
			MyPanel.processEdge();
			repaint();
		}else if("Rescale".equals(cmd)) {
			MyPanel.processRescale();
			repaint();
		}else if("Rotate".equals(cmd)) {
			MyPanel.processRotate();
			repaint();
		}else if("Binarization".equals(cmd)) {
			MyPanel.processBinarization();
			repaint();
		}else if("Gray scale 2".equals(cmd)) {
			MyPanel.processRGB2Gray();
			repaint();
		}
	}

	
}
	
class MyPanel extends JPanel implements Runnable,Printable, MouseMotionListener,MouseListener,KeyListener {
	
		
	BufferedImage bi;
	private static BufferedImage image;
	private GeneralPath gp1 = new GeneralPath();
	private GeneralPath gp2 = new GeneralPath();
	public double spinValue = 0;
	
	AffineTransform at = new AffineTransform();
	
	// Declare blue obstacle
	Shape obj1 = null;
	Shape obj2 = null;
	Shape obj3 = null;
	Shape obj4 = null;
	
	// Declare green obstacle
	Shape obj5 = null;
	Shape obj6 = null;
	Shape obj7 = null;
	Shape obj8 = null;
	
	// Declare obstacle play and quit button
	Shape obj9 = null;
	Shape obj10 = null;
	
	// Declare obstacle image tic tac toe 
	Shape obj11 = null;
	
	// Declare angle
	float angleGreenObstacle = 0f;
	float angleBlueObstacle = 0f;
	
	// Declare player1
	Shape player1 = null;
	int player1R = 30;
	int player1X = 45;
	int player1Y = 750;
	BufferedImage player1Image = null;
	
	// Declare player2
	Shape player2 = null;
	int player2R = 30;
	int player2X = 650;
	int player2Y = 40;
	BufferedImage player2Image = null;
	
	boolean selected = false;
	
	// mouse
	int mXVelo = 0;
	int mYVelo = 0;
	
	BufferedImage stormImage = null;
	
	int[] rules = {AlphaComposite.CLEAR, AlphaComposite.SRC_OVER,
		    AlphaComposite.DST_OVER, AlphaComposite.SRC_IN,
		    AlphaComposite.DST_IN, AlphaComposite.SRC_OUT,
		    AlphaComposite.DST_OUT, AlphaComposite.SRC,
		    AlphaComposite.DST, AlphaComposite.SRC_ATOP,
		    AlphaComposite.DST_ATOP, AlphaComposite.XOR};
		  int ruleIndex = 0;

	
public MyPanel() {
		
	// add mouse listener
	addMouseMotionListener(this);
	addMouseListener(this);
		
	// Add keyboard listener
	this.setFocusable(true);
	addKeyListener(this);

	// Create thread
	Thread thread = new Thread(this);
	thread.start();
		

		
	int w = 700;
	int h = 800;
	setPreferredSize(new Dimension(w, h));
	setBackground(Color.white);
	bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	WritableRaster raster = bi.getRaster();
	int[] rgb = new int[3];
	float xmin = -2;
	float ymin = -2;
	float xscale = 4f/w;
	float yscale = 4f/h;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
			   float cr = xmin + j * xscale;
			   float ci = ymin + i * yscale;
			   int count = iterCount(cr, ci);
			   rgb[0] = (count & 0x07) << 5;
			   rgb[1] = ((count >> 4) & 0x07 & 0x09 & 0x01& 0x03 & 0x00& 0x09) << 5;
			   rgb[2] = ((count >> 2) & 0x07 & 0x05 & 0x06 & 0x010 & 0x041 & 0x071) << 5;
			   raster.setPixel(j, i, rgb);
		 }
  }
	
		//Get Logo image 
		URL url = getClass().getClassLoader().getResource("tictactoe.png");
		try {
			image = ImageIO.read(url);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		//Get player 1 image
		
		URL url2 = getClass().getClassLoader().getResource("x.png");
		try {
			player1Image = ImageIO.read(url2);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		//Get player 2 image
		
		URL url3 = getClass().getClassLoader().getResource("o.png");
		try {
			player2Image = ImageIO.read(url3);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		//Get storm image
		
		URL url4 = getClass().getClassLoader().getResource("relampago.jpg");
		try {
			stormImage = ImageIO.read(url4);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		//GeneralPath with elipse2D and arc2d appended
		gp1.append(new Ellipse2D.Double(-38, -320, 70, 70), true);
		for (double angle = 0; angle < 360; angle += 30) {
			Arc2D arc = new Arc2D.Double(-38, -320, 70, 70, 
					angle, 
					30, 
					Arc2D.PIE);
			gp1.append(arc, false);
		}
	
		//GeneralPath 2 with elipse2D and arc2d appended
		gp2.append(new Ellipse2D.Double(50, -320, 70, 70), true);
		for (double angle = 0; angle < 360; angle += 30) {
			Arc2D arc2 = new Arc2D.Double(50, -320, 70, 70, 
					angle, 
					30, 
					Arc2D.PIE);
			gp2.append(arc2, false);
		}
			  
	}

	private int iterCount(float cr, float ci) {
		 int max = 512;
	     float zr = 0;
		 float zi = 0;
		 float lengthsq = 0;
		 int count = 0;
		while ((lengthsq < 4.0) && (count < max)) {
			 float temp = zr * zr - zi * zi + cr;
			 zi = 2 * zr * zi + ci;
			 zr = temp;
			 lengthsq = zr * zr + zi * zi;
			 count++;
		}
		return max-count;
	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g.drawImage(bi,0,0,this);
		draw(g);
		
		
	}
	
	@Override
	public int print(Graphics g, PageFormat pF, int pageIndex) throws PrinterException {
		switch (pageIndex) {
		case 0:
			draw(g);
			break;
		case 1:
			g.translate(-(int) pF.getImageableWidth(), 0);
			draw(g);
			break;
		default:
			return NO_SUCH_PAGE;
		}
		return PAGE_EXISTS;
	}
	
	static BufferedImage a;
	
	public static void processGrayScale() {
		BufferedImageOp op = null;
		op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		BufferedImage bi = op.filter(image, null);
		a = bi;
	}
	
	public static void processSharpen() {
		BufferedImageOp op = null;
		float[] data = { 0f, -1f, 0f, -1f, 5f, -1f, 0f, -1f, 0f };
		Kernel k = new Kernel(3, 3, data);
		op = new ConvolveOp(k);
		BufferedImage bi = op.filter(image, null);
		a = bi;
	}
	
	public static void processEdge() {
		BufferedImageOp op = null;
		float[] data = { 0f, -1f, 0f, -1f, 4f, -1f, 0f, -1f, 0f };
		Kernel k = new Kernel(3, 3, data);
		op = new ConvolveOp(k);
		BufferedImage bi = op.filter(image, null);
		a = bi;
	}
	
	public static void processRescale() {
		BufferedImageOp op = null;
		op = new RescaleOp(1.5f, 0f, null);
		BufferedImage bi = op.filter(image, null);
		a = bi;
	}
				
	public static void processRotate() {
		BufferedImageOp op = null;
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.PI /4);
		op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage bi = op.filter(image, null);
		a = bi;
	}
	
	public static BufferedImage binarize(BufferedImage image) {
		WritableRaster rasterImageIn = image.getRaster();
		WritableRaster rasterImageOut = image.getRaster();
		int[] rgba = new int[4];
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				rasterImageIn.getPixel(x, y, rgba);
				int gray = (int) ((rgba[0] + rgba[1] + rgba[2]) / 3f);
				if (gray < 150)
					rgba[0] = rgba[1] = rgba[2] = 255;
				else
					rgba[0] = rgba[1] = rgba[2] = 0;
				rasterImageOut.setPixel(x, y, rgba);
			}
		}
		return image;
	}	
	
    public static void processBinarization() { 
    	binarize(image);
    }
 
    public static BufferedImage RGB2Gray(BufferedImage image) {
		WritableRaster rasterImgIn = image.getRaster();
		WritableRaster rasterImgOut = image.getRaster();
		int[] rgba = new int[4];
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				rasterImgIn.getPixel(x, y, rgba);
				int gray = (int) ((rgba[0] + rgba[1] + rgba[2]) / 3f);
				rgba[0] = rgba[1] = rgba[2] = gray;
				rasterImgOut.setPixel(x, y, rgba);
			}
		}
		return image;
	}
    
    public static void processRGB2Gray() {
    	RGB2Gray(image);
    }
    
	public void draw(Graphics g) {
		
	    Graphics2D g2 = (Graphics2D)g;
	    
	    
	    //Colors 
	    Color Color1 = new Color(100, 200, 100);
	    Color Color2 = new Color(150, 200, 150);
	    Color Color3 = new Color(1, 250, 167);
	    
	    //Fonts
	    Font font = new Font("Calibri", Font.BOLD, 50);
	    Font font2 = new Font("Courier", Font.PLAIN, 15);
	    Font font3 = new Font("Courier", Font.PLAIN, 50);
	    Font font4 = new Font("Calibri", Font.PLAIN, 25);
	    
	    //Strokes
	    Stroke stroke = new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
	    
	    //Gradient/Paints
	    GradientPaint gp = new GradientPaint(289,7, Color3, 308,80, Color1, true);
	    TexturePaint tr = new TexturePaint(stormImage, 
	    new Rectangle2D.Double(100, 100, stormImage.getWidth(), stormImage.getHeight()));
	    
	    //AlphaComposites
    	AlphaComposite ac = AlphaComposite.getInstance(rules[ruleIndex], 0.4f);
        AlphaComposite ac1 = AlphaComposite.getInstance(rules[ruleIndex], 1f);
        
	    //Primitive oval with ColorPaint 
	    g2.setPaint(Color3);
	    g2.fillOval(629, 18, 42, 44);
	    
	    //Primitive arc
	    g2.setPaint(Color1);
	    g2.drawArc(25,710, 42, 44, 12, 140);
	      
	    //Primitive polygon 
	    g2.setPaint(Color2);
		int xs[] = {image.getWidth()+10-50, image.getWidth()+10, image.getWidth()+10};
		int ys[] = {10, 10, 50};
		g.fillPolygon(xs, ys, 3);
		
        //Round Rectangle2D(2D API)
        RoundRectangle2D rct2dplay = new RoundRectangle2D.Double(245 , 260 , 210, 80,10, 10);
        g2.draw(rct2dplay);
        
        //Round Rectangle2D(2D API)
        RoundRectangle2D rct2dquit= new RoundRectangle2D.Double(245 , 360 , 210, 80,10, 10);
        g2.draw(rct2dquit);
        
        //Round Rectangle2D(2D API)
        RoundRectangle2D rct2d= new RoundRectangle2D.Double(10 , 10 , 180, 155,10, 10);
        g2.draw(rct2d);
        
        //Ellipse2D (2D API)
        g2.setPaint(Color2);
        Ellipse2D eli2d = new Ellipse2D.Double(25,725, 42, 49);
        g2.fill(eli2d);
        
        //QuadCurve2D (Shape from 2D API)
        g2.setPaint(Color3);
        QuadCurve2D qc2d1 = new QuadCurve2D.Double(550 , 755 , 360, 345,650, 755);
        g2.draw(qc2d1);
        
        //CustomShape 
	    g2.setPaint(tr);
	    Shape cs1 = new CustomShape(220, 650, 300, 200);
	    g2.fill(cs1);
	    
		// Create Blue Obstacle
		obj1 = new Rectangle2D.Double(-120, -10, 250, 20);
		obj2 = new Rectangle2D.Double(-10, -100, 20, 200);
		obj3 = new Rectangle2D.Double(-50, -10, 20, 200);
		obj4 = new Rectangle2D.Double(40, -170, 20, 200);
		
		// Create Green Obstacle
		obj5 = new Rectangle2D.Double(-120, -10, 250, 20);
		obj6 = new Rectangle2D.Double(-10, -100, 20, 200);
		obj7 = new Rectangle2D.Double(-50, -10, 20, 200);
		obj8 = new Rectangle2D.Double(40, -170, 20, 200);
		
		// Create Play and Quit Obstacle
		obj9 = new Rectangle2D.Double(350-100 , 400-32, 200, 64);
		obj10 = new Rectangle2D.Double(250 , 268 , 200, 64);
		
		// Create Tic Tac Toe Image Obstacle
		obj11 = new Rectangle2D.Double(500 , 600 , 180, 175);

		//Blue Obstacle
		at.setToIdentity();
		at.translate(10, 370);
		at.rotate(Math.toRadians(angleBlueObstacle));
		obj1 = at.createTransformedShape(obj1);
		obj2 = at.createTransformedShape(obj2);
		obj3 = at.createTransformedShape(obj3);
		obj4 = at.createTransformedShape(obj4);
		g2.setColor(Color.blue);
		g2.fill(obj1);
		g2.fill(obj2);
		g2.fill(obj3);
		g2.fill(obj4);
		
		//Green Obstacle
		at.setToIdentity();
		at.translate(700, 370);
		
		at.rotate(Math.toRadians(angleGreenObstacle));
		obj5 = at.createTransformedShape(obj5);
		obj6 = at.createTransformedShape(obj6);
		obj7 = at.createTransformedShape(obj7);
		obj8 = at.createTransformedShape(obj8);
		g2.setColor(Color.GREEN);
		g2.fill(obj5);
		g2.fill(obj6);
		g2.fill(obj7);
		g2.fill(obj8);
		
		//Play and Quit Obstacle
		at.setToIdentity();
		at.translate(0, 0);

		obj9 = at.createTransformedShape(obj9);
		obj10 = at.createTransformedShape(obj10);
		g2.setColor(Color.red);
		g2.fill(obj9);
		g2.fill(obj10);
		
		//Tic Tac Toe Image Obstacle
		
		at.setToIdentity();
		at.translate(0,0);
		obj11 = at.createTransformedShape(obj11);
		g2.setColor(Color.RED);
		g2.fill(obj11);


		//Create player 1
		
		player1 = new Ellipse2D.Double(-player1R, -player1R, 2 * player1R, 2 * player1R);
		
		//Create player 2 
		
		player2 = new Ellipse2D.Double(-player2R, -player2R, 2 * player2R, 2 * player2R);
		
		
		// Draw Player2	
		at.setToTranslation(650,40);
		player2 = at.createTransformedShape(player2);
		TexturePaint player2Paint = new TexturePaint(player2Image, player2.getBounds());
		g2.setPaint(player2Paint);
		g2.fill(player2);
		g2.draw(player2);
		
		
		// Draw Player1		
		at.setToTranslation(player1X,player1Y);
		player1 = at.createTransformedShape(player1);
		TexturePaint player1Paint = new TexturePaint(player1Image, player1.getBounds());
		g2.setPaint(player1Paint);
		g2.fill(player1);
		g2.draw(player1);
	
		mXVelo = 0;
		mYVelo = 0;
		
		checkFinish();
		checkColision();
		checkPanelLimits();
		
	    //Tic tac toe logo
		if(a == null) {
			g.drawImage(image, 500, 600, null);
		}else {
			g.drawImage(MyPanel.a,500, 600 , null);
		}
		
        //Quit Rectangle
        g2.setFont(font2);
        g2.setPaint(Color1);
    	g2.drawRect(350-100 , 400-32, 200, 64);
    	g2.drawString("Quit", 335, 408);
    	
    	//Play rectangle
    	g2.drawRect(250 , 268 , 200, 64);
    	g2.drawString("Play", 335, 308);
		
	    
	    //Primitive Line
	    g2.setStroke(stroke);
	    g2.setColor(Color.BLACK);
	    g2.drawLine(690, 0, 690, 800);
	    g2.drawLine(0, 790, 700, 790);
	    g2.drawLine(10, 0, 10, 800);
	    g2.drawLine(0, 10, 790, 10);

	    
	    
	    //Jogo
        //g2.setComposite(ac);
	    g2.setColor(Color1);
	    g2.setFont(font3);
	    g2.drawString("Jogo", 15, 50);	  
	    
 
	    
	    //Galo
	    //g2.setComposite(ac);
	    g2.setColor(Color1);
	    g2.setFont(font3);
	    g2.drawString("Galo", 15, 150);
	    
	    //Do
	    g2.setComposite(ac1);
	    g2.setColor(Color.WHITE);
	    g2.setFont(font);
	    g2.drawString("Do", 40, 100); 
	    
	    //Clipping Path
	    GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
	    path.moveTo(0,0);
	    path.lineTo(246,144);
	    path.lineTo(286,1);
	    path.lineTo(420,1);
	    path.lineTo(464,144);
	    path.lineTo(353,224);
	    path.lineTo(246,144);
	    path.closePath();
	    
	    g2.clip(path);
	    g2.setPaint(gp);
	    g2.fill(path);
	    
	    g2.setColor(Color.black);
	    g2.drawLine(353, 0, 353, 340);
	    g2.drawLine(240, 145, 500, 145);
	    g2.setFont(font4);
	 
	    g2.drawString("2D",310,180);
	    g2.drawString("CG",370,180);
	    
        //Rotating pie (Animation) using Arc2D and Elipse2D from 2D API  
	    //Geometric transformation applied to the coordinate system 
	    
	    AffineTransform oldTransform = g2.getTransform();
	    g2.setColor(Color.white);
	    RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);

        Rectangle2D bounds = gp1.getBounds2D();
        double x = (getWidth() - bounds.getWidth()) / 2d;
        double y = (getHeight() - bounds.getHeight()) / 2d;
        
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(spinValue, bounds.getCenterX(), bounds.getCenterY());

        g2.transform(at);
        g2.draw(gp1);
        at.setToIdentity();
        
        
        //---------------------------------------------------------------
        
        g2.setTransform(oldTransform);
	    g2.setColor(Color.white);
	    RenderingHints hintss = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hintss);

        Rectangle2D boundss = gp2.getBounds2D();
        double x2 = (getWidth() - boundss.getWidth()) / 2d;
        double y2 = (getHeight() - boundss.getHeight()) / 2d;
       
        AffineTransform att = AffineTransform.getTranslateInstance(x2, y2);
        att.rotate(spinValue, boundss.getCenterX(), boundss.getCenterY());

        g2.transform(att);
        g2.draw(gp2);
        att.setToIdentity();

	    
		
	
	}
	// check Finish
	public  void checkFinish()  {
		if(player2.contains(player1X,player1Y)) {
			PaginaInicial.frame.dispose();
			JogoDoGalo jogodogalo = new JogoDoGalo();
			jogodogalo.setVisible(true);
					
		}
	}
	
	private void checkColision() {
	
		if(player1.intersects(obj1.getBounds())||player1.intersects(obj2.getBounds())||player1.intersects(obj3.getBounds())||player1.intersects(obj4.getBounds())||player1.intersects(obj5.getBounds())||player1.intersects(obj6.getBounds())||player1.intersects(obj7.getBounds())||player1.intersects(obj8.getBounds())||player1.intersects(obj9.getBounds())|| player1.intersects(obj10.getBounds())|| player1.intersects(obj11.getBounds())) {
			player1X = 40;
			player1Y = 750;
			selected = false;
		}

	}
	private void checkPanelLimits() {
		if(player1X - player1R < 0) {
			player1X = player1R;
		}
		if(player1X - player1R > 660) {
			player1X = 660 - player1R;
		}
		if(player1Y - player1R < 0) {
			player1Y = player1R;
		}
		if(player1Y - player1R > 760) {
			player1Y = 760 - player1R;
		}
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ruleIndex++;
	    ruleIndex %= 12;
	    repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(player1.contains(e.getPoint())) {
			player1X = e.getX();
			player1Y = e.getY();
			selected = true;
		}
		
		int mx = e.getX();
		int my = e.getY();
		
		
		//Play button 
		if(mouseOver(mx, my, 250, 268, 200, 64)) {
			PaginaInicial.frame.dispose();
			JogoDoGalo jogodogalo = new JogoDoGalo();
			jogodogalo.setVisible(true);
		}
		
		//quit button 
		if(mouseOver(mx, my, 250, 368, 200, 64)) {
			System.exit(0);
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(selected) {
			mXVelo = e.getX() - player1X;
			mYVelo = e.getY() - player1Y;
		}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean mouseOver (int mx, int my, int x, int y , int width, int height) {
		if(mx > x && mx < x + width ) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		}else return false;
	}

	@Override
	public void run() {
		while (true) {

			angleGreenObstacle = (angleGreenObstacle - 3.5f) % 360;
			angleBlueObstacle = (angleBlueObstacle + 3.5f) % 360;
			spinValue += 0.01;

			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Get the code of the key pressed
				int keyCode = e.getKeyCode();
				// see what key had been pressed
				switch (keyCode) {
				case KeyEvent.VK_P: 
					PaginaInicial.frame.dispose();
					JogoDoGalo jogodogalo = new JogoDoGalo();
					jogodogalo.setVisible(true);
					break;
				
				case KeyEvent.VK_Q: 
					System.exit(0);

				}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
