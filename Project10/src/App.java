import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame
{   
    private String name; //name of animal
    private String typeSelection = ""; //typeSelection of animal
    private ArrayList<Animal> animalList = new ArrayList<Animal>(); //list for animal storage
    private Animal a; //animal to be stored each iteration

    private Container c = getContentPane();
    private JTextField nameInput;
    private JTextField typeInput;
    private JButton add;
    private JButton finish;
    private JButton clear;
    private JTextArea outputArea;

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

    public App()
    {
        c.setLayout(new FlowLayout());
        c.setBackground(Color.DARK_GRAY);

        //name and type input fields
        nameInput = new JTextField(12);
        typeInput = new JTextField(12);
        JLabel nLabel = new JLabel("name:");
        nLabel.setForeground(Color.WHITE);
        JLabel tLabel = new JLabel("type:");
        tLabel.setForeground(Color.WHITE);
        c.add(nLabel);
        c.add(nameInput);
        c.add(tLabel);
        c.add(typeInput);

        //buttons for add animal with inputted values, new animal, and finish loop
        //add button event handler
        add = new JButton("Add Animal");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == add)
                { 
                    try 
                    {
                        //get name and type from fields 
                        name = nameInput.getText();
                        typeSelection = typeInput.getText();

                        //check to see if typeSelection selection is 1, 2, or 3
                        if(typeSelectionSelectionIsValid(typeSelection) == false)
                        {
                            //if not throw invalid animal typeSelection excpetion
                            throw new InvalidAnimaltypeSelectionException();
                        }
                        
                        //conditional for each animal type
                        if (typeSelection.equalsIgnoreCase("1"))
                        {
                            a = new Dog(name);

                        } else if (typeSelection.equalsIgnoreCase("2"))
                        {
                            a = new Cat(name);

                        } else if (typeSelection.equalsIgnoreCase("3"))
                        {
                            a = new Turtle(name);

                        }

                        //get the animal to speak in order for lambda speak to set data for speak attribute
                        a.speak();
                        animalList.add(a);

                        //wipe input fields
                        nameInput.setText(null);
                        typeInput.setText(null);

                    } catch(InvalidAnimaltypeSelectionException iate) //catch invalid animal typeSelection selection
                    {
                        JOptionPane.showMessageDialog(null, "Animal type must be 1, 2, or 3");
                        nameInput.setText(null);
                        typeInput.setText(null);
                    }
                }
            }
        });

        //finish button event handler
        finish = new JButton("Finish List");
        finish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                outputArea.setText(null);
                outputArea.append("Let's hear the animals speak:");
                for(Animal c : animalList)
                {
                    outputArea.append("\n- " + c.speak);
                }
                outputArea.append("\n\n Press the 'Clear All Animals' button to start a new list");
            }
        });

        //clear button event handler
        clear = new JButton("Clear All Animals");
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //clear arrayList, text areas, and fields
                animalList.clear();
                outputArea.setText("Enter a name and a valid animal type." 
                + "\nClick add to add animal to list"
                + "\nAnimal types:"
                + "\n1 - Dog"
                + "\n2 - Cat"
                + "\n3 - Turtle"
                + "\nPress the 'Finish List' button to stop enetering animals");
                nameInput.setText(null);
                typeInput.setText(null);
            }
        });

        //add buttons to pane
        c.add(add);
        c.add(finish);
        c.add(clear);

        //add text area for prompts and display method
        outputArea = new JTextArea(20,30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Calibri", Font.PLAIN, 15));
        outputArea.setText("Enter a name and a valid animal type." 
            + "\nClick add to add animal to list"
            + "\nAnimal types:"
            + "\n1 - Dog"
            + "\n2 - Cat"
            + "\n3 - Turtle"
            + "\nPress the 'Finish List' button to stop enetering animals");
        c.add(outputArea);
    }
    
    public static void main(String[] args)
    {
        App frame = new App();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
    } 
}
