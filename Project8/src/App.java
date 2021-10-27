import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;

public class App 
{

    //method to output string to file
    public static void write(PrintWriter out, String str)
    {
            out.println(str);
            out.close();  
    }

    public static void main(String[] args) 
    {
        //variables and other stuff
        String again = "y";
        String line = "";
        String name = "";
        String food = "";
        Double cost;
        Scanner kb = new Scanner(System.in);
        File file = new File("FastFoodData.txt");

        //DECLARE AN ARRAYLIST OF FASTFOODNEW OBJECTS
        ArrayList<FastFoodNew> restaurants = new ArrayList<FastFoodNew>();


        while(again.equalsIgnoreCase("y"))
        {
            //OPEN THE TEXT FILE FOR INPUT
            try
            {
                //open file to be read
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                //create printWriter for output to file
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter infoWriter = new PrintWriter(bufferedWriter);

                //get restaurant info in terminal and output to textfile
                System.out.println("Enter name of restaurant, its food type, and cost of that food: ");
                line = kb.nextLine();
                write(infoWriter, line);
            } 
            catch (IOException ioe)
            {
                ioe.toString();
                System.exit(1);
            }
            
            StringTokenizer tokens = new StringTokenizer(line, ", ");
            //USE WHILE LOOP TO:
            while(tokens.hasMoreTokens()) 
            {
                //TOKENIZE LINE STRING INTO STRING NAME, STRING FOOD, DOUBLE COST
                name = tokens.nextToken();
                food = tokens.nextToken();
                cost = Double.parseDouble(tokens.nextToken());

                //CALL NON-DEFAULT CONSTRUCTOR AND USE THOSE STRINGS AS PARAMETERS
                FastFoodNew ffn = new FastFoodNew(name, food, cost);

                //ADD THE OBJECT INTO THE ARRAY
                restaurants.add(ffn);
            }
            //END OF LOOP
            System.out.println("Enter another object? (y/n)");
            again = kb.nextLine();
        
        }

        //display all objects
        for(int i = 0; i < restaurants.size(); i++)
        {
            restaurants.get(i).display();
        }
        
    }
}


