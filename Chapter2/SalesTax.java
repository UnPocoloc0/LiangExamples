import java.util.Scanner;
public class SalesTax {
		public static void main(String[] args) {
		// Input Stream -> keyboard
		Scanner input = new Scanner(System.in); 
		
		System.out.print("Enter purchase amount: ");
		double purchaseAmount = input.nextDouble(); 
		
		double tax = purchaseAmount * 0.06;
		// Truncate not round -> old way
		System.out.println("Sales tax is $" + (int)(tax * 100) / 100.0);
	} 
}
