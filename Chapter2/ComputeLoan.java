import java.util.Scanner; 

// Lainalaskuri -> pankki laskee lainan kuukausierän
public class ComputeLoan {
        public static void main(String[] args) {
                // Create a Scanner -> Read users inpur
                Scanner input = new Scanner(System.in);
                // Enter annual interest (vuosikorko) rate in percentage, e.g., 7.25%
                System.out.print("Enter annual interest rate, e.g., 7.25%: ");
                double annualInterestRate = input.nextDouble();
                // Obtain monthly interest rate
        // Convert to decimal & divide to all months  
        // Same as / 12 & / 100 -> / 1200
                double monthlyInterestRate = annualInterestRate / 1200;
                // Enter number of years
                System.out.print(
                        "Enter number of years as an integer, e.g., 5: ");
                int numberOfYears = input.nextInt();
                // Enter loan amount
                System.out.print("Enter loan amount, e.g., 120000.95: ");
                double loanAmount = input.nextDouble();
                // Calculate payment
        // Annuiteettikaava: 
        // Kaavassa on monta jakoviivaa
                double monthlyPayment = loanAmount * monthlyInterestRate / (1
                        - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
                
        double totalPayment = monthlyPayment * numberOfYears * 12;
        
        // Display results (2 decimals)
        System.out.println("The monthly payment is $" + (int)(monthlyPayment * 100) / 100.0);
        System.out.println("The total payment is $" + (int)(totalPayment * 100) / 100.0);
            }
    }

// Annuiteetti sisältää koron, lyhennykset ja maksu loppuu tasan laina ajan loppuun 
