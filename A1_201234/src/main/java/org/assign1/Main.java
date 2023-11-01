package org.assign1;
import java.util.*;


interface librarian {
    void Add_Book(int book_id, String book_title, String author_book, int more_copies);
    void Remove_Book (int book_id);
    void register_member (int stud_id, String stud_name, String contact_number, int age);
    void remove_member(int stud_id);
    boolean registration_check(student s);
    void show_available_books();
    void show_borrowed_books(student s);
    void display_books();
    void display_members();
}
class library implements librarian{
    private HashMap<Integer, library_book> books = new HashMap<>();
    private HashMap<Integer, student> students = new HashMap<>();

    public library(){
        books = new HashMap<>();
        students = new HashMap<>();
    }

    public student getsid(int stud_id){
        return students.get(stud_id);
    }

    public student getstud_phone(String contact_number){
        for(student stud : students.values()){
            if (stud.getcontactnumber().equals(contact_number)) {
                return stud;
            }
        }
        return null;
    }


    @Override
    public void register_member(int stud_id, String stud_name, String contact_number, int age){
        student new_member = new student(stud_id, stud_name, contact_number, age);

        if (students.containsKey(stud_id)) {
            System.out.println("A member with the ID " + stud_id + " is already registered.");
            return;
        }

        students.put(stud_id, new_member);
        System.out.println("Member Successfully Registered with ID:" + stud_id);

    }
    @Override
    public void remove_member(int stud_id){
        if (students.containsKey(stud_id)){
            student remove_member = students.remove(stud_id);
            System.out.println("Member with ID " + remove_member.getStudID() + " removed.");
        }
        else{
            System.out.println("No member found with the given ID.");
        }
    }




    @Override
    public void Add_Book(int book_id, String book_title, String author_book, int more_copies) {
        for(library_book existing_book : books.values()){
            if (existing_book.gettitle().equals(book_title) && existing_book.getauthor().equals(author_book)){
                int updated_copies = existing_book.gettotalcopies() + more_copies;
                existing_book.settotalcopies(updated_copies);
                return;
            }

        }

        if (books.containsKey(book_id)) {
            System.out.println("Book with same ID already in the system.");
            return;
        }

        book b = new library_book(book_id, book_title, author_book, more_copies);

        books.put(book_id, (library_book) b);

        System.out.println("Book Added Successfully!");

    }
    @Override
    public void Remove_Book(int book_id){
        if(books.containsKey(book_id)){
            library_book book_remove = books.get(book_id);
            books.remove(book_id);
            System.out.println(book_remove.gettitle() + " Removed!");
        }
        else{
            System.out.println("No book found with the given ID.");
        }

    }
    @Override
    public boolean registration_check(student s){
        return students.values().contains(s);
    }

    public library_book get_book_id(int book_id){
        return books.get(book_id);
    }
    @Override
    public void show_available_books(){
        System.out.println("Books Available:");
        for (library_book b1 : books.values()) {
            if (b1.isAvailable()) {
                System.out.println("ID: " + b1.getid() + ", Title: " + b1.gettitle());
            }
        }
    }
    @Override
    public void show_borrowed_books(student s){
        System.out.println("Issued books:");
        for(library_book b1 : s.getborrowed_books()){
            System.out.println("ID: " + b1.getid() + ", Title: " + b1.gettitle());
        }
    }
    @Override
    public void display_books(){
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("List of books available: ");
        for (library_book b1 : books.values()) {
            int copies_available = b1.gettotalcopies() - b1.getBorrowed_copies();
            System.out.println("Book ID: " + b1.getid() + ", Title: " + b1.gettitle() + ", Author: " + b1.getauthor() + ", Available Copies: " + copies_available);

        }
    }
    @Override
    public  void  display_members(){
        System.out.println("List of registered members:");
        System.out.println("---------------------------");

        for(student s : students.values()){
            System.out.println("Member Name: " + s.getStudName());
            System.out.println("Member ID: " + s.getStudID());
            System.out.println("Phone Number: " + s.getcontactnumber());
            System.out.println("Age: " + s.getAge());
            if(s.fine != 0){
                System.out.println("Fine Amount: $" + s.cal_fine());
            }else{System.out.println("Fine Amount: $" + 0);}
            System.out.println("Books Issued: ");
            if (s.getborrowed_books().isEmpty()) {
                System.out.println("None");
            }
            else {
                for (library_book b1 : s.getborrowed_books()) {
                    System.out.println(b1.gettitle() + " (ID: " + b1.getid() + "), ");
                }
                System.out.println();
            }

            System.out.println("------------------------");
        }
    }

    public void issue_book(int book_id){
        library_book bti = books.get(book_id);
        if (bti != null && bti.gettotalcopies() > 0){
            bti.settotalcopies(bti.gettotalcopies() - 1);
        }
        else{
            System.out.println("Cannot issue this book. Either it is not in the library or there are no copies left.");
        }
    }

