package com.food.test;

import java.util.List;
import java.util.Scanner;

import com.food.daoimplement.LoginDAOImpl;
import com.food.daoimplement.OrderFoodDAOImpl;
import com.food.pojo.OrderFood;

public class AdminTest {

    public static void main(String[] args) {

        int ch = 0;
        Scanner sc = new Scanner(System.in);
        OrderFoodDAOImpl oFoodDAOImpl = new OrderFoodDAOImpl();

        System.out.println("Enter Email Id: ");
        String emailId = sc.nextLine();
        System.out.println("Enter Password: ");
        String password = sc.nextLine();

        boolean validateAdmin = new LoginDAOImpl().validateAdmin(emailId, password);


        if (validateAdmin) {
            System.out.println("*#*#*# __Login Success ___ Welcome Admin__ *#*#*#");
            System.out.println("_________________________________________________");

            do {
                System.out.println("1. View All Orders");
                System.out.println("2. Update Order Status");
                System.out.println("3. Exit");

                System.out.println("Select Operation: ");
                ch = Integer.parseInt(sc.nextLine());

                switch (ch) {
                    case 1:
                        System.out.println("**************** Here are All Orders ****************");
                        List<OrderFood> orderList = oFoodDAOImpl.viewAllOrders();

                        for (OrderFood orderFood : orderList) {
                            System.out.println(orderFood);
                        }

                        break;
                    case 2:

                    System.out.println("Enter New Order Status: ");
                    String update = sc.nextLine();
                    System.out.println("Enter Order id to Update the Status: ");
                    int temp = Integer.parseInt(sc.nextLine());

                    Boolean updateStatus = oFoodDAOImpl.updateOrderStatus(update, temp);
                    if(updateStatus){
                        System.out.println("Order Updated");
                    }
                    else{
                        System.out.println(
                            "Error While Updating..."
                        );
                    }
                    break;

                    case 3: 
                    System.out.println("Thank You !");
                    System.exit(0);

                    default:
                        break;
                }
            } while (ch != 3);
        }
        else{
            System.out.println("Invalid Admin Email and Password");
            System.out.println(
                "Try Again..."
            );
        }
        sc.close();
    }
    
}
