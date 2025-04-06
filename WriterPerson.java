import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriterPerson extends Person {
    public WriterPerson(String name) {
        super(name);
        Menu();
    }

    @Override
    public void Menu() {
        while (true) {
            System.out.println("==========================================");
            System.out.println("              Writer's Menu               ");
            System.out.println("==========================================");
            System.out.println("Hello " + getName() + "!");
            System.out.println();
            System.out.println("1. Write a new book");
            System.out.println("2. Edit an existing book");
            System.out.println("3. Delete a book");
            System.out.println("4. Go back to Main Menu");
            System.out.println("==========================================");
            System.out.print("Enter choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            performAction(choice);
        }
    }

    @Override
    public void performAction(int choice) {
        String title = "", content = "";
        switch (choice) {
            case 1:
                Scanner scanner2 = new Scanner(System.in);
                System.out.print("Enter Book Title: ");
                title = scanner2.nextLine();
                System.out.println("Start Writing Your Book: ");
                content = scanner2.nextLine();
                writeBook(title, content, getName());
                break;

            case 2:
                Scanner scanner3 = new Scanner(System.in);
                System.out.print("Enter Book Title: ");
                title = scanner3.nextLine();
                editBook(title, scanner3, getName());
                break;

            case 3:
                Scanner scanner4 = new Scanner(System.in);
                System.out.print("Enter Book Title: ");
                title = scanner4.nextLine();
                System.out.print("Enter Author Name: ");
                String author = scanner4.nextLine();
                deleteBook(title, author);
                break;

            case 4:
                System.out.println("Exiting Writing Mode...");
                Main.main(null);
                break;

            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void writeBook(String title, String content, String author) {
        try (FileWriter fileWriter = new FileWriter(title + ".txt")) {
            fileWriter.write(content);
            System.out.println("Book '" + title + "' written successfully!");
            updateBookList(title, author, author, true);
        } catch (IOException e) {
            System.out.println("Error Writing the book !");
        }
    }

    public void editBook(String title, Scanner scanner3, String author) {
        File bookFile = new File(title + ".txt");
        if (!bookFile.exists()) {
            System.out.println("Book does not exist!");
            return;
        }
        try (BufferedReader filReader = new BufferedReader(new FileReader(title + ".txt"))) {
            String line;
            System.out.println("Reading Book: " + title);
            System.out.println("This is the current content of the book: ");
            System.out.println("==========================================");
            while ((line = filReader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("==========================================");
        } catch (IOException e) {
            System.out.println("Error Reading Book !");
        }

        System.out.println("Enter New Content: ");
        String content = scanner3.nextLine();

        try (FileWriter fileWriter = new FileWriter(title + ".txt", true)) {
            fileWriter.write("\n" + content);
            System.out.println("Book '" + title + "' written successfully!");
            updateBookList(title, null, author, false);
        } catch (IOException e) {
            System.out.println("Error Editing the book !");
        }
    }

    public void updateBookList(String title, String author, String latestAuthor, boolean isnewBook) {
        String bookList = "BookList.csv";
        if (isnewBook == true) {
            try (FileWriter listwriter = new FileWriter(bookList, true)) {
                listwriter.write("\n" + title + "," + author + "," + latestAuthor);
            } catch (IOException e) {
                System.out.println("Error in updating the book list file for a new Author !");
            }
        } else {
            File inputFile = new File("BookList.csv");
            File tempFile = new File("temp_books_list.csv");
            try (BufferedReader listReader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String line;
                boolean found = false;
                while ((line = listReader.readLine()) != null) {
                    String data[] = line.split(",", -1);
                    if (data.length >= 3) {
                        String bTitle = data[0].trim();
                        String bauthor = data[1].trim();
                        String blatestAuthor = data[2].trim();
                        if (bTitle.equalsIgnoreCase(title)) {
                            blatestAuthor = latestAuthor;
                            found = true;
                            System.out.println("Updating book: " + title);
                        }
                        writer.write(bTitle + "," + bauthor + "," + blatestAuthor);
                        writer.newLine();
                    }
                }
                if (!found) {
                    System.out.println("Book not found in the list!");
                }
            } catch (IOException e) {
                System.out.println("Error updating the book list file!");
            }

            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Error renaming temp file !");
                }
            }
        }
    }

    public static void deleteBook(String bookTitleToDelete, String authorToDelete) {
        File inputFile = new File("BookList.csv");
        File tempFile = new File("temp_books_list.csv");

        boolean deleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (data.length >= 3) {
                    String bookTitle = data[0].trim();
                    String author = data[1].trim();
                    if (bookTitle.equalsIgnoreCase(bookTitleToDelete.trim())
                            && author.equalsIgnoreCase(authorToDelete.trim())) {
                        deleted = true;
                        File bookFile = new File(bookTitleToDelete + ".txt");
                        if (bookFile.exists()) {
                            if (bookFile.delete()) {
                                System.out.println("Book file '" + bookTitleToDelete + "' deleted successfully.");
                            } else {
                                System.out.println("Failed to delete the book file: " + bookTitleToDelete);
                            }
                        } else {
                            System.out.println("Book file not found: " + bookTitleToDelete);
                        }
                        continue;
                    }
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the book.");
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }

        if (deleted) {
            System.out.println(
                    "Book '" + bookTitleToDelete + "' by author '" + authorToDelete + "' deleted successfully.");
        } else {
            System.out.println("Book not found in the list....");
        }
    }
}