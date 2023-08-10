package com.food.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.food.daoimplement.CartDAOImpl;
import com.food.daoimplement.CustomerDAOImpl;
import com.food.daoimplement.FoodDAOImpl;
import com.food.daoimplement.LoginDAOImpl;
import com.food.pojo.Cart;
import com.food.pojo.Customer;
import com.food.pojo.Food;

public class CartTest {

    public static void main(String[] args) {
        int cartId, ch = 0;
        String emailId, password;
        int foodId;
        String foodName;
        int quantity;
        double price;
        double totalPrice;

        Scanner sc = new Scanner(System.in);
        CartDAOImpl cartDAOImpl = new CartDAOImpl();
        List<Cart> cartList = new ArrayList<>();
        Cart cart = null;

        System.out.println("Enter EmailId :- ");
        emailId = sc.nextLine().trim();

        System.out.println("Enter Password :- ");
        password = sc.nextLine().trim();

        boolean validate = new LoginDAOImpl().validateCustomer(emailId, password);
        Customer customer = new CustomerDAOImpl().viewCustomerByEmailid(emailId);

        if (validate) {
            System.out.println("Login Success \nWelcome Back " + customer.getCtr_Name() + " :) ");
            do {

                menu();
                System.out.println("Select Operation: ");
                ch = sc.nextInt();

                switch (ch) {
                    case 1: // add to cart
                        // Displaying all Food Items to add food into the Cart
                        List<Food> foodlist = new FoodDAOImpl().viewAllFood();
                        for (Food food : foodlist) {
                            System.out.println(food);
                            System.out.println(
                                    "***************************************************************************************");
                        }

                        System.out.println("Enter Food ID Which You Want to add to Cart");
                        foodId = sc.nextInt();
                        System.out.println("Enter Quantity to add");
                        quantity = sc.nextInt();

                        cart = new Cart();
                        cart.setEmailId(emailId);
                        cart.setQuantity(quantity);
                        cart.setFoodId(foodId);

                        boolean added = cartDAOImpl.addToCart(cart);
                        if (added) {
                            System.out.println("Food Item Added");
                        } else {
                            System.out.println("Error While Adding to Cart");
                        }
                        break;

                    case 2: // Show my Cart
                        cartList = cartDAOImpl.viewCartByEmailId(emailId);
                        if (cartList.isEmpty() || null == cartList) {
                            System.out.println("Cart is Empty");
                        } else {
                            double bill = 0;
                            for (Cart cart1 : cartList) {
                                System.out.println(cart1);
                                bill += cart1.getTotalPrice();
                                System.out.println("*****************************************");
                            }
                            System.out.println("Total Bill Is : " + bill);
                        }
                        break;

                    case 3: // Update Quantity Of Items
                        System.out.println("Enter Cart Id: ");
                        cartId = sc.nextInt();

                        System.out.println("Enter Quantity to update");
                        quantity = sc.nextInt();

                        boolean updateCart = cartDAOImpl.updateCart(cartId, quantity);
                        if (updateCart) {
                            System.out.println("Details Updated ");
                        } else {
                            System.out.println("Error While Updating !");
                        }
                        break;

                    case 4: // delete item from Cart

                        System.out.println("Enter Cart Id To Delete Cart Items: ");
                        cartId = sc.nextInt();
                        boolean delete = cartDAOImpl.deleteCartItems(cartId);
                        if (delete) {
                            System.out.println("Delete Succesfully");
                        } else {
                            System.out.println("Error While Deleting the Item. ");
                        }
                        break;

                    case 5: // Clear My Cart

                        break;

                    case 6: // Exit
                        System.out.println("Thank You !");
                        System.exit(0);

                    default:
                        break;
                }

            } while (ch != 6);
        } else {
            System.out.println("Invalid Credentials \n :( Try Again !");
        }

        sc.close();
    }

    static void menu() {

        System.out.println("1. Add To Cart.");
        System.out.println("2. Show my Cart");
        System.out.println("3. Update Cart");
        System.out.println("4. Delete Item From Cart");
        System.out.println("5. Clear My Cart");
        System.out.println("6. Exit");
    }
}
