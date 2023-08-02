package com.food.test;

import java.util.List;
import java.util.Scanner;

import com.food.daoimplement.CustomerDAOImpl;
import com.food.pojo.Customer;

public class CustomerTest {

    public static void main(String[] args) {

        int choice;
        Scanner sc = new Scanner(System.in);
        String ctr_Name;
        String emailId;
        String password;
        String phoneno;
        String address;
        Customer customer = null;
        CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();

        do {
            displayMenu(); // Display Menu Method which is Static writen inside the Test Class.
            System.out.println("Select Operation :: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:// Add Customer
                    System.out.println("Enter Name: ");
                    ctr_Name = sc.nextLine();
                    System.out.println("Enter Email Id: ");
                    emailId = sc.nextLine();
                    System.out.println("Enter Password: ");
                    password = sc.nextLine();
                    System.out.println("Enter Phone Number: ");
                    phoneno = sc.nextLine();
                    System.out.println("Enter Address: ");
                    address = sc.nextLine();
                    customer = new Customer(ctr_Name, emailId, password, phoneno, address);

                    boolean addCustomer = customerDAOImpl.addCustomer(customer);

                    if (addCustomer) {
                        System.out.println("You are Registered Successfully with us.\n Welcome :) ");
                    } else {
                        System.out.println("Error! While Registering ! Try Again :( ");
                    }
                    break;

                case 2:  // Update Customer 

                    System.out.println("Enter Email Id: ");
                    emailId = sc.nextLine();

                    customer = customerDAOImpl.viewCustomerByEmailid(emailId);
                    if (customer == null) {
                        System.err.println("No Record Found :(");
                    } else {
                        System.out.println("Data Found : " + customer);
                        System.out.println("Do you really want to update the Record: (y/n)");
                        char c = sc.nextLine().charAt(0);
                        if (c == 'y' || c == 'Y') {
                            System.out.println("Enter Name: ");
                            ctr_Name = sc.nextLine();
                            System.out.println("Enter Password: ");
                            password = sc.nextLine();
                            System.out.println("Enter Phone Number: ");
                            phoneno = sc.nextLine();
                            System.out.println("Enter Address: ");
                            address = sc.nextLine();

                            Customer updateCustomer = new Customer(ctr_Name, emailId, password, phoneno, address);
                            boolean update = customerDAOImpl.updateCustomer(updateCustomer);
                            if (update) {
                                System.out.println("Record Updated Successfully :) ");
                            } else {
                                System.err.println("Error While updating the Record. Try Again !");
                            }
                        } else {
                            System.out.println("Thank You :) ");
                        }
                    }
                    break;

                case 3:  // Delete Customer

                    System.out.println("Enter Email Id: ");
                    emailId = sc.nextLine();

                    customer = customerDAOImpl.viewCustomerByEmailid(emailId);

                    if(customer == null){
                        System.err.println("No Record Found :(");
                    }
                    else
                    {
                        System.out.println("Do You Really Want To delete the Record ? (y/n)");
                        char c = sc.nextLine().charAt(0);
                        if(c == 'y' || c == 'Y'){
                            boolean delete = customerDAOImpl.deleteCustomer(emailId);
                            if(delete){
                                System.out.println("Record Deleted Successfully ");
                            }
                        }
                        else{
                            System.out.println("Thank You :) ");
                        }
                    }
                    break;

                case 4: // Show All Customer Details (Admin Access Only.)
                    List<Customer> customersList = customerDAOImpl.viewAllCustomers();
                    for (Customer ncustomer : customersList) {
                        System.out.println(ncustomer);
                    }
                    break;
                case 5:// View Customer By Email Id.

                    System.out.println("Enter Email Id: ");
                    emailId = sc.nextLine();

                    customer = customerDAOImpl.viewCustomerByEmailid(emailId);

                    if (customer == null) {
                        System.err.println("No Record Found :( ");
                    } else {
                        System.out.println(customer);
                    }
                    break;

                case 6: // Exit Case
                    System.out.println("Thank You :) ");
                    break;
                default:  // Defaulter
                    System.out.println("Select Appropriate Operation. ");
                    break;
            }
        } while (choice != 6);
        sc.close();
    }

    public static void displayMenu() {
        System.out.println(
                "1. Add Customer\n2. Update Customer\n3. Delete Customer\n4. View Customer\n5. View Customer By EmailId\n6. Exit");

    }
}
