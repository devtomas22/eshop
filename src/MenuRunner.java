import java.util.*;

public class MenuRunner {
    public static void runMenu(Map<String, Runnable> optionMap) {
        Scanner scanner = new Scanner(System.in);

        List<String> keys = new ArrayList<>(optionMap.keySet());

        while (true) {
            // Print menu options with indexes
            for (int i = 0; i < keys.size(); i++) {
                System.out.println(i + ": " + keys.get(i));
            }
            System.out.print("Enter a number (or Ctrl+C to exit): ");
            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
                if (choice >= 0 && choice < keys.size()) {
                    // Execute corresponding function
                    optionMap.get(keys.get(choice)).run();
                } else {
                    System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.\n");
            }
        }
    }
}