    public void return_inventory(int book_id){
        library_book b1 = books.get(book_id);
        if (b1 != null){
            b1.settotalcopies(b1.gettotalcopies() + 1);
        }
    }



}
class student{
    private int stud_id;
    private String stud_name;
    private String contact_number;
    private int age;
    double fine = 0.0;
    private int books_borrowed;
    private List<library_book> borrowed_books = new ArrayList<>();


    public student(int stud_id, String stud_name, String contact_number, int age){
        this.stud_id = stud_id ;
        this.stud_name=stud_name;
        this.contact_number=contact_number;
        this.age=age;

    }


    public void add_borrowed_book(library_book b1){
        borrowed_books.add(b1);
    }

    public void remove_borrowed_book(library_book b1){
        borrowed_books.remove(b1);
    }

    public List<library_book> getborrowed_books() {
        return borrowed_books;
    }


    public int getStudID(){
        return stud_id;
    }

    public String getStudName(){
        return  stud_name;
    }

    public String getcontactnumber(){
        return   contact_number;
    }

    public int getAge(){
        return age;
    }

    public double getfine(){
        return fine;
    }

    public void setfine(double fine){
        this.fine = fine;
    }

    public boolean Borrow(){
        return (fine == 0) && (books_borrowed < 2);
    }

    public void increment_books_borrowed(){
        this.books_borrowed++;
    }

    public void decrement_books_borrowed(){
        this.books_borrowed--;
    }

    public boolean enter_library(library lib){
        if (lib.registration_check(this)) {
            String first_name = this.stud_name.split(" ")[0];
            System.out.println("Welcome " + first_name + ". Member ID:" + stud_id);
            return true;
        } else {
            System.out.println("Member with Name: " + stud_name + " and Phone No: " + contact_number + " doesn't exist.");
            return false;
        }
    }

    public boolean can_borrow(){
        return (fine == 0) && (books_borrowed < 2);
    }

    public void book_borrow(int book_id, library lib){
        library_book b1 = lib.get_book_id(book_id);

        if (b1 == null) {
            System.out.println("No such book found.");
            return;
        }

        if (b1.gettotalcopies() > 0) {
            if (this.borrowed_books.size() < 2) {
                lib.issue_book(book_id);
                this.borrowed_books.add(b1);
                System.out.println("Book Issued Successfully!");
            } else {
                System.out.println("You are already holding two books.");
            }
        } else {
            System.out.println("The book is not available right now.");
        }
    }

    public void book_return(int book_id, library lib){
        library_book book_return = lib.get_book_id(book_id);

        if (book_return == null) {
            System.out.println("No book found.");
            return;
        }

        if (!borrowed_books.contains(book_return)) {
            System.out.println("You have not issued this book.");
            return;
        }


        double fine_amount = cal_fine();
        if (fine_amount > 0) {
            System.out.println("Book ID: " + book_id + " successfully returned. $" + fine_amount + " has been charged for a delay of " + (int)(fine_amount/3) + " days.");
        }
        else{
            System.out.println("Book successfully returned.");
        }


        book_return.setAvailable(true);
        book_return.setborrower(null);
        this.remove_borrowed_book(book_return);

        lib.return_inventory(book_id);

    }

    public double cal_fine(){
        Random rand = new Random();
        int days_after_due = rand.nextInt(10);
        fine = days_after_due*3;
        return fine;
    }

    public void display_fine(){
        System.out.println("Total fine: $" + fine + " for being" + (int)(fine/3) + " days overdue.");

    }

    public void pay_fine(){
        if (fine <= 0) {
            System.out.println("There is no fine to pay");
            return;
        }

        System.out.println("You had a total fine of $ " + fine + ". It has been paid successfully!");
        fine = 0.0;
    }

}
class book {
    private int book_id;
    private String book_title;
    private String author_book;


    public book(int book_id, String book_title, String author_book){
        this.book_id = book_id;
        this.book_title = book_title;
        this.author_book = author_book;
    }

    public book() {
    }

    public int getid(){
        return book_id;
    }

    public String gettitle(){
        return book_title;
    }

    public String getauthor(){
        return author_book;
    }
}

class library_book extends book{
    private int total_copies;
    private int available_copies;
    private boolean isAvailable;
    private student borrower;
    private final int borrowed_copies = 0;

    public library_book(int book_id, String book_title, String author_book, int total_copies){
        super(book_id, book_title, author_book);
        this.total_copies = total_copies;
        this.available_copies = total_copies;
    }



    public int gettotalcopies(){
        return total_copies;
    }

    public int getavailablecopies(){
        return available_copies;
    }

    public void settotalcopies(int total_copies){
        this.total_copies = total_copies;
        if (this.available_copies > this.total_copies) {
            this.available_copies = this.total_copies;
        }
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public void setAvailable(boolean available){
        isAvailable=available;
    }



    public void setborrower(student s){
        this.borrower = s;
    }

    public student getborrower(){
        return borrower;
    }

    public int getBorrowed_copies() {
        return borrowed_copies;
    }
}

class Application{
    private Scanner scn = new Scanner(System.in);

