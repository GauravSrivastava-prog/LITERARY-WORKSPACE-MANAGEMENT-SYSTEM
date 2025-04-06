import java.io.*;
import java.util.Scanner;

class ReaderPerson extends Person {
    public ReaderPerson(String name) {
        super(name);
        Menu();
    }

    @Override
    public void Menu() {
        while (true) {
            System.out.println("==========================================");
            System.out.println("              Reader's Menu               ");
            System.out.println("==========================================");
            System.out.println("Hello " + getName() + "!");
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Search a book via Book Title");
            System.out.println("2. List all the books written by a Author");
            System.out.println("3. Exit to Main Menu");
            System.out.println("==========================================");
            int choice = scanner.nextInt();
            scanner.nextLine();
            performAction(choice);
        }
    }

    @Override
    public void performAction(int choice) {
        switch (choice) {
            case 1:
                Scanner scanner2 = new Scanner(System.in);
                System.out.print("Enter Book Title: ");
                String bookTitle = scanner2.nextLine();
                try (BufferedReader filReader = new BufferedReader(new FileReader(bookTitle + ".txt"))) {
                    String line;
                    System.out.println("Reading Book: " + bookTitle);
                    System.out.println("==========================================");
                    while ((line = filReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    System.out.println("==========================================");
                    System.out.println("Reading Completed !\n");
                } catch (IOException e) {
                    System.out.println("Error Reading Book !");
                }
                break;
            case 2:
                System.out.print("Enter Author Name: ");
                Scanner scanner3 = new Scanner(System.in);
                String authorName = scanner3.nextLine();
                System.out.println("==========================================");
                System.out.println("        Books Written by " + authorName.toUpperCase() + ":  ");
                System.out.println("==========================================");
                boolean found = false;
                try {
                    BufferedReader br = new BufferedReader(new FileReader("BookList.csv"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        String data[] = line.split(",");
                        if (data.length >= 3) {
                            String title = data[0].trim();
                            String author = data[1].trim();
                            if (author.equalsIgnoreCase(authorName)) {
                                System.out.println("Book Title: " + title);
                                found = true;
                            }
                        }
                    }
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error Reading the books by this author.");
                }
                if (!found) {
                    System.out.println("No books found by this author.");
                }
                break;

            case 3:
                System.out.println("Exiting Reading Mode...");
                Main.main(null);
                break;

            default:
                System.out.println("Invalid Choice !");
        }
    }
}
