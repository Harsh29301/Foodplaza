package com.food.test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.food.daoimplement.CartDAOImpl;
import com.food.daoimplement.LoginDAOImpl;
import com.food.daoimplement.OrderFoodDAOImpl;
import com.food.pojo.Cart;
import com.food.pojo.OrderFood;

public class OrderTest {
    
    public static void main(String[] args) {
        
        Integer ch = 0;
        Scanner sc = new Scanner(System.in);
        OrderFoodDAOImpl orderFoodDAOImpl = new OrderFoodDAOImpl();
        CartDAOImpl cartDAOImpl = new CartDAOImpl();

        System.out.println("Enter Email ID: ");
        String emailId = sc.nextLine();
        System.out.println("Enter Password: ");
        String password = sc.nextLine();

        boolean validateUser = new LoginDAOImpl().validateCustomer(emailId, password);

        OrderFood order = null;
        if(validateUser){
            System.out.println("Login Successfully Welcome " + emailId);
            System.out.println("_______________________________________");

            do{
                System.out.println("1. Place Order");
                System.out.println("2. View Order History");
                System.out.println("3. View Order Details by Order Id");
                System.out.println("4. Cancel Order");
                System.out.println("5. Exit");

                System.out.println("Select Operation: ");
                ch = Integer.parseInt(sc.nextLine());

                switch (ch) {
                    case 1:
                        List<Cart> myCart = cartDAOImpl.viewCartByEmailId(emailId);
                        if(myCart.isEmpty() || myCart == null){
                            System.out.println("Your Cart is Empty. Please add items to cart.");
                        }
                        else{
                            System.out.println("Your Cart Items: --");
                            for (Cart cart : myCart) {
                                System.out.println(cart);
                            }

                            System.out.println("Proceed to Buy (y/n)");
                            char c = sc.nextLine().charAt(0);
                            if(c == 'y' || c =='Y'){
                                order = new OrderFood();
                                order.setOrderStatus("Processing");
                                order.setEmailId(emailId);

                                boolean placeOrder = orderFoodDAOImpl.placeOrder(order);
                                if(placeOrder){
                                    System.out.println("Your Order has Been Placed :) ");
                                    LocalDateTime deliveryDate = LocalDateTime.now().plusDays(8);
                                    System.out.println("Your Order Will be Delivered on : " + deliveryDate);
                                }
                                else{
                                    System.out.println("Error While Placing the order ..... Try Again !");
                                }
                            }
                            else{
                                System.out.println("Thank You");
                            }
                        }
                        System.out.println("***************************************************************");
                        break;
                
                    case 2:
                    List<OrderFood> mylist = orderFoodDAOImpl.viewMyOrderHistory(emailId);

                    if(mylist == null || mylist.isEmpty()){
                        System.out.println("No Orders to show. Please place order first.");   
                    }
                    else{
                        for (OrderFood orderFood : mylist) {
                            System.out.println(orderFood);
                        }
                    }
                    break;

                    case 3: 
                    System.out.println("Enter Order Id to display Detials: ");
                    int temp = Integer.parseInt(sc.nextLine());
                    List<OrderFood> dataList = orderFoodDAOImpl.viewOrderByOrderId(temp);
                    if(dataList == null || dataList.isEmpty()){
                        System.out.println("No Orders to show. Please place order first.");   
                    }
                    else{
                        for (OrderFood orderFood : dataList) {
                            System.out.println("***************************************************");
                            System.out.println("Order Id: " + orderFood.getOrderId());
                            System.out.println("Cart Id: " + orderFood.getCartId());
                            System.out.println("Mail Id: " + orderFood.getEmailId());
                            System.out.println("Food Id: " + orderFood.getFoodId());
                            System.out.println("Food Name: " + orderFood.getFoodName());
                            System.out.println("Quantity: " + orderFood.getQuantity());
                            System.out.println("Total Price: " + orderFood.getTotalPrice());
                            System.out.println("***************************************************");
                        }
                    }
                    break;

                    case 5: 
                        System.out.println("Thank You For Shopping With US :) ");
                        System.exit(0);
                    default:
                        break;
                }
            }while(ch != 5);
        }
        else{
            System.out.println("Invalid User Try Again :( ");
        }

        sc.close();

    }
}
