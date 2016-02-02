/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;

/**
 *
 * @author gauripulekar
 */
public class DataSetCreator {

    /**
     * @param args the command line arguments
     */
    static final String ATOZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static void main(String[] args) {
        
        System.out.println("----- Step 1: Creating dataset begins -----");
        
        Random randomGenerator = new Random();
        StringBuilder stringBuilderObj;
        
        String custFileName = "customer_dataset.txt", transFileName = "transaction_dataset.txt";
        
        int nameLength, nameLengthMax = 20, nameLengthMin = 10;        
        int age, ageMax=70, ageMin=10;
        int countryCode, countryCodeMax=10, countryCodeMin=1;       
        float salary, salaryMin = 100.0f, salaryMax = 10000.0f;        
        int customerID, customerIDMax=50000, customerIDMin=1;
        float transTotal, transTotalMin = 10.0f, transTotalMax = 1000.0f;   
        int transNumItems, transNumItemsMax=10, transNumItemsMin=1;
        int transDescLength, transDescLengthMax = 50, transDescLengthMin = 20;       
       
        System.out.println("----- Step 1.1: Creating Customer dataset begins -----");
        
        //ID: unique sequential number (integer) from 1 to 50,000 (that is the file will have 50,000 line)
        //Name: random sequence of characters of length between 10 and 20 (do not include commas)
        //Age: random number (integer) between 10 to 70
        //CountryCode: random number (integer) between 1 and 10
        //Salary: random number (float) between 100 and 10000
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(custFileName))))
        {
            for(int custID=1; custID<=50000; custID++)
            {   
                age = randomGenerator.nextInt(ageMax - ageMin + 1) + ageMin;
                nameLength = randomGenerator.nextInt(nameLengthMax - nameLengthMin + 1) + nameLengthMin;
                stringBuilderObj = new StringBuilder(nameLength);
                for(int i = 0; i < nameLength; i++ ) 
                    stringBuilderObj.append(ATOZ.charAt(randomGenerator.nextInt(ATOZ.length())));

                countryCode = randomGenerator.nextInt(countryCodeMax) + countryCodeMin;
                salary = randomGenerator.nextFloat() * (salaryMax - salaryMin) + salaryMin;
                //System.out.println(custID +","+stringBuilderObj.toString()+","+age+","+countryCode+","+salary);
                out.println(custID +","+stringBuilderObj.toString()+","+age+","+countryCode+","+salary);
            }
        }
        catch(IOException e)
        {
            System.out.println("Error occurred while created Customer dataset.");
        }
        
        System.out.println("----- Step 1.1: Creating Customer dataset ends -----");
        
        System.out.println("----- Step 1.2: Creating Transaction dataset begins -----");
        
        //TransID: unique sequential number (integer) from 1 to 5,000,000 (the file has 5M transactions)
        //CustID: References one of the customer IDs, i.e., from 1 to 50,000 (on Avg. a customer has 100 trans.)
        //TransTotal: random number (float) between 10 and 1000
        //TransNumItems: random number (integer) between 1 and 10
        //TransDesc: random text of characters of length between 20 and 50 (do not include commas)
        
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(transFileName))))
        {
            for(int transID=1; transID<=5000000; transID++)
            {   
                customerID = randomGenerator.nextInt(customerIDMax) + customerIDMin;
                transTotal = randomGenerator.nextFloat() * (transTotalMax - transTotalMin) + transTotalMin;
                transNumItems = randomGenerator.nextInt(transNumItemsMax) + transNumItemsMin;

                transDescLength = randomGenerator.nextInt(transDescLengthMax - transDescLengthMin + 1) + transDescLengthMin;
                stringBuilderObj = new StringBuilder(transDescLength);
                for(int i = 0; i < transDescLength; i++ ) 
                    stringBuilderObj.append(ATOZ.charAt(randomGenerator.nextInt(ATOZ.length())));

                //System.out.println(transID +","+customerID+","+transTotal+","+transNumItems+","+transDescLength);
                out.println(transID +","+customerID+","+transTotal+","+transNumItems+","+stringBuilderObj.toString());
            }
        }
        catch(IOException e)
        {
            System.out.println("Error occurred while created Transaction dataset.");
        }
        
        System.out.println("----- Step 1.2: Creating Transaction dataset ends -----");
    }
    
}
