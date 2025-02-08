import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {
        // Predefined username and password for authentication
        String correctUsername = "admin";
        String correctPassword = "password123";
        
        Scanner scanner = new Scanner(System.in);
        int attempts = 3; // Maximum login attempts
        
        while (attempts > 0) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            
            System.out.print("Enter password: ");
            String password = readPassword(scanner);
            
            // Check if entered credentials match the correct ones
            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                System.out.println("\nLogin successful! Welcome, " + username + "!");
                break; // Exit the loop on successful login
            } else {
                attempts--; // Reduce the remaining attempts
                System.out.println("Incorrect username or password. Attempts left: " + attempts);
                
                if (attempts == 0) {
                    System.out.println("Too many failed attempts. Access denied.");
                }
            }
        }
        
        scanner.close(); // Close scanner to prevent memory leak
    }
    
    // Method to read password and mask it as '*'
    private static String readPassword(Scanner scanner) {
        StringBuilder password = new StringBuilder();
        
        while (true) {
            char ch = scanner.next().charAt(0); // Read a single character
            
            if (ch == '\n' || ch == '\r') { // Stop reading on Enter
                break;
            } else if (ch == '\b' && password.length() > 0) { // Handle backspace
                password.deleteCharAt(password.length() - 1);
                System.out.print("\b ");
            } else {
                password.append(ch);
                System.out.print("*"); // Mask character as '*'
            }
        }
        
        return password.toString();
    }
}
