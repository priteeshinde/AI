import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Customer Chatbot!");

        while (true) {
            System.out.print("Customer: ");
            String userInput = scanner.nextLine();
            String botResponse = generateResponse(userInput);
            System.out.println("Chatbot: " + botResponse);

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Chatbot: Goodbye! Have a nice day.");
                break;
            }
        }

        scanner.close();
    }

    public static String generateResponse(String userInput) {
        // Simple predefined responses based on user input
        String response;

        if (userInput.equalsIgnoreCase("hi") || userInput.equalsIgnoreCase("hello")) {
            response = "Hello! How can I assist you today?";
        } else if (userInput.equalsIgnoreCase("how are you?")) {
            response = "I'm a chatbot, so I don't have feelings, but thank you for asking!";
        } else if (userInput.equalsIgnoreCase("help")) {
            response = "How can I help you? Feel free to ask any questions.";
        } else if (userInput.equalsIgnoreCase("thank you") || userInput.equalsIgnoreCase("thanks")) {
            response = "You're welcome! If you need any further assistance, feel free to ask.";
        } else {
            response = "I'm sorry, I didn't understand. Can you please rephrase or ask a different question?";
        }

        return response;
    }
}
