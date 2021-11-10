import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame
{
    private Container c = getContentPane();
    private TextArea ta = new TextArea(20,25);
    private String[] itemNames = {"bobblehead", "thunder stick", "foam paw", "tee shirt", "sweat shirt","cap", "knit hat", "mug", "pennant" };
    private double[] prices = {12.0,9.0,5.0,15.0,25.0,5.0,10.0,8.0,12.0,3.0};

    private String[] teamNames ={"Redwings","Lions","Tigers","Pistons"};
    private String[] logos2 = {"redwings.gif","lions.gif","tigers.gif","pistons.gif"};

    private ImageIcon []logos = new ImageIcon[4];
    private double[] ticketPrices = {40.0,50.0,100.0};
    private Icon[] teamIcon = new ImageIcon[4];

    private double seatPrice;
    private double ticketCost; //store the cost of the ticket
    private int teamIndex; //store selection from JComboBox (index into array of team names)
    private double surcharge; //store extra charge based on seating choice
    private int numTickets; //to store input from JSlider
    private int[] itemindex; //to store indices of all selected souvenirs in the JList
    private String seating;   //store selection from JRadioButtonssaa   
    private double subtotal; // store subtotal of transaction

    private JSlider slider;
    private JButton orderButton;
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
        northPanel.setBackground(Color.DARK_GRAY);

        //create south panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1,2));
        southPanel.setBackground(Color.DARK_GRAY);

        //create east panel
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(4,1));
        eastPanel.setBackground(Color.LIGHT_GRAY);

        //create west panel
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(4,1));
        westPanel.setBackground(Color.LIGHT_GRAY);

        //create ceneter ponel that has two other panels, left and right
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1,2));
        JPanel centerLeftPanel = new JPanel();
        centerLeftPanel.setLayout(new GridLayout(2,1));
        JPanel centerRightPanel = new JPanel();
        centerRightPanel.setLayout(new GridLayout(2,1));
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
        ticketLabel.setForeground(Color.WHITE);
        northPanel.add(ticketLabel, BorderLayout.NORTH);

        //SOUTH Panel: a JLabel followed by a JSlider.
        JLabel sliderLabel = new JLabel("Select Number of Tickets:");
        sliderLabel.setFont(new Font ("Calibri", Font.ITALIC, 20));
        sliderLabel.setForeground(Color.WHITE);
        southPanel.add(sliderLabel, BorderLayout.SOUTH);
        slider = new JSlider(SwingConstants.HORIZONTAL, 0,10,5);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setForeground(Color.WHITE);
        slider.setPaintLabels(true);
        slider.setValue(0);

        //event handler for slider
        slider.addChangeListener
        (new ChangeListener()
            {
                public void stateChanged(ChangeEvent e)
                    {
                        numTickets = slider.getValue();
                    }
            }
        );
        southPanel.add(slider, BorderLayout.SOUTH);

        //EAST panel: a JLabel: Click Selection, 3 Buttons
        JLabel selection = new JLabel("Click Selection");

        //event handler for order button
        orderButton = new JButton("Order");
        orderButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == orderButton)
                {   
                    ta.setText(null);
                    ta.setEditable(false);
                    ta.append("TEAM  " + teamNames[teamIndex]);
                    ta.append("\nTicket Cost  " + seatPrice);
                    ta.append("\nSurcharge  " + surcharge);
                    ta.append("\nItems Ordered:");
                    //output selected items from list
                    ta.append("\n\nSubtotal:  " + seatPrice);
                    ta.append("\n\nSets Ordered:  " + numTickets);
                    ta.append("\n\nTotal Cost:  " + (seatPrice * numTickets));

                    JOptionPane.showMessageDialog(null, ta, "Your Order", JOptionPane.INFORMATION_MESSAGE, logos[teamIndex]);
                    
                }
            }
        });

        //event handler for clear selection button
        clearSelectionButton = new JButton("Clear Selection");
        clearSelectionButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == clearSelectionButton)
                {
                    nameList.clearSelection();
                    teamIndex = 0;
                    itemindex = null;
                    slider.setValue(0);
                    lowerDeck.setSelected(false);
                    upperDeck.setSelected(false);
                    luxuryBox.setSelected(false);
                    seating = "";
                    seatPrice = 0.0;
                    surcharge = 0.0;
                    subtotal = 0.0;
                }
            }
        });

        //event handler for exit button
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == exitButton)
                {
                    System.exit(1);
                }
            }
        });

        //add components to east panel
        eastPanel.add(selection);
        eastPanel.add(orderButton);
        eastPanel.add(clearSelectionButton);
        eastPanel.add(exitButton);

        //WEST panel:  a JLabel: Seat selections and 3 JRadioButtons as shown above
        JLabel seatSelect = new JLabel("Seat Selection:");
        seatGroup = new ButtonGroup();
        
        //event handler for lowerdeck radiobutton
        lowerDeck.addItemListener(new ItemListener() 
        {
            public void itemStateChanged(ItemEvent e)
            {
                if(e.getSource() == lowerDeck && lowerDeck.isSelected() == true)
                {   
                    seatPrice = ticketPrices[0];
                    seating = "Lower Deck";
                }
            }
        });
        seatGroup.add(lowerDeck);

        //event handler for upperdeck radiobutton
        upperDeck.addItemListener(new ItemListener() 
        {
            public void itemStateChanged(ItemEvent e)
            {
                if(e.getSource() == upperDeck && upperDeck.isSelected() == true)
                {
                    seatPrice = ticketPrices[1];
                    seating = "Upper Deck";
                }
            }
        });
        seatGroup.add(upperDeck);

        //event handler for luxurybox radiobutton
        luxuryBox.addItemListener(new ItemListener() 
        {
            public void itemStateChanged(ItemEvent e)
            {
                if(e.getSource() == luxuryBox && luxuryBox.isSelected() == true)
                {
                    seatPrice = ticketPrices[2];
                    seating = "Luxury Box";
                }
            }
        });
        seatGroup.add(luxuryBox);

        //add label and buttons to panel
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

        //event handler for combox
        teamBox.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                if(e.getSource() == teamBox)
                {
                    teamIndex = teamBox.getSelectedIndex();
                }
            }
        });
        centerLeftPanel.add(teamBox);

        //add list to right panel
        nameList = new JList(itemNames);
        nameList.setVisibleRowCount(5);
        nameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        //event handler for lsit
        nameList.addListSelectionListener
        (new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e)
            {
                itemindex = nameList.getSelectedIndices();
            }
        });
        centerRightPanel.add(new JScrollPane(nameList));
    }

    public static void main(String[] args)
    {
        App app = new App();
        app.setSize(800,400);
        app.setVisible(true);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
