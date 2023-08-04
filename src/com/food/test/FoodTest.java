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
        String foodType, image;
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
                    System.out.println("Add Image");
                    image = sc.nextLine();

                    food = new Food(foodName, category, description, price, foodType,image);

                    boolean addfood = foodDAOImpl.addFood(food);

                    if(addfood){
                        System.out.println("Food Item Added Successfully :) ");
                    }
                    else{
                        System.out.println("Error While Adding Food Item X Try Again ! :( ");
                    }

                    break;

                case 2: // Update Food Items.
                    System.out.println("Enter Food Id: ");
                    foodId = Integer.parseInt(sc.nextLine());

                    food = foodDAOImpl.viewFoodbyFoodId(foodId);

                    if(food == null){
                        System.out.println("No Record Found :( ");
                    }
                    else{
                        System.out.println("Data Found: " + food);
                        System.out.println("Do you Really want to update the Record: (y/n)");
                        char c = sc.nextLine().charAt(0);
                        if(c == 'y' || c == 'Y'){
                            System.out.println("Enter Food Name: ");
                            foodName = sc.nextLine();
                            System.out.println("Enter Food Category: ");
                            category = sc.nextLine();
                            System.out.println("Enter Food Discription: ");
                            description = sc.nextLine();
                            System.out.println("Enter Food Type: ");
                            foodType = sc.nextLine();
                            System.out.println("Enter Food Price: ");
                            price = Double.parseDouble(sc.nextLine());


                            Food updateFood = new Food(foodName, category, description, price, foodType);
                            updateFood.setFoodId(foodId);
                            boolean update = foodDAOImpl.updateFood(updateFood);
                            if(update){
                                System.out.println("Record Updated Successfully :) ");
                            }
                            else{
                                System.out.println("Error While Updating the Record. Try Again !");
                            }
                        }
                        else{
                            System.out.println("Thank You :) ");
                        }
                    }
                    break;  
                case 5: // View Food Items By Id.
                    System.out.println("Enter Food Id: ");
                    foodId = Integer.parseInt(sc.nextLine());

                    food = foodDAOImpl.viewFoodbyFoodId(foodId);

                    if(food == null){
                        System.out.println("No Record Found :( ");
                    }
                    else{
                        System.out.println(food);
                    }
                    break;
                default:
                System.out.println("Select Operation :: ");
                    break;
            }
        } while (choice != 9);

        sc.close();
        
    }

    static void foodMenu() {
        System.out.println("1. Add Food Item");
        System.out.println("2. Update Food Item");
        System.out.println("3. Delete Food Item");
        System.out.println("4. View All Food Items ");
        System.out.println("5. View Food by FoodId");
        System.out.println("6. View Food by FoodName");
        System.out.println("7. View Food By FoodType");
        System.out.println("8. View Food By FoodCategory");
        System.out.println("9. Exit");
    }
}
