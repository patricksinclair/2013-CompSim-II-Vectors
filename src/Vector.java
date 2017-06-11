import java.util.InputMismatchException;
import java.util.Scanner;


public class Vector {
//simple vector object with  x and y co-ordinates
	private double x, y;

	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX(){
		return x;
	}
	public void setX(double x){
		this.x = x;
	}
	public double getY(){
		return y;
	}
	public void setY(double y){
		this.y = y;
	}


//method for finding distance from origin/magnitude.
	public double magnitude(){
		return Math.sqrt(getX()*getX() + getY()*getY());
	}



	public static double dotProduct(Vector a, Vector b){	
		return a.getX()*b.getX() + a.getY()*b.getY();
	}

	public static double perpendicularDotProduct(Vector a, Vector b){
		return a.getX()*b.getY() - a.getY()*b.getX();
	}

	public static Vector subtractedVector(Vector a, Vector b){

		double xc = a.getX() - b.getX();
		double yc = a.getY() - b.getY();

		return new Vector(xc, yc);
	}


//method for user input of vector, performs checks on input, eg,
	public static Vector askVector(){

		Scanner keyboard = new Scanner(System.in);
		double x = 0, y = 0;
		String str = null;

		outerloop:
			while(true){
				System.out.println("Enter the x,y values of the vector.");
				str = keyboard.next();
				String[] tokens = str.split(",");

				if(tokens.length != 2){
					System.out.println("Please use only 2 values, seperated by a comma.");
					continue outerloop;
				}

				inloop1:
					while(true){
						try{
							Scanner xScan = new Scanner(tokens[0]);
							x = xScan.nextDouble();
							break inloop1;
						}catch(InputMismatchException e){
							System.out.println("Please enter a number for the x value.");
							keyboard.nextLine();
							continue outerloop;
						}
					}


				while(true){
					try{
						Scanner yScan = new Scanner(tokens[1]);
						y = yScan.nextDouble();
						break outerloop;
					}catch(InputMismatchException e){
						System.out.println("Please enter a number for the y value.");
						keyboard.nextLine();
						continue outerloop;
					}
				}
			}
		return new Vector(x, y);
	}



}