    private library lib = new library();


    public library getLib() {
        return lib;
    }

    public void setLib(library lib) {
        this.lib = lib;
    }

    public Scanner getScn() {
        return scn;
    }

    public void setScn(Scanner scn) {
        this.scn = scn;
    }

    private student accessing_student;

    public void enter_menu(){

        while (true) {
            System.out.println("1. Enter as a librarian");
            System.out.println("2. Enter as a member");
            System.out.println("3. Exit");

            int m1 = -1;
            if (scn.hasNextInt()) {
                m1 = scn.nextInt();
            }


            scn.nextLine();


            switch (m1) {
                case 1:
                    librarian_access();
                    break;
                case 2:
                    System.out.println("Name: ");
                    scn.nextLine();
                    scn.nextLine();
                    System.out.println("Phone No: ");
                    String pn = scn.nextLine();
                    scn.nextLine();

                    student access = lib.getstud_phone(pn);
                    if (access != null && access.enter_library(lib)) {
                        accessing_student = access;
                        student_access();
                    } else {
                        System.out.println("No member with this name and Phone No. is registered.");
                    }

                    break;
                case 3:
                    System.out.println("Thanks for visiting!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }


    }
    public void librarian_access(){

        while (true) {
            System.out.println("1. Register a member");
            System.out.println("2. Remove a member");
            System.out.println("3. Add a book");
            System.out.println("4. Remove a book");
            System.out.println("5. View all members along with their books and fine to be paid");
            System.out.println("6. View all books");
            System.out.println("7. Back");

            int m2 = -1;
            if (scn.hasNextInt()) {
                m2 = scn.nextInt();
            }
            scn.nextLine();

            switch (m2) {
                case 1:
                    System.out.println("Name: ");
                    String name = scn.nextLine();
                    scn.nextLine();
                    System.out.println("Age: ");
                    int age = scn.nextInt();
                    scn.nextLine();
                    System.out.println("Phone no: ");
                    String phone = scn.nextLine();
                    scn.nextLine();
                    System.out.println("ID: ");
                    int ID = scn.nextInt();
                    scn.nextLine();



                    lib.register_member(ID, name, phone, age);
                    break;

                case 2:
                    System.out.println("Member ID: ");
                    int id = scn.nextInt();
                    scn.nextLine();
                    lib.remove_member(id);
                    break;

                case 3:
                    System.out.println("Book ID: ");
                    int bid = scn.nextInt();
                    scn.nextLine();
                    System.out.println("Title: ");
                    String title = scn.nextLine();
                    scn.nextLine();
                    System.out.println("Author Name: ");
                    String authname = scn.nextLine();
                    System.out.println("Total Copies: ");
                    int copies = scn.nextInt();
                    scn.nextLine();
                    lib.Add_Book(bid, title, authname, copies);
                    break;

                case 4:
                    System.out.println("Book ID: ");
                    int bkid = scn.nextInt();
                    scn.nextLine();
                    lib.Remove_Book(bkid);
                    break;

                case 5:
                    lib.display_members();
                    break;

                case 6:
                    lib.display_books();
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Please Try Again.");
                    break;

            }
        }
    }

    public void student_access(){
        while (true) {
            System.out.println("1. List Available Books");
            System.out.println("2. List My Books");
            System.out.println("3. Issue book");
            System.out.println("4. Return book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Back");

            int m3 = -1;
            if (scn.hasNextInt()) {
                m3 = scn.nextInt();
            }
            scn.nextLine();


            switch (m3) {
                case 1:
                    lib.display_books();
                    break;
                case 2:
                    System.out.println("Member ID: ");
                    int mid = scn.nextInt();
                    scn.nextLine();
                    student s1 = lib.getsid(mid);
                    if(s1 != null){
                        lib.show_borrowed_books(s1);
                    }
                    else {
                        System.out.println("Invalid id");
                    }
                    break;
                case 3:
                    System.out.println("Member ID: ");
                    int mis = scn.nextInt();
                    scn.nextLine();
                    student s2 = lib.getsid(mis);
                    if (s2 != null) {
                        System.out.println("Book ID: ");
                        int b_id = scn.nextInt();
                        scn.nextLine();
                        s2.book_borrow(b_id, lib);
                    }
                    else {
                        System.out.println("Invalid ID");
                    }
                    break;

                case 4:
                    System.out.println("Enter Book ID: ");
                    int returnbook = scn.nextInt();
                    scn.nextLine();
                    accessing_student.book_return(returnbook, lib);
                    break;

                case 5:
                    accessing_student.pay_fine();
                    break;
                case 6:
                    return;

                default:
                    System.out.println("Please try again.");
                    break;
            }


        }

    }



}
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("Library Portal Initialized....");
        Application app = new  Application();
        app.enter_menu();
        }
}
