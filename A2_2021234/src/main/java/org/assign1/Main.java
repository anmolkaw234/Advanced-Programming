package org.assign1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

abstract class zoo_func {
    private String name;
    private String email;
    private String password;

    public zoo_func(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract void displayAdminMenu();
    public abstract void displayVisitorMenu();
}

interface user_type {
    void displayAdminMenu();
    void displayVisitorMenu();

}

class admin extends zoo_func{

    private String specialDeal;

    public admin(String name, String email, String password) {
        super(name, email, password);
        this.specialDeal = "No special deals available";
    }

    public String getSpecialDeal() {
        return specialDeal;
    }

    public void setSpecialDeal(String specialDeal) {
        this.specialDeal = specialDeal;
    }

    @Override
    public void displayAdminMenu() {

        throw new UnsupportedOperationException("Unimplemented method 'displayAdminMenu'");
    }

    @Override
    public void displayVisitorMenu() {

        throw new UnsupportedOperationException("Unimplemented method 'displayVisitorMenu'");
    }

}

class visitor extends zoo_func{

    private int age;
    private double balance;

    public visitor(String name, String email, String password, int age, double balance) {
        super(name, email, password);
        this.age = age;
        this.balance = balance;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public Object getPhoneNumber() {
        return null;
    }

    @Override
    public void displayAdminMenu() {

        throw new UnsupportedOperationException("Unimplemented method 'displayAdminMenu'");
    }

    @Override
    public void displayVisitorMenu() {

        throw new UnsupportedOperationException("Unimplemented method 'displayVisitorMenu'");
    }

    public void buyMembership(String membershipType, double amount, String discountCode) {

        if (!"None".equals(discountCode)) {
            System.out.println("Discount code is not yet implemented. Full price will be charged.");
        }
        if (balance >= amount) {
            balance -= amount;
            System.out.println(membershipType + " purchased successfully. Your balance is now ₹" + balance + ".");
        } else {
            System.out.println("Insufficient balance. Please top-up your balance and try again.");
        }
    }

    public void buyTicket(String attractionName, double ticketPrice) {
        if (balance >= ticketPrice) {
            balance -= ticketPrice;
            System.out.println("The ticket for " + attractionName + " was purchased successfully. Your balance is now ₹" + balance + ".");
        } else {
            System.out.println("Insufficient balance. Please top-up your balance and try again.");
        }
    }


}

class visitor_management {

    private List<visitor> registeredVisitors = new ArrayList<>();


    public void registerVisitor(visitor newVisitor) {
        for (visitor v : registeredVisitors) {
            if (v.getEmail().equals(newVisitor.getEmail()) || v.getPhoneNumber().equals(newVisitor.getPhoneNumber())) {
                System.out.println("Email or Phone Number already exists!");
                return;
            }
        }
        registeredVisitors.add(newVisitor);
        System.out.println("Registration successful!");
    }


    public visitor login(String email, String password) {
        for (visitor v : registeredVisitors) {
            if (v.getEmail().equals(email) && v.getPassword().equals(password)) {
                return v;
            }
        }
        System.out.println("Login failed. Incorrect email or password.");
        return null;
    }

    public List<visitor> getRegisteredVisitors() {
        return registeredVisitors;
    }

    public void setRegisteredVisitors(List<visitor> registeredVisitors) {
        this.registeredVisitors = registeredVisitors;
    }

    public visitor_management() {
        this.registeredVisitors = new ArrayList<>();
    }
}


class animal {
    private String name;
    private String type;
    private String description;

    public animal(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String makeNoise() {
        switch(type) {
            case "Mammal": return "Generic mammal noise";
            case "Bird": return "Chirp";
            case "Fish": return "Blub";
            case "Amphibian": return "Ribbit";
            default: return "Unknown noise";
        }
    }
}

class attraction {
    private String name;
    private String description;
    private double ticketPrice;

