import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class App extends JFrame
{
    private Container c = getContentPane();
    private TextArea ta = new TextArea(10,10);
    private String[] itemNames = {"bobblehead", "thunder stick", "foam paw", "tee shirt", "sweat shirt","cap", "knit hat", "mug", "pennant" };
    private double[] prices = {12.0,9.0,5.0,15.0,25.0,5.0,10.0,8.0,12.0,3.0};

    private String[] teamNames ={"Redwings","Lions","Tigers","Pistons"};
    private String[] logos2 = {"redwings.gif","lions.gif","tigers.gif","pistons.gif"};

    private ImageIcon []logos = new ImageIcon[4];
    private double[] ticketPrices = {50.0,100.0,35.0,40.0};
    private Icon[] teamIcon = new ImageIcon[4];

    private int teamIndex; //store selection from JComboBox (index into array of team names)
    private double surcharge; //store extra charge based on seating choice
    private int index;
    private int numTickets; //to store input from JSlider
    private int[] itemindex; //to store indices of all selected souvenirs in the JList
    private String seating;   //store selection from JRadioButtonssaa   

    private JSlider slider;
    private JButton addToCartButton;
    private JButton clearSelectionButton;
    private JButton exitButton;
    private JRadioButton lowerDeck = new JRadioButton("Lower Deck");
    private JRadioButton upperDeck = new JRadioButton("Upper Deck");
    private JRadioButton luxuryBox = new JRadioButton("Luxury Box");
    private ButtonGroup seatGroup;
    private JComboBox teamBox;
    private JList nameList;




    public App() 
    {
        //for loop from rubric
        for (int i = 0; i < logos.length; i++)
        {
            logos[i] = new ImageIcon(logos2[i]);
        }
        ta.setFont(new Font("Calibri", Font.BOLD, 18));

        //create main borderlayout
        c.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.CYAN);

        //create south panel
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.CYAN);

        //create east panel
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setBackground(Color.LIGHT_GRAY);

        //create west panel
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setBackground(Color.LIGHT_GRAY);

        //create ceneter ponel that has two other panels, left and right
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        JPanel centerLeftPanel = new JPanel();
        centerLeftPanel.setLayout(new BoxLayout(centerLeftPanel, BoxLayout.Y_AXIS));
        JPanel centerRightPanel = new JPanel();
        centerRightPanel.setLayout(new BoxLayout(centerRightPanel, BoxLayout.Y_AXIS));
        centerPanel.add(centerLeftPanel);
        centerPanel.add(centerRightPanel);

        //add all subpanels to borderlayout
        c.add(northPanel, BorderLayout.NORTH);
        c.add(southPanel, BorderLayout.SOUTH);
        c.add(eastPanel, BorderLayout.EAST);
        c.add(westPanel, BorderLayout.WEST);
        c.add(centerPanel, BorderLayout.CENTER);

        //NORTH panel:  a JLabel with the title: Tickets...  (choose a large font and colors of your choice)
        JLabel ticketLabel = new JLabel("Tickets Tickets Tickets");
        ticketLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
        ticketLabel.setForeground(Color.blue);
        northPanel.add(ticketLabel, BorderLayout.NORTH);

        //SOUTH Panel: a JLabel followed by a JSlider.
        JLabel sliderLabel = new JLabel("Select Number of Tickets:");
        sliderLabel.setFont(new Font ("Calibri", Font.ITALIC, 20));
        southPanel.add(sliderLabel, BorderLayout.SOUTH);
        slider = new JSlider(SwingConstants.HORIZONTAL, 0,10,5);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        southPanel.add(slider, BorderLayout.SOUTH);

        //EAST panel: a JLabel: Click Selection, 3 Buttons
        JLabel selection = new JLabel("Click Selection");
        addToCartButton = new JButton("Add to cart");
        clearSelectionButton = new JButton("Clear Selection");
        exitButton = new JButton("Exit");
        eastPanel.add(selection);
        eastPanel.add(addToCartButton);
        eastPanel.add(clearSelectionButton);
        eastPanel.add(exitButton);

        //WEST panel:  a JLabel: Seat selections and 3 JRadioButtons as shown above
        JLabel seatSelect = new JLabel("Seat Selection:");
        seatGroup = new ButtonGroup();
        seatGroup.add(lowerDeck);
        seatGroup.add(upperDeck);
        seatGroup.add(luxuryBox);
        westPanel.add(seatSelect);
        westPanel.add(lowerDeck);
        westPanel.add(upperDeck);
        westPanel.add(luxuryBox);

        //CENTER panel: a JLabel with JCombobox below and another label with one Multiselection JList  below 
        JLabel teamLabel = new JLabel("Team");
        JLabel souvLabel = new JLabel("Souvenirs (CTRL Click)");
        teamLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        souvLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        centerLeftPanel.add(teamLabel);
        centerRightPanel.add(souvLabel);        
        
        //add combo box to left panel
        teamBox = new JComboBox(teamNames);
        teamBox.setMaximumRowCount(3);
        centerLeftPanel.add(teamBox);

        //add list to right panel
        nameList = new JList(itemNames);
        nameList.setVisibleRowCount(5);
        centerRightPanel.add(new JScrollPane(nameList));
        nameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }



    public static void main(String[] args) 
    {
        App app = new App();
        app.setSize(800,330);
        app.setVisible(true);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
