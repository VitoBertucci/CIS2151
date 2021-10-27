//Done with part 1

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;

public class App 
{
    public static void main(String[] args) 
    {
        String line = "";
        String dataValue = "";
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
            int i = 0;
            while((line = bufferedReader.readLine()) != null) 
            {

                StringTokenizer tokens = new StringTokenizer(line, " ");
                while(tokens.hasMoreTokens()) 
                {
                    //TOKENIZE LINE STRING INTO STRING NAME, STRING FOOD, DOUBLE COST AND STORE IN THEIR ARRAYS
                    name = tokens.nextToken();
                    food = tokens.nextToken();
                    cost = Double.parseDouble(tokens.nextToken());

                    //CALL NON-DEFAULT CONSTRUCTOR AND USE THOSE STRINGS AS PARAMETERS
                    FastFoodNew ffn = new FastFoodNew(name, food, cost);

                    //ADD THE OBJECT INTO THE ARRAY
                    restaurants.add(ffn);
                } 
            } //END THE LOOP

            //CLOSE THE FILE
            bufferedReader.close();

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
        FileOutputStream fileOutputStream = new FileOutputStream(binaryFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        DataOutputStream output = new DataOutputStream(bufferedOutputStream);
        
        )
        {
            //USE A LOOP TO GO THROUGH ARRAYLIST
            for (int i = 0; i < restaurants.size(); i++)
            {
                //GET AN ARRAY LIST OBJECT
                FastFoodNew ffn = restaurants.get(i);
                //USE ITS GET METHODS TO GET EACH DATA MEMBER AND 
                //OUTPUT TO BINARY ACCESS FILE
                output.writeUTF(ffn.getName());
                output.writeUTF(ffn.getFoodType());
                output.writeUTF(String.valueOf(ffn.getCost()) + "\n");
            } //END THE LOOP
            
            //CLOSE THE FILE
            output.close();

        } catch (FileNotFoundException fnfe) 
        {
            System.exit(1);
        } catch (IOException ioe)
        {
            System.out.println("File error");
        }

    //______________END OF PART 2________________________________________________________________   

        try 
        {
            //OPEN TXT FILE 
            PrintWriter writer = new PrintWriter("FastFoodReport.txt");

            //OPEN BINARY ACCESS FILE FOR INPUT
            FileInputStream inputStream = new FileInputStream("FastFoodInfo.dat");
            DataInputStream dataStream = new DataInputStream(inputStream);

            //LOOP TO READ EACH DATA ITEM
            for (int i = 1; i < 6; i++)
            {
                //OUTPUT EACH DATA VALUE INTO THE TEXT FILE
                dataValue = dataStream.readUTF();
                writer.print("Restaurant: " + dataValue);
                dataValue = dataStream.readUTF();
                writer.print("     Best known for: " + (dataValue));
                dataValue = dataStream.readUTF();
                writer.print("     Cost: " + (dataValue));
            } //END THE LOOP

            //CLOSE THE DATA FILE
            dataStream.close();

            //CLOSE THE TEXT FILE
            writer.close();
            
        } catch (IOException ioe)
        {
            System.exit(1);
        }

    }
}