    public attraction(String name, String description, double ticketPrice) {
        this.name = name;
        this.description = description;
        this.ticketPrice = ticketPrice;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

class animal_management extends admin {
    private List<animal> animals;

    public animal_management(String name, String email, String password) {
        super(name, email, password);
        this.animals = new ArrayList<>();
    }

    public void add_animal(animal a) {
        animals.add(a);
    }

    public void remove_animal(animal a) {
        animals.remove(a);
    }

    public List<animal> getAnimals() {
        return animals;
    }

    public boolean updateAnimalDetails(int animalIndex, String newName, String newType, String newDescription) {
        if (animalIndex >= 0 && animalIndex < animals.size()) {
            animal anim = animals.get(animalIndex);
            anim.setName(newName);
            anim.setType(newType);
            anim.setDescription(newDescription);
            return true;
        }
        return false;
    }

    public boolean removeAnimal(int animalIndex) {
        if (animalIndex >= 0 && animalIndex < animals.size()) {
            animals.remove(animalIndex);
            return true;
        }
        return false;
    }
}

class attraction_management extends admin {
    private List<attraction> attractions;

    public attraction_management(String name, String email, String password) {
        super(name, email, password);
        this.attractions = new ArrayList<>();
    }



    public void add_attraction(attraction a) {
        attractions.add(a);
    }

    public void remove_attraction(attraction a) {
        attractions.remove(a);
    }

    public List<attraction> getAttractions() {
        return attractions;
    }

    public boolean modifyAttraction(int attractionIndex, String newName, String newDescription, double newPrice) {
        if (attractionIndex >= 0 && attractionIndex < attractions.size()) {
            attraction attr = attractions.get(attractionIndex);
            attr.setName(newName);
            attr.setDescription(newDescription);
            attr.setTicketPrice(newPrice);
            return true;
        }
        return false;
    }

    public boolean removeAttraction(int attractionIndex) {
        if (attractionIndex >= 0 && attractionIndex < attractions.size()) {
            attractions.remove(attractionIndex);
            return true;
        }
        return false;
    }
}


class discount_manager {
    private Map<String, Double> discounts;

    public discount_manager() {
        this.discounts = new HashMap<>();
    }

    public void addDiscount(String code, double percentage) {
        discounts.put(code, percentage);

    }

    public double getDiscount(String code) {
        return discounts.getOrDefault(code, 0.0);
    }

    public Map<String, Double> getDiscounts() {
        return discounts;
    }

    public boolean removeDiscount(String code) {
        if (discounts.containsKey(code)) {
            discounts.remove(code);
            return true;
        }
        return false;
    }
}

class ExploringVisitor extends visitor {
    private Map<String, Integer> attractionTickets;
    private animal_management animalManager;
    private attraction_management attractionManager;

    public ExploringVisitor(String name, String email, String password, int age, double balance) {
        super(name, email, password, age, balance);
        this.attractionTickets = new HashMap<>();
        this.animalManager = null;
        this.attractionManager = null;
    }

    public void buyTickets(String attractionName, int count) {
        int currentTickets = attractionTickets.getOrDefault(attractionName, 0);
        attractionTickets.put(attractionName, currentTickets + count);
    }

    public void visitAttraction(String attractionName) {
        int currentTickets = attractionTickets.getOrDefault(attractionName, 0);
        if (currentTickets > 0) {
            attractionTickets.put(attractionName, currentTickets - 1);
        } else {
            System.out.println("No tickets available for this attraction.");
        }
    }



    public Map<String, Integer> getAttractionTickets() {
        return attractionTickets;
    }

    public void setAttractionTickets(Map<String, Integer> attractionTickets) {
        this.attractionTickets = attractionTickets;
    }

    public animal_management getAnimalManager() {
        return animalManager;
    }

    public void setAnimalManager(animal_management animalManager) {
        this.animalManager = animalManager;
    }

    public attraction_management getAttractionManager() {
        return attractionManager;
    }

    public void setAttractionManager(attraction_management attractionManager) {
        this.attractionManager = attractionManager;
    }


}

class feedback {
    private String visitorEmail;
    private String feedbackText;

    public feedback(String visitorEmail, String feedbackText) {
        this.visitorEmail = visitorEmail;
        this.feedbackText = feedbackText;
    }

    public String getVisitorEmail() {
        return visitorEmail;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public void setVisitorEmail(String visitorEmail) {
        this.visitorEmail = visitorEmail;
    }
}

class feedback_management {
    private List<feedback> feedbacks = new ArrayList<>();

    public void addFeedback(feedback newFeedback) {
        feedbacks.add(newFeedback);
    }

    public List<feedback> getAllFeedbacks() {
        return feedbacks;
    }
}

class visitor_stats {
    private int totalVisitors;
    private double totalRevenue;
    private String mostPopularAttraction;

    public visitor_stats(int totalVisitors, double totalRevenue, String mostPopularAttraction) {
        this.totalVisitors = totalVisitors;
        this.totalRevenue = totalRevenue;
        this.mostPopularAttraction = mostPopularAttraction;
    }

    public int getTotalVisitors() {
        return totalVisitors;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public String getMostPopularAttraction() {
        return mostPopularAttraction;
    }

    public void setTotalVisitors(int totalVisitors) {
        this.totalVisitors = totalVisitors;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setMostPopularAttraction(String mostPopularAttraction) {
        this.mostPopularAttraction = mostPopularAttraction;
    }
}

class admin_authentication {

    private String adminID = "admin123";
    private String password = "password123";

    public boolean authenticate(String enteredID, String enteredPassword) {
        return adminID.equals(enteredID) && password.equals(enteredPassword);
    }
}

class ZooTopia_System implements user_type {
    private admin adminUser;
    private visitor visitorUser;
    private discount_manager discountMgr;
    private visitor_management visitorMgr = new visitor_management();
    animal_management animalMgr = new animal_management("erge", "efge", "er");
    attraction_management attractionMgr = new attraction_management("dfgg", "dgf", "dfgdfg");
    Scanner sc = new Scanner(System.in);
    feedback_management feedbackMgr;

    public ZooTopia_System() {
        adminUser = new admin("DefaultAdmin", "admin@zootopia.com", "admin123");
        discountMgr = new discount_manager();
    }

    public admin getAdminUser() {
        return adminUser;
    }

    public visitor getVisitorUser() {
        return visitorUser;
    }

    public void setAdminUser(admin adminUser) {
        this.adminUser = adminUser;
    }

    public void setVisitorUser(visitor visitorUser) {
        this.visitorUser = visitorUser;
    }

    private admin_authentication auth = new admin_authentication();

    public boolean adminLogin() {
        System.out.println("Admin Login:");
        System.out.print("Enter Admin ID: ");
        String enteredID = sc.nextLine();
        System.out.print("Enter Password: ");
        String enteredPassword = sc.nextLine();

        if (auth.authenticate(enteredID, enteredPassword)) {
            System.out.println("Login Successful!");
            return true;
        } else {
            System.out.println("Incorrect ID or Password.");
            return false;
        }
    }


    public void displayMainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to ZooTopia!!!");

        while (true) {
            System.out.println("1. Enter as Admin");
            System.out.println("2. Enter as Visitor");
            System.out.println("3. View Special Deals");
            System.out.println("4. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    if(adminLogin()){
                        displayAdminMenu();
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("1. Register");
                        System.out.println("2. Login");
                        System.out.println("3. Back");

                        int choice2 = sc.nextInt();
                        sc.nextLine();
                        switch (choice2) {
                            case 1:
                                System.out.print("Enter Name: ");
                                String name = sc.nextLine();
                                System.out.print("Enter Age: ");
                                int age = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Phone Number: ");
                                String phone_number = sc.nextLine();
                                System.out.print("Enter Balance: ");
                                double balance = sc.nextDouble();
                                sc.nextLine();
                                System.out.print("Enter Email: ");
                                String email = sc.nextLine();
                                System.out.print("Enter Password: ");
                                String password = sc.nextLine();

                                visitor newVisitor = new visitor(name, email, password, age, balance);
                                visitorMgr.registerVisitor(newVisitor);

                                break;
                            case 2:
                                System.out.print("Enter Email: ");
                                String loginEmail = sc.nextLine();
                                System.out.print("Enter Password: ");
                                String loginPassword = sc.nextLine();
                                visitor loggedInVisitor = visitorMgr.login(loginEmail, loginPassword);
                                if (loggedInVisitor != null) {

                                    displayVisitorMenu();
                                }
                                break;
                            case 3:
                                displayMainMenu();


                            default:
                                System.out.println("Invalid Choice. Try Again");

                        }
                    }
                case 3:
                    System.out.println(adminUser.getSpecialDeal());
                    break;
                case 4:
                    System.out.println("Thank you for visiting ZooTopia!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    @Override
    public void displayVisitorMenu() {


        while (true) {
            System.out.println("Visitor Menu:");
            System.out.println("1. Explore the Zoo");
            System.out.println("2. Buy Membership");
            System.out.println("3. Buy Tickets");
            System.out.println("4. View Discounts");
            System.out.println("5. View Special Deals");
            System.out.println("6. Visit Animals");
            System.out.println("7. Visit Attractions");
            System.out.println("8. Leave Feedback");
            System.out.println("9. Log Out");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:

                    exploreZooMenu();
                    break;
                case 2:

                    System.out.println("Buy Membership:");
                    System.out.println("1. Basic Membership (₹20)");
                    System.out.println("2. Premium Membership (₹50)");
                    System.out.print("Enter your choice: ");
                    int membershipChoice = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Apply Discount Code: ");
                    String discountCode = sc.nextLine();
                    if (membershipChoice == 1) {
                        visitorUser.buyMembership("Basic Membership", 20, discountCode);
                    } else if (membershipChoice == 2) {
                        visitorUser.buyMembership("Premium Membership", 50, discountCode);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 3:

                    System.out.println("Buy Tickets:");
                    System.out.print("Enter the number of tickets: ");
                    int numTickets = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Select an Attraction to Buy a Ticket:");
                    List<attraction> attractions = attractionMgr.getAttractions();
                    int attractionCount = 1;
                    for (attraction attr : attractions) {
                        System.out.println(attractionCount + ". " + attr.getName() + " (₹" + attr.getTicketPrice() + ")");
                        attractionCount++;
                    }
                    int attractionChoice = sc.nextInt();
                    sc.nextLine();
                    if (attractionChoice > 0 && attractionChoice <= attractions.size()) {
                        attraction selectedAttraction = attractions.get(attractionChoice - 1);
                        visitorUser.buyTicket(selectedAttraction.getName(), selectedAttraction.getTicketPrice() * numTickets);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 4:
                    Map<String, Double> discounts = discountMgr.getDiscounts();
                    if (discounts.isEmpty()) {
                        System.out.println("No discounts available at the moment.");
                    } else {
                        System.out.println("Available Discounts:");
                        for (Map.Entry<String, Double> discountEntry : discounts.entrySet()) {
                            System.out.println("Discount Code: " + discountEntry.getKey() + " - " + discountEntry.getValue() + "% off");
                        }
                    }
                    break;
                case 5:
                    System.out.println(adminUser.getSpecialDeal());
                    break;
                case 6:

                    List<animal> animals = animalMgr.getAnimals();
                    if (animals.isEmpty()) {
                        System.out.println("No animals available at the moment.");
                    } else {
                        System.out.println("Animals in ZooTopia:");
                        int animalCount = 1;
                        for (animal anim : animals) {
                            System.out.println(animalCount + ". " + anim.getName() + " - " + anim.getType() + " - " + anim.getDescription());
                            animalCount++;
                        }
                        System.out.print("Enter the number of the animal you wish to visit: ");
                        int animalChoice = sc.nextInt();
                        if (animalChoice > 0 && animalChoice <= animals.size()) {
                            System.out.println("You are now visiting: " + animals.get(animalChoice - 1).getName());
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 7:

                    visitAttractionMenu();
                    break;
                case 8:

                    System.out.println("Please provide your feedback below:");
                    String feedbackText = sc.nextLine();
                    feedback newFeedback = new feedback(visitorUser.getEmail(), feedbackText);
                    feedbackMgr.addFeedback(newFeedback);
                    System.out.println("Thank you for your feedback!");
                    break;

                case 9:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


    }
    public void visitAttractionMenu() {

        while (true) {
            System.out.println("Visit Attractions:");
            System.out.println("Select an Attraction to Visit:");
            List<attraction> attractions = attractionMgr.getAttractions();
            int attractionCount = 1;
            for (attraction attr : attractions) {
                System.out.println(attractionCount + ". " + attr.getName());
                attractionCount++;
            }
            System.out.println(attractionCount + ". Exit");

            int attractionChoice = sc.nextInt();
            if (attractionChoice == attractionCount) {
                break;
            } else if (attractionChoice > 0 && attractionChoice < attractionCount) {
                System.out.println("Ticket not available. Basic Members need to buy separate tickets for the attractions.");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public void exploreZooMenu() {

        attraction_management attractionMgr = new attraction_management("Keshav", "sdfgaea", "drf4343 ");
        animal_management animalMgr = new animal_management("dgerg", "dfger", "dfger");

        while (true) {
            System.out.println("Explore the Zoo:");
            System.out.println("1. View Attractions");
            System.out.println("2. View Animals");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    List<attraction> attractions = attractionMgr.getAttractions();
                    for (attraction attr : attractions) {
                        System.out.println(attr.getName() + " - " + attr.getDescription() + " - $" + attr.getTicketPrice());
                    }
                    break;
                case 2:
                    List<animal> animals = animalMgr.getAnimals();
                    for (animal anim : animals) {
                        System.out.println(anim.getName() + " - " + anim.getType() + " - " + anim.getDescription());
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    @Override
    public void displayAdminMenu() {

        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Attractions");
            System.out.println("2. Manage Animals");
            System.out.println("3. Schedule Events");
            System.out.println("4. Set Discounts");
            System.out.println("5. Set Special Deal");
            System.out.println("6. View Visitor Stats");
            System.out.println("7. View Feedback");
            System.out.println("8. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    manageAttractionsMenu();
                    break;
                case 2:
                    manageAnimalsMenu();
                    break;
                case 3:
                    scheduleEvents();
                    break;
                case 4:
                    setDiscountsMenu();
                    break;
                case 5:
                    setSpecialDeal();
                    break;
                case 6:
                    viewVisitorStats();
                    break;
                case 7:
                    viewFeedbacks();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void viewFeedbacks() {
        if (feedbackMgr == null) {
            System.out.println("Feedback Manager is not initialized.");
            return;
        }

        List<feedback> feedbacks = feedbackMgr.getAllFeedbacks();
        if (feedbacks == null) {
            System.out.println("Error fetching feedbacks.");
            return;
        }

        if (feedbacks.isEmpty()) {
            System.out.println("No feedback available at the moment.");
        } else {
            System.out.println("Visitor Feedbacks:");
            for (feedback fb : feedbacks) {
                if (fb != null) {
                    System.out.println("Email: " + (fb.getVisitorEmail() != null ? fb.getVisitorEmail() : "Unknown"));
                    System.out.println("Feedback: " + (fb.getFeedbackText() != null ? fb.getFeedbackText() : "No feedback text"));
                    System.out.println("----------------------------");
                }
            }
        }
    }

    public void viewVisitorStats() {

        visitor_stats stats = new visitor_stats(1000, 20000, "Jungle Safari");  // Sample data
        System.out.println("Total Visitors: " + stats.getTotalVisitors());
        System.out.println("Total Revenue: ₹" + stats.getTotalRevenue());
        System.out.println("Most Popular Attraction: " + stats.getMostPopularAttraction());
    }

    public void setSpecialDeal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Set Special Deal:");
        System.out.print("Enter Special Deal Description: ");
        String dealDescription = sc.nextLine();
        adminUser.setSpecialDeal(dealDescription);
        System.out.println("Special Deal has been set successfully!");
    }

    public void setDiscountsMenu() {


        while (true) {
            System.out.println("Set Discounts:");
            System.out.println("1. Add Discount");
            System.out.println("2. Modify Discount");
            System.out.println("3. Remove Discount");
            System.out.println("4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Discount Code: ");
                    String code = sc.nextLine();
                    System.out.print("Enter Discount Percentage (0-100): ");
                    double percentage = sc.nextDouble();
                    discountMgr.addDiscount(code, percentage);
                    System.out.println("Discount added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Discount Code to Modify: ");
                    String modifyCode = sc.nextLine();
                    System.out.print("Enter New Discount Percentage (0-100): ");
                    double newPercentage = sc.nextDouble();
                    discountMgr.addDiscount(modifyCode, newPercentage);
                    System.out.println("Discount modified successfully!");
                    break;
                case 3:
                    System.out.print("Enter Discount Code to Remove: ");
                    String removeCode = sc.nextLine();

                    if (discountMgr.removeDiscount(removeCode)) {
                        System.out.println("Discount removed successfully.");
                    } else {
                        System.out.println("Discount not found or could not be removed.");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void scheduleEvents() {


        System.out.println("Schedule Events:");
        System.out.print("Enter Event Name: ");
        String eventName = sc.nextLine();
        sc.nextLine();
        System.out.print("Enter Event Date (format: dd/mm/yyyy): ");
        String eventDate = sc.nextLine();
        sc.nextLine();
        System.out.print("Enter Event Time (format: HH:MM): ");
        String eventTime = sc.nextLine();
        sc.nextLine();
        System.out.print("Enter Event Description: ");
        String eventDescription = sc.nextLine();
        sc.nextLine();


        System.out.println("Event '" + eventName + "' scheduled for " + eventDate + " at " + eventTime + " has been added successfully!");
    }

    public void manageAttractionsMenu() {

        attraction_management attractionMgr = new attraction_management(adminUser.getName(), adminUser.getEmail(), adminUser.getPassword());

        while (true) {
            System.out.println("Manage Attractions:");
            System.out.println("1. Add Attraction");
            System.out.println("2. View Attractions");
            System.out.println("3. Modify Attraction");
            System.out.println("4. Remove Attraction");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Attraction Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Attraction Description: ");
                    String description = sc.nextLine();
                    System.out.print("Enter Ticket Price: ");
                    double price = sc.nextDouble();
                    attractionMgr.add_attraction(new attraction(name, description, price));
                    System.out.println("Attraction added successfully!");
                    break;
                case 2:
                    List<attraction> attractions = attractionMgr.getAttractions();
                    for (attraction attr : attractions) {
                        System.out.println(attr.getName() + " - " + attr.getDescription() + " - $" + attr.getTicketPrice());
                    }
                    break;
                case 3:
                    System.out.print("Enter Attraction Name to Modify: ");
                    String modifyName = sc.nextLine();

                    System.out.print("Enter the number of the attraction you wish to modify: ");
                    int attractionIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = sc.nextLine();
                    System.out.print("Enter new price: ");
                    double newPrice = sc.nextDouble();
                    if (attractionMgr.modifyAttraction(attractionIndex, newName, newDescription, newPrice)) {
                        System.out.println("Attraction modified successfully.");
                    } else {
                        System.out.println("Failed to modify attraction.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Attraction Name to Remove: ");
                    String removeName = sc.nextLine();

                    System.out.print("Enter the number of the attraction you wish to remove: ");
                    attractionIndex = sc.nextInt() - 1;
                    if (attractionMgr.removeAttraction(attractionIndex)) {
                        System.out.println("Attraction removed successfully.");
                    } else {
                        System.out.println("Failed to remove attraction.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void manageAnimalsMenu() {
        animal_management animalMgr = new animal_management(adminUser.getName(), adminUser.getEmail(), adminUser.getPassword());

        while (true) {
            System.out.println("Manage Animals:");
            System.out.println("1. Add Animal");
            System.out.println("2. Update Animal Details");
            System.out.println("3. Remove Animal");
            System.out.println("4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Animal Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Animal Type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter Animal Description: ");
                    String description = sc.nextLine();
                    animalMgr.add_animal(new animal(name, type, description));
                    System.out.println("Animal added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Animal Name to Update: ");
                    String updateName = sc.nextLine();

                    System.out.print("Enter the number of the animal you wish to update: ");
                    int animalIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new type: ");
                    String newType = sc.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = sc.nextLine();
                    if (animalMgr.updateAnimalDetails(animalIndex, newName, newType, newDescription)) {
                        System.out.println("Animal details updated successfully.");
                    } else {
                        System.out.println("Failed to update animal details.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Animal Name to Remove: ");
                    String removeName = sc.nextLine();

                    System.out.print("Enter the number of the animal you wish to remove: ");
                    animalIndex = sc.nextInt() - 1;
                    if (animalMgr.removeAnimal(animalIndex)) {
                        System.out.println("Animal removed successfully.");
                    } else {
                        System.out.println("Failed to remove animal.");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public admin_authentication getAuth() {
        return auth;
    }

    public void setAuth(admin_authentication auth) {
        this.auth = auth;
    }

    public discount_manager getDiscountMgr() {
        return discountMgr;
    }

    public void setDiscountMgr(discount_manager discountMgr) {
        this.discountMgr = discountMgr;
    }

    public visitor_management getVisitorMgr() {
        return visitorMgr;
    }

    public void setVisitorMgr(visitor_management visitorMgr) {
        this.visitorMgr = visitorMgr;
    }
}

public class Main {
    public static void main(String[] args) {
        ZooTopia_System system = new ZooTopia_System();
        system.displayMainMenu();
    }
}
