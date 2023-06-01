package example;


public class GeneratePrompt {
    public static String generatePromptOne(String animal) {
        String capitalizedAnimal = animal.substring(0, 1).toUpperCase() + animal.substring(1).toLowerCase();
        return "Suggest three names for an animal that is a superhero.\n\n" +  
        "Animal: Cat\n" +
        "Names: Captain Sharpclaw, Agent Fluffball, The Incredible Feline\n" +
        "Animal: Dog\n" +
        "Names: Ruff the Protector, Wonder Canine, Sir Barks-a-Lot\n" +
        "Animal: " + capitalizedAnimal + "\n" +
        "Names:";
    }
    public static String generatePromptTwo() {
        return "Title: Refactoring for Single Responsibility Principle\n\n" +
                "Context:\n" +
                "You are working on an e-commerce application, and one of the key classes in the system is Order. The Order class is responsible for handling the creation of an order as well as sending it for processing. However, this violates the Single Responsibility Principle, which states that a class should have only one reason to change.\n\n" +
                "Problem:\n" +
                "Identify the violation of the Single Responsibility Principle in the Order class and propose a refactoring solution.\n\n" +
                "Task:\n" +
                "Refactor the Order class to adhere to the Single Responsibility Principle by separating the creation and processing responsibilities into separate classes.\n\n" +
                "Guidelines:\n" +
                "1. Create a new class called OrderProcessor that will be responsible for handling the processing logic.\n" +
                "2. Move the code responsible for processing an order from the Order class to the OrderProcessor class.\n" +
                "3. The Order class should retain its responsibility for handling order details, such as customer information, order items, and shipping details.\n" +
                "4. Ensure that the Order class collaborates with the OrderProcessor class appropriately to perform the necessary processing tasks.\n\n" +
                "Constraints:\n" +
                "- Do not modify the existing functionality of the Order class related to order creation and order details.\n" +
                "- The OrderProcessor class should handle the processing logic and any associated tasks, such as sending notifications or updating the inventory.\n" +
                "- Aim to achieve a clean separation of concerns between the Order class and the OrderProcessor class.\n\n" +
                "Test Cases:\n" +
                "Consider the following scenarios to verify the correctness of your solution:\n" +
                "1. Create an Order object and verify that the order details are correctly stored.\n" +
                "2. Process an order using the OrderProcessor class and ensure that the appropriate processing tasks are performed.\n\n" +
                "Example Solution:\n" +
                "java\n" +
                "Copy code\n" +
                "// Order.java\n" +
                "public class Order {\n" +
                "    private String customerId;\n" +
                "    private List<OrderItem> orderItems;\n" +
                "    private Address shippingAddress;\n" +
                "\n" +
                "    // Constructors, getters, and setters for order details\n" +
                "\n" +
                "    // Existing code for order creation and order details\n" +
                "}\n" +
                "\n" +
                "// OrderProcessor.java\n" +
                "public class OrderProcessor {\n" +
                "    public void processOrder(Order order) {\n" +
                "        // Code for processing the order, including any additional tasks\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "// Usage in the application\n" +
                "Order order = new Order();\n" +
                "// Set order details\n" +
                "\n" +
                "OrderProcessor orderProcessor = new OrderProcessor();\n" +
                "orderProcessor.processOrder(order);\n" +
                "Additional Resources:\n" +
                "Link to SOLID principles documentation\n" +
                "Link to Single Responsibility Principle (SRP) article\n" +
                "Link to SOLID principles in Java\n" +
                "Link to SOLID principles in software development";
    }
    
}

