/**
 * Git Level 2 - Merge vs Rebase Demo
 * Simple calculator to practice different merge strategies
 */
public class Calculator {
    private double result;
    
    public Calculator() {
        this.result = 0;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public double subtract(double a, double b) {
        return a - b;
    }
    
    // More operations will be added in feature branches
    
    public void demo() {
        System.out.println("Git Level 2 - Merge vs Rebase Demo");
        System.out.println("==================================");
        
        System.out.println("Basic Calculator Operations:");
        System.out.println("Addition: 5 + 3 = " + add(5, 3));
        System.out.println("Subtraction: 10 - 4 = " + subtract(10, 4));
        
        System.out.println("\nPerfect for practicing:");
        System.out.println("- Feature branch merging");
        System.out.println("- Rebase vs merge strategies");
        System.out.println("- Conflict resolution");
    }
    
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.demo();
    }
} 