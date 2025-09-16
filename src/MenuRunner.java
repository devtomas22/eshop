import java.util.*;

public class MenuRunner {
    public static void runMenuUntilQuit(Scanner scanner, Map<String, Runnable> optionMap) {
        boolean[] flag = new boolean[] { true };
        Map<String, Runnable> optionMapCopy = new LinkedHashMap<>(optionMap);
        optionMapCopy.put("Quit", () -> flag[0] = false );
        while (flag[0]) {
            runMenu(scanner, optionMapCopy);
        }
    }
    public static void runMenu(Scanner scanner, Map<String, Runnable> optionMap) {
        List<String> keys = new ArrayList<>(optionMap.keySet());

        while (true) {
            // Print menu options with indexes
            for (int i = 0; i < keys.size(); i++) {
                System.out.println(i + ": " + keys.get(i));
            }
            System.out.print("Enter a number:  ");
            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
                if (choice >= 0 && choice < keys.size()) {
                    // Execute corresponding function
                    optionMap.get(keys.get(choice)).run();
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.\n");
            }
        }
    }
}
