import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public Book(String bookId, String title, String author, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", ISBN: " + isbn +
                ", Status: " + (isBorrowed ? "Borrowed" : "Available");
    }
}

class Member {
    private String memberId;
    private String name;
    private String contactInfo;
    private List<String> borrowedBookIds;

    public Member(String memberId, String name, String contactInfo) {
        this.memberId = memberId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBookIds = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<String> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public void borrowBook(String bookId) {
        borrowedBookIds.add(bookId);
    }

    public void returnBook(String bookId) {
        borrowedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + ", Name: " + name + ", Contact: " + contactInfo +
                ", Borrowed Books: " + borrowedBookIds;
    }
}

class Library {
    private Map<String, Book> books;
    private Map<String, Member> members;

    public Library() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
    }

    public void addBook(Book book) {
        if (!books.containsKey(book.getBookId())) {
            books.put(book.getBookId(), book);
            System.out.println("Book added successfully: " + book.getTitle());
        } else {
            System.out.println("Book with ID " + book.getBookId() + " already exists.");
        }
    }

    public void addMember(Member member) {
        if (!members.containsKey(member.getMemberId())) {
            members.put(member.getMemberId(), member);
            System.out.println("Member added successfully: " + member.getName());
        } else {
            System.out.println("Member with ID " + member.getMemberId() + " already exists.");
        }
    }

    public void displayAvailableBooks() {
        System.out.println("\n--- Available Books ---");
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        boolean found = false;
        for (Book book : books.values()) {
            if (!book.isBorrowed()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books currently available.");
        }
    }

    public void displayBorrowedBooks() {
        System.out.println("\n--- Borrowed Books ---");
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        boolean found = false;
        for (Book book : books.values()) {
            if (book.isBorrowed()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books are currently borrowed.");
        }
    }

    public void displayMembers() {
        System.out.println("\n--- Library Members ---");
        if (members.isEmpty()) {
            System.out.println("No members in the library.");
            return;
        }
        for (Member member : members.values()) {
            System.out.println(member);
        }
    }

    public void borrowBook(String bookId, String memberId) {
        if (!books.containsKey(bookId)) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }
        if (!members.containsKey(memberId)) {
            System.out.println("Member with ID " + memberId + " not found.");
            return;
        }

        Book book = books.get(bookId);
        Member member = members.get(memberId);

        if (book.isBorrowed()) {
            System.out.println("Book with ID " + bookId + " is already borrowed.");
        } else {
            book.setBorrowed(true);
            member.borrowBook(bookId);
            System.out.println("Book " + book.getTitle() + " borrowed successfully by " + member.getName());
        }
    }

    public void returnBook(String bookId, String memberId) {
        if (!books.containsKey(bookId)) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }
        if (!members.containsKey(memberId)) {
            System.out.println("Member with ID " + memberId + " not found.");
            return;
        }

        Book book = books.get(bookId);
        Member member = members.get(memberId);

        if (!book.isBorrowed()) {
            System.out.println("Book with ID " + bookId + " is not currently borrowed.");
        } else if (!member.getBorrowedBookIds().contains(bookId)) {
            System.out.println("Member " + member.getName() + " did not borrow book with ID " + bookId + ".");
        } else {
            book.setBorrowed(false);
            member.returnBook(bookId);
            System.out.println("Book " + book.getTitle() + " returned successfully by " + member.getName());
        }
    }

    public Book findBook(String bookId) {
        return books.get(bookId);
    }

    public Member findMember(String memberId) {
        return members.get(memberId);
    }
}

public class OnlineLibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Display Available Books");
            System.out.println("4. Display Borrowed Books");
            System.out.println("5. Display Members");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(bookId, title, author, isbn));
                    break;
                case "2":
                    System.out.print("Enter Member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Contact Info: ");
                    String contactInfo = scanner.nextLine();
                    library.addMember(new Member(memberId, name, contactInfo));
                    break;
                case "3":
                    library.displayAvailableBooks();
                    break;
                case "4":
                    library.displayBorrowedBooks();
                    break;
                case "5":
                    library.displayMembers();
                    break;
                case "6":
                    System.out.print("Enter Book ID to borrow: ");
                    String borrowBookId = scanner.nextLine();
                    System.out.print("Enter your Member ID: ");
                    String borrowMemberId = scanner.nextLine();
                    library.borrowBook(borrowBookId, borrowMemberId);
                    break;
                case "7":
                    System.out.print("Enter Book ID to return: ");
                    String returnBookId = scanner.nextLine();
                    System.out.print("Enter your Member ID: ");
                    String returnMemberId = scanner.nextLine();
                    library.returnBook(returnBookId, returnMemberId);
                    break;
                case "8":
                    System.out.println("Exiting the Library Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
