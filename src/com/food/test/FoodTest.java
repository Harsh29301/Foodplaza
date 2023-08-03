package com.food.test;

import java.util.Scanner;

import com.food.daoimplement.FoodDAOImpl;
import com.food.pojo.Food;

public class FoodTest {

    public static void main(String[] args) {

        int foodId;
        String foodName;
        String category;
        String description;
        double price;
        String foodType;
        int choice;
        Scanner sc = new Scanner(System.in);
        Food food = null;
        FoodDAOImpl foodDAOImpl = new FoodDAOImpl();

        do {
            foodMenu();
            System.out.println("Select Operation :: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter Food Name: ");
                    foodName = sc.nextLine();
                    System.out.println("Enter Food Category: ");
                    category = sc.nextLine();
                    System.out.println("Enter Food Desscription:");
                    description = sc.nextLine();
                    System.out.println("Enter Food Type: ");
                    foodType = sc.nextLine();
                    System.out.println("Enter Food Price: ");
                    price = Double.parseDouble(sc.nextLine());

                    food = new Food(foodName, category, description, price, foodType);

                    boolean addfood = foodDAOImpl.addFood(food);

                    if(addfood){
                        System.out.println("Food Item Added Successfully :) ");
                    }
                    else{
                        System.out.println("Error While Adding Food Item X Try Again ! :( ");
                    }

                    break;
            
                default:
                System.out.println("Select Operation :: ");
                    break;
            }
        } while (choice != 9);
        
    }

    static void foodMenu() {
        System.out.println("1. Add Food Item");
        System.out.println("2. Update Food Item");
        System.out.println("3. Delete Food Item");
        System.out.println("4. View Food by FoodId");
        System.out.println("5. View All Food Items");
        System.out.println("6. View Food by FoodName");
        System.out.println("7. View Food By FoodType");
        System.out.println("8. View Food By FoodCategory");
        System.out.println("9. Exit");
    }
}
