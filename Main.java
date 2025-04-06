import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String args[]) {
        Scanner obj = new Scanner(System.in);
        while (true) {
            System.out.println("==========================================");
            System.out.println("  LITERARY WORKSPACE MANAGEMENT SYSTEM  ");
            System.out.println("==========================================");
            System.out.println("1. View List of Available Books");
            System.out.println("2. I am a Writer");
            System.out.println("3. I am a Reader");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = obj.nextInt();
            obj.nextLine();
            switch (choice) {
                case 1:
                    int counter = 1;
                    System.out.println("==========================================");
                    System.out.println("         LIST OF AVAILABLE BOOKS          ");
                    System.out.println("==========================================");
                    try {
                        BufferedReader br = new BufferedReader(new FileReader("BookList.csv"));
                        String line;
                        while ((line = br.readLine()) != null) {
                            String data[] = line.split(",");
                            if (data.length >= 3) {
                                String title = data[0].trim();
                                String author = data[1].trim();
                                String lastEdited = data[2].trim();
                                System.out.println("==========================================");
                                System.out.println(counter + ".    Book Title: " + title);
                                System.out.println("      Author: " + author);
                                System.out.println("      Last Edited: " + lastEdited);
                                System.out.println("==========================================");
                                counter++;
                            }
                        }
                        br.close();
                        if (counter == 1) {
                            System.out.println("    No books available currently !  ");
                        }
                    } catch (IOException e) {
                        System.out.println("Error Reading the Available Books.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Your Good Name: ");
                    String name = obj.nextLine();
                    WriterPerson wp = new WriterPerson(name);
                    break;

                case 3:
                    System.out.print("Enter Your Good Name: ");
                    name = obj.nextLine();
                    ReaderPerson rp = new ReaderPerson(name);
                    break;

                case 4:
                    System.out.println("==========================================");
                    System.out.println("      THANK YOU FOR USING OUR SYSTEM      ");
                    System.out.println("==========================================");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice !");
            }
        }
    }
}