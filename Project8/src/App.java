import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;

//class for the invalidNumberOfValuesException
class InvalidNumberOfValuesException extends Exception 
    {
        public InvalidNumberOfValuesException()
        {
            super("Incorrect number of values for restaurant");
        }
    }

public class App 
{
    //method to see if a string is numeric in value or not
    public static Boolean isNumeric (String str)
    {
        if(str == null)
        {
            return false;
        }
        try
        {
            Double d = Double.parseDouble(str);
        } catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
    public static void main(String[] args)
    {
        String line = "";
        String dataValue = "";
        String name = "";
        String food = "";
        Double cost = 0.0;
        int lineCount = 0;
        int numberTokens = 0;
        
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
                try 
                {
                    StringTokenizer tokens = new StringTokenizer(line, " ");
                    numberTokens = tokens.countTokens();

                    //throw InvalidNumberOfValuesException if number of values for restaurant is < 3 or > 3 
                    if (numberTokens < 3 || numberTokens > 3)
                    {
                        throw new InvalidNumberOfValuesException();
                    }

                    while(tokens.hasMoreTokens()) 
                    {
                        //TOKENIZE LINE STRING INTO STRING NAME, STRING FOOD, DOUBLE COST AND STORE IN THEIR ARRAYS
                        name = tokens.nextToken();
                        food = tokens.nextToken();
                        cost = Double.parseDouble(tokens.nextToken());

                        //if name or food is not a non-numeric string, throw nfe
                        if(isNumeric(food) == true || isNumeric(name) == true)
                        {
                            throw new NumberFormatException();
                        }
                        
                        //CALL NON-DEFAULT CONSTRUCTOR AND USE THOSE STRINGS AS PARAMETERS
                        FastFoodNew ffn = new FastFoodNew(name, food, cost);

                        //ADD THE OBJECT INTO THE ARRAY
                        restaurants.add(ffn);
                    } 
                //NumberFormatException omits the restaurant with the exception
                } catch (NumberFormatException nfe)
                {
                    System.out.println("Name and food must be string, cost must be a double");
                //InvalidNumberOfValuesException omits the restaurant that does not have exactly 3 values with it
                } catch (InvalidNumberOfValuesException inve)
                {
                    System.out.println("Each line on the txt file must have three components");
                }
            } //END THE LOOP

            //CLOSE THE FILE
            bufferedReader.close();
        
        //catch IO exceptions and exit program
        } catch (IOException ioe)
        {
            ioe.toString();
            System.exit(1);
        }

        //OPEN BINARY ACCESS FILE FOR OUTPUT
        File binaryFile = new File("FastFoodInfo.dat");
        try
        {
            //open file and create outputStream
            FileOutputStream fileOutputStream = new FileOutputStream(binaryFile);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            DataOutputStream output = new DataOutputStream(bufferedOutputStream);
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
                lineCount++;
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

        try 
        {
            //OPEN TXT FILE 
            PrintWriter writer = new PrintWriter("FastFoodReport.txt");

            //OPEN BINARY ACCESS FILE FOR INPUT
            FileInputStream inputStream = new FileInputStream("FastFoodInfo.dat");
            DataInputStream dataStream = new DataInputStream(inputStream);

            //LOOP TO READ EACH DATA ITEM
            for (int i = 0; i < lineCount ; i++)
            {
                //OUTPUT EACH DATA VALUE INTO THE TEXT FILE
                dataValue = dataStream.readUTF();
                writer.print("Restaurant: " + dataValue);
                dataValue = dataStream.readUTF();
                writer.print("     Best known for: " + (dataValue));
                dataValue = dataStream.readUTF();
                writer.print("     Cost: $" + (dataValue));
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


