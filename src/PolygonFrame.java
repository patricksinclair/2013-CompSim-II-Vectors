import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



class PolygonPanel extends JPanel{
	private int X, Y;
	private double x = 0, y = 0;
	Polygon pol;
	Vector vect;
	
	
	public PolygonPanel(Polygon pol){
		this.pol = pol;
		vect = new Vector(x, y);
		setBackground(Color.ORANGE);
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		X = getWidth();
		Y = getHeight();
		int xScale = getWidth()/(pol.xMax()+5);
		int yScale = -1*getHeight()/(pol.yMax()+5);
		
		int[] xVals = pol.xVals(xScale);
		int[] yVals = pol.yVals(yScale);
		
		g2d.translate(X/2, Y/2);
		g2d.setColor(Color.BLACK);
		g2d.fillPolygon(xVals, yVals, pol.getVectors().size());
		
		if(pol.isInside(vect))g2d.setColor(Color.GREEN);
		else g2d.setColor(Color.BLUE);
		
		g2d.fillOval((int)(vect.getX()*xScale), (int)(vect.getY()*yScale), 7, 7);
	}
	
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	
	public Vector getVector(){
		return vect;
	}
	public void setVector(Vector vect){
		this.vect = vect;
	}
}




public class PolygonFrame extends JFrame{
	
	private int X = 600;
	private int Y = 600;
	
	Polygon pol;
	PolygonPanel polpan;
	JPanel control;
	JLabel label;
	JTextField intext;
	
	public PolygonFrame(Polygon pol){
		this.pol = pol;
		polpan = new PolygonPanel(pol);
		control = new JPanel();
		label = new JLabel("Enter the co-ordinates of the point: ");
		intext = new JTextField(5);
		
		intext.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				intext.setText("");
			}
		});
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		control.add(label);
		control.add(intext);
		
		setLayout(new BorderLayout());
		getContentPane().add(polpan, BorderLayout.CENTER);
		getContentPane().add(control, BorderLayout.SOUTH);
		pack();
		
		setBackground(Color.GRAY);
		setTitle("Polygon");
		setSize(X, Y);
		setLocation(200, 0);
		setVisible(true);
		
		revector();
	}
	
	
	public void revector(){
		intext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String str = intext.getText();
				String[] tokens = str.split(",");
				double x = Double.parseDouble(tokens[0]);
				double y = Double.parseDouble(tokens[1]);
				polpan.setVector(new Vector(x,y));
				repaint();
			}
		});
	}

}
