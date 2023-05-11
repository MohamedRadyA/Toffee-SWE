import controllers.CatalogController;
import controllers.Database;
import controllers.UserController;
import controllers.CartController;

import java.util.Scanner;

public class Main {
    private static UserController userController;
    private static CatalogController catalogController;
    private static CartController cartController;

    public static void main(String[] args) {
        Database.init();
        userController = new UserController();
        cartController = new CartController();
        catalogController = new CatalogController(cartController);

        System.out.println("Welcome to Toffee Shop..\nPlease choose the option you would like to do:\n");
        while (true)
            showMenu();
    }

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        if (userController.getCurrentUser() == null) { //Not logged in view
            System.out.println("1. Register a new user\n2. Log in\n3. View catalog\n4. Exit\n");
            int option = sc.nextInt();
            switch (option) {
                case 1: userController.handleRegister(); break;
                case 2: userController.handleLogin(); break;
                case 3: catalogController.displayCatalogItems(); break;
                default:
                    System.exit(0);
            }
        } else {
            System.out.println("1. View catalog\n2. View shopping cart\n3. Logout\n4. Exit\n");
            int option = sc.nextInt();
            switch (option) {
                case 1: catalogController.viewCatalog(); break;
                case 2: cartController.viewCart(); break;
                case 3:
                    userController.logout();
                    cartController = new CartController();
                    catalogController = new CatalogController(cartController);
                    break;
                default:
                    System.exit(0);
            }
        }
    }

}