import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{   
    //InvalidAnimaltypeSelection exception class
    static class InvalidAnimaltypeSelectionException extends Exception
    {
        public InvalidAnimaltypeSelectionException()
        {
            super("Invalid animal typeSelection selection");
        }
    }

    //method to see if input for typeSelection is in range (1,3)
    public static Boolean typeSelectionSelectionIsValid(String str)
    {
        //if input is anything other than 1, 2, or 3, typeSelectionSelection is not valid
        switch(str)
        {
            case "1":
                return true;
            case "2":
                return true;
            case "3":
                return true;
            default:
                return false;
        }
    }
    public static void main(String[] args)
    {
        String loop = "y"; //loop selction
        String name; //name of animal
        int index = 0;
        String typeSelection = ""; //typeSelection of animal
        Scanner kb = new Scanner(System.in); //scanner for input
        ArrayList<Animal> animalList = new ArrayList<Animal>(); //list for animal storage

        //welcome message
        System.out.println("Welcome to the animal creator!");

        //while user loop selection is y
        while(loop.equalsIgnoreCase("y"))
        {

            try
            {
                //ask for typeSelection of animal (1-3)
                System.out.println("Enter typeSelection of animal: ");
                System.out.println("1 - Dog" + "\n2 - Cat" + "\n3 - Turtle");
                typeSelection = kb.next();

                //check to see if typeSelection selection is 1, 2, or 3
                if(typeSelectionSelectionIsValid(typeSelection) == false)
                {
                    //if not throw invalid animal typeSelection excpetion
                    throw new InvalidAnimaltypeSelectionException();
                }

                //get name of animal
                System.out.println("Enter name of animal: ");
                name = kb.next();
                
                if (typeSelection.equalsIgnoreCase("1"))
                {
                    //if selection is 1, make a dog with the inputted name and add to list
                    animalList.add(new Dog(name));
                } else if (typeSelection.equalsIgnoreCase("2"))
                {
                    //if selection is 2, make a cat with the inputted name and add to list
                    animalList.add(new Cat(name));
                } else if (typeSelection.equalsIgnoreCase("3"))
                {
                    //if selection is 3, make a turtle with the inputted name and add to list
                    animalList.add(new Turtle(name));
                }

                index++;

                //ask for loop condition
                System.out.println("Create another animal? (y/n) ");
                loop = kb.next();


            }  catch (InvalidAnimaltypeSelectionException iate) //catch invalid animal typeSelection selection
            {
                //ask for valid selection
                System.out.println("Enter valid animal typeSelection (1,2,3)");

            } 
        }

        System.out.println("Now let's hear the animals speak:");
        System.out.println("---------------------------------");
        
        for (int i = 0; i < animalList.size(); i++)
        {
            (animalList.get(i)).speak();
        }
    } 
}
