
public class CartesianMain {
	public static void main(String args[]){

		Polygon pol = Polygon.askPolygon();
		pol.printSummary();
		System.out.println("Now for the winding number: ");
		Vector vect = Vector.askVector();
		if(pol.isInside(vect)) System.out.println("inside");
		else System.out.println("outside");
		PolygonFrame pf = new PolygonFrame(pol);	
	}
}
