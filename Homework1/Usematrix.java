package homework1;

public class Usematrix {
	public static void main(String[] args) {
		try{
		matrix m = new matrix(8, 8);
		m.printMatrix();
		}catch(NullPointerException e){
			System.out.println("Sorry, check input data again");			
		}
	}

}
