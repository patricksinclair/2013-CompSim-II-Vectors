import java.util.ArrayList;
import java.util.Scanner;


public class Polygon {
	private ArrayList<Vector> vectors;

	public Polygon(ArrayList<Vector> vectors){
		this.vectors = vectors;
	}

	public ArrayList<Vector> getVectors(){
		return vectors;
	}
	public void setVectors(ArrayList<Vector> vectors){
		this.vectors = vectors;
	}


	public ArrayList<Vector> edgeVectors(){

		ArrayList<Vector> edgeVectors = new ArrayList<Vector>();

		for(int i = 0; i < getVectors().size(); i++){
			int j = i+1;
			if(i == getVectors().size()-1) j = 0;

			Vector edge = Vector.subtractedVector(getVectors().get(i), getVectors().get(j));
			edgeVectors.add(edge);
		}
		return edgeVectors;
	}



	public boolean convex(){

		int nPos = 0;
		int nNeg = 0;

		for(int i = 0; i < edgeVectors().size(); i++){
			int j = i+1;
			if(i == edgeVectors().size()-1) j = 0;

			double perpDot = Vector.perpendicularDotProduct(edgeVectors().get(i), edgeVectors().get(j));
			if(perpDot >=0) nPos++;
			else nNeg++;
		}

		if(nPos == edgeVectors().size() || nNeg == edgeVectors().size()) return true;

		return false;
	}



	public void printSummary(){

		double mag1 = getVectors().get(0).magnitude();
		double dotProd = Vector.dotProduct(getVectors().get(0), getVectors().get(1));

		String strM = String.format("The magnitude of the first vector is: %.2f", mag1);
		String strD = String.format("The dot product of the first two vectors is: %.2f", dotProd);
		String strC = null;

		if(convex()) strC = "The polygon is convex.";
		else strC = "The polygon is concave.";

		System.out.println(strM);
		System.out.println(strD);
		System.out.println(strC);		
	}


	public static Polygon askPolygon(){

		Scanner keyboard = new Scanner(System.in);
		ArrayList<Vector> vectors = new ArrayList<Vector>();
		int nVects = 1;
		String str = null;

		while(true){

			System.out.println("Press any key to enter the values for vector no."+nVects+". "
					+ "\nOr press q to finish.");
			str = keyboard.next();

			if(str.equals("q") && vectors.size() < 2) System.out.println("Please enter at least 2 vectors.");
			if(str.equals("q") && vectors.size() >= 2) break;
			Vector vect = Vector.askVector();
			vectors.add(vect);
			nVects++;
		}
		return new Polygon(vectors);
	}


	public int[] xVals(){

		int[] xVals = new int[getVectors().size()];
		for(int i = 0; i < getVectors().size(); i++){
			xVals[i] = (int)getVectors().get(i).getX();
		}
		return xVals;
	}
	
	public int[] yVals(){
		
		 int[] yVals = new int[getVectors().size()];
		for(int i = 0; i < getVectors().size(); i++){
			yVals[i] = (int)getVectors().get(i).getY();
		}
		 return yVals;
	}
	
	public int[] xVals(int scale){

		int[] xVals = new int[getVectors().size()];
		for(int i = 0; i < getVectors().size(); i++){
			xVals[i] = (int)getVectors().get(i).getX()*scale;
		}
		return xVals;
	}
	
	public int[] yVals(int scale){
		
		 int[] yVals = new int[getVectors().size()];
		for(int i = 0; i < getVectors().size(); i++){
			yVals[i] = (int)getVectors().get(i).getY()*scale;
		}

		 return yVals;
	}
	
	public int xMax(){
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < xVals().length; i++){
			if(Math.abs(xVals()[i]) > max) max = Math.abs(xVals()[i]);
		}
		return max;
	}
	public int yMax(){
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < yVals().length; i++){
			if(Math.abs(yVals()[i]) > max) max = Math.abs(yVals()[i]);
		}
		return max;
	}
	
	
	public double windingNumber(Vector vect){
		
		double runningtotal = 0;
		
		for(int i = 0; i < getVectors().size(); i++){
			int j = i+1;
			if(i == getVectors().size()-1) j = 0;
			
			Vector vi = Vector.subtractedVector(getVectors().get(i), vect);
			Vector vj = Vector.subtractedVector(getVectors().get(j), vect);
			
			double numerator = Vector.dotProduct(vi, vj);
			double denominator = vi.magnitude()*vj.magnitude();
			double thetai = Math.acos(numerator/denominator);
			double sign = Math.signum(Vector.perpendicularDotProduct(vi, vj));
			runningtotal += sign*thetai;
		}
		return Math.round(runningtotal/2*Math.PI);
	}
	
	
	public boolean isInside(Vector vect){
		
		if(windingNumber(vect)==0)return false;
		return true;
	}
	
	
	
}
