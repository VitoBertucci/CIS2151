//Done with part 1

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;

public class App 
{
    public static void main(String[] args) 
    {
        //variables and other stuff
        String line = "";
        String name = "";
        String food = "";
        Double cost;
        
        File textFile = new File("FastFoodData.txt");

        //DECLARE AN ARRAYLIST OF FASTFOODNEW OBJECTS
        ArrayList<FastFoodNew> restaurants = new ArrayList<FastFoodNew>();

        //READ A LINE OF INPUT FROM THE FILE
        try
        {
            FileReader fileReader = new FileReader(textFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //USE WHILE LOOP TO:
            while((line = bufferedReader.readLine()) != null) 
            {
                StringTokenizer tokens = new StringTokenizer(line, ", ");
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
            }

            fileReader.close();

        } catch (IOException ioe)
        {
            ioe.toString();
            System.exit(1);
        }


        
    
    //______________END OF PART 1________________________________________________________________
        
        //OPEN BINARY ACCESS FILE FOR OUTPUT
        File binaryFile = new File("FastFoodInfo.dat");
        try(
        //open file and create outputStream
        FileInputStream fileInputStream = new FileInputStream(binaryFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

        FileOutputStream fileOutputStream = new FileOutputStream(binaryFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        DataOutputStream output = new DataOutputStream(bufferedOutputStream);
        
        )
        {
            //USE A LOOP TO GO THROUGH ARRAYLIST
            for (int i = 0; i < restaurants.size(); i++)
            {
                FastFoodNew ffn = restaurants.get(i);
                output.writeChars(ffn.getName() + " ");
                output.writeChars(ffn.getFoodType() + " ");
                output.writeDouble(ffn.getCost());
                output.writeChars("\n");
            }
            //close the outputstream
            output.close();
            //CLOSE THE FILE
            fileInputStream.close();

        } catch (FileNotFoundException fnfe) 
        {
            System.exit(1);
        } catch (IOException ioe)
        {
            System.out.println("File error");
        }

    //______________END OF PART 2________________________________________________________________

    
    
    }
}


