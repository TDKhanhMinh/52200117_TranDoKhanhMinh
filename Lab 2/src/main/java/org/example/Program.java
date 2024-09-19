package org.example;
import java.util.List;
import java.util.Scanner;

public class Program {
    private static ProductDAO productDAO;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the database connection URL as a command-line argument.");
            return;
        }

        String url = args[0];

        try {
            productDAO = new ProductDAO(url);
            runMenu();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (productDAO != null) {
                    productDAO.close();
                }
            } catch (Exception e) {
                System.out.println("Er ror closing database connection: " + e.getMessage());
            }
        }
    }

    private static void runMenu() {
        while (true) {
            System.out.println("\nProduct Management System");
            System.out.println("1. Read product list");
            System.out.println("2. Read a product by input id");
            System.out.println("3. Add a new product");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        readProductList();
                        break;
                    case 2:
                        readProductById();
                        break;
                    case 3:
                        addNewProduct();
                        break;
                    case 4:
                        updateProduct();
                        break;
                    case 5:
                        deleteProduct();
                        break;
                    case 6:
                        System.out.println("Exiting program. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void readProductList() throws Exception {
        List<Product> products = productDAO.readAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void readProductById() throws Exception {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Product product = productDAO.read(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void addNewProduct() throws Exception {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Product newProduct = new Product(0, name, price);
        int id = productDAO.add(newProduct);
        System.out.println("New product added with ID: " + id);
    }

    private static void updateProduct() throws Exception {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product existingProduct = productDAO.read(id);
        if (existingProduct == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter new name (or press enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            existingProduct.setName(name);
        }

        System.out.print("Enter new price (or -1 to keep current): ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        if (price != -1) {
            existingProduct.setPrice(price);
        }

        boolean updated = productDAO.update(existingProduct);
        if (updated) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Failed to update product.");
        }
    }

    private static void deleteProduct() throws Exception {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean deleted = productDAO.delete(id);
        if (deleted) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Failed to delete product. Product may not exist.");
        }
    }
}
