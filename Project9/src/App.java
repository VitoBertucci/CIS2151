import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.StringBuffer;

public class App extends JFrame
{
    private Container c = getContentPane();
    private TextArea ta = new TextArea(20,25);
    private String[] itemNames = {"bobblehead", "thunder stick", "foam paw", "tee shirt", "sweat shirt","cap", "knit hat", "mug", "pennant" }; //names of items
    private double[] prices = {12,9,5,15,25,5,10,8,12}; // prices of items

    private String[] teamNames ={"Redwings","Lions","Tigers","Pistons"}; //names of teams
    ImageIcon redwingsIcon = new ImageIcon("/Users/vitobertucci/CIS2151/Project9Logos/redwings.jpg");
    ImageIcon tigersIcon = new ImageIcon("/Users/vitobertucci/CIS2151/Project9Logos/tigers.png");
    ImageIcon pistonsIcon = new ImageIcon("/Users/vitobertucci/CIS2151/Project9Logos/pistons.jpg");
    ImageIcon lionsIcon = new ImageIcon("/Users/vitobertucci/CIS2151/Project9Logos/lions.jpg");
    private ImageIcon []logos = {redwingsIcon, lionsIcon, tigersIcon, pistonsIcon}; // array of team icons
    private double[] ticketPrices = {50.0,100.0,35.0,40}; // price of different games

    private double ticketCost; //store the cost of the ticket
    private int teamIndex; //store selection from JComboBox (index into array of team names)
    private double surcharge; //store extra charge based on seating choice
    private int numTickets; //to store input from JSlider
    private int[] itemindex; //to store indices of all selected souvenirs in the JList
    private String seating;   //store selection from JRadioButtonssaa   
    private double subtotal; //store subtotal of transaction
    private boolean addItem; //boolean to see if adding more items/ changing order 
    private double gameCost; //cost of game and seating surcharge
    private double itemsCost; //cost of all items in order

    private String topOfOrder; //string for team, ticket, and surcharge in optionpane
    private String bottomOfOrder; //string for subtotal, sets, and total in optionpane
    private String currentItem; //keep track of current item in loop
    private StringBuffer itemsOrdered; //to merge all item strings into one string to append
    private String itemsOrderedString; //string of string buffer
    private StringBuffer itemsAdded; //to merge all added items into one string to append
    private String itemsAddedString; //string of string buffer
    
    private JSlider slider;
    private JButton addCartButton;
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
        //set add item to false to get first iteration of data
        addItem = false;

        //set font and editable for textarea
        ta.setFont(new Font("Calibri", Font.BOLD, 16));
        ta.setEditable(false);

        //create main borderlayout
        c.setLayout(new BorderLayout());

        //--------------------------------------------------------------------------------------------------
        //                          NORTH PANEL
        //--------------------------------------------------------------------------------------------------

        //make north panel and set background
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.DARK_GRAY);

        //NORTH panel:  a JLabel with the title: Tickets...  (choose a large font and colors of your choice)
        JLabel ticketLabel = new JLabel("Tickets Tickets Tickets");
        ticketLabel.setFont(new Font("Calibri", Font.PLAIN, 40));
        ticketLabel.setForeground(Color.WHITE);
        northPanel.add(ticketLabel, BorderLayout.NORTH);

        //--------------------------------------------------------------------------------------------------
        //                          CENTER PANEL
        //--------------------------------------------------------------------------------------------------

        //create center ponel that has two other panels, left and right
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1,2));
        JPanel centerLeftPanel = new JPanel();
        centerLeftPanel.setLayout(new GridLayout(2,1));
        JPanel centerRightPanel = new JPanel();
        centerRightPanel.setLayout(new GridLayout(2,1));
        centerPanel.add(centerLeftPanel);
        centerPanel.add(centerRightPanel);

        //make labels for team and item selection, set font and add to panes
        JLabel teamLabel = new JLabel("Team", SwingConstants.CENTER);
        JLabel souvLabel = new JLabel("Souvenirs (CTRL Click)", SwingConstants.CENTER);
        teamLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        souvLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
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
                    //get index of team from team box
                    teamIndex = teamBox.getSelectedIndex();
                }
            }
        });

        //add the team box to the left panel
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
                //get index from item name list
                itemindex = nameList.getSelectedIndices();
            }
        });

        //add item list to right panel
        centerRightPanel.add(new JScrollPane(nameList));

        //--------------------------------------------------------------------------------------------------
        //                           SOUTH PANEL
        //--------------------------------------------------------------------------------------------------

        //create south panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1,2));
        southPanel.setBackground(Color.DARK_GRAY);

        //SOUTH Panel: a JLabel followed by a JSlider.
        JLabel sliderLabel = new JLabel("Select Number of Tickets:", SwingConstants.CENTER);
        sliderLabel.setFont(new Font ("Calibri", Font.ITALIC, 20));
        sliderLabel.setForeground(Color.WHITE);
        southPanel.add(sliderLabel, BorderLayout.SOUTH);
        slider = new JSlider(SwingConstants.HORIZONTAL, 1,10,1);

        //set all slider properties
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setForeground(Color.WHITE);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setValue(1);

        //event handler for slider
        slider.addChangeListener
        (new ChangeListener()
            {
                public void stateChanged(ChangeEvent e)
                    {
                        //number of tickets is given by slider
                        numTickets = slider.getValue();
                    }
            }
        );

        //add slider to south pane
        southPanel.add(slider, BorderLayout.SOUTH);

        //--------------------------------------------------------------------------------------------------
        //                            EAST PANEL
        //--------------------------------------------------------------------------------------------------

        //create east panel
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(4,1));
        eastPanel.setBackground(Color.LIGHT_GRAY);

        //EAST panel: a JLabel: Click Selection, 3 Buttons
        JLabel selection = new JLabel("Click Selection", SwingConstants.CENTER);

        //event handler for order button
        addCartButton = new JButton("Add To Cart");
        addCartButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                if(e.getSource() == addCartButton)
                {   
                    ta.setText(null);
                    //this is just here in case the slider is not changed before adding to cart
                    numTickets = slider.getValue();
                    ticketCost = ticketPrices[teamIndex];
                    gameCost = ticketCost + surcharge;
                    
                    //string for top of order
                    topOfOrder = 
                    "TEAM  " + teamNames[teamIndex] + 
                    "\nSeating  " + seating + 
                    "\nTicket Cost  $" + ticketCost +
                    "\nSurcharge  $" + surcharge + 
                    "\nItems Ordered:";
                    ta.append(topOfOrder);

                    //create string buffers for item list strings
                    itemsOrdered = new StringBuffer("");
                    itemsAdded = new StringBuffer("");

                    //set current price
                    Double currentPrice = 0.0;

                    try
                    {
                        //if first time loading order
                        if (addItem == false)
                        {
                            //get all selected items and add to string buffer for appending to ta
                            for (int i = 0; i < itemindex.length; i++)
                            {
                                currentItem = itemNames[itemindex[i]];
                                currentPrice = prices[itemindex[i]];
                                itemsOrdered.append("\n  - " + currentItem + " $" + currentPrice);
                                itemsCost += currentPrice;
                            }

                            //set buffer to string to keep original ordered items in ta
                            itemsOrderedString = itemsOrdered.toString();

                        } else if (addItem == true)
                        {
                            //every other time, get new items strings and append onto original ordered items string
                            for (int i = 0; i < itemindex.length; i++)
                            {
                                currentItem = itemNames[itemindex[i]];
                                currentPrice = prices[itemindex[i]];
                                itemsAdded.append("\n  - " + currentItem + " $" + currentPrice);
                                itemsCost += currentPrice;
                            }

                            //set added items buffer to string and append onto original ordered items buffer
                            itemsAddedString = itemsAdded.toString();
                            itemsOrdered.append(itemsAddedString);

                            //all ordered items combined into one string to append onto ta
                            itemsOrderedString = (itemsOrderedString + itemsAddedString);
                        }
                    } catch (NullPointerException npe)
                    {
                        npe.toString();
                    }

                    //subtotal is cost of game plus cost of items
                    subtotal = gameCost + itemsCost;

                    //append item list string onto ta
                    ta.append(itemsOrderedString);

                    //append totals to ta under item list
                    bottomOfOrder = 
                    "\n\nSubtotal:  " + subtotal +
                    "\n\nSets Ordered:  " + numTickets +
                    "\n\nTotal Cost:  " + (subtotal * numTickets);
                    ta.append(bottomOfOrder);
                    
                    //clear item list selection after each iteration and set add item to true at end of first iteration
                    nameList.clearSelection();
                    addItem = true;
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
                    
                    //set ticket slider to default(1)
                    slider.setValue(1);

                    //clear the item list and team box and reset their assoc. values
                    nameList.clearSelection();
                    teamBox.setSelectedIndex(0);
                    teamIndex = 0;
                    itemindex = null;
                    subtotal = 0.0;
                    itemsOrdered.delete(0, itemsOrdered.length());
                    itemsOrderedString = "";
                    itemsCost = 0.0;

                    //clear the seat buttons and reset their assoc. values
                    seatGroup.clearSelection();
                    seating = "";
                    surcharge = 0.0;
                    gameCost = 0.0;
                    ticketCost = 0.0;

                    //set add item to false for new order data
                    addItem = false;
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

        //add all buttons to east panel
        eastPanel.add(selection);
        eastPanel.add(addCartButton);
        eastPanel.add(clearSelectionButton);
        eastPanel.add(exitButton);


        //--------------------------------------------------------------------------------------------------
        //                            WEST PANEL
        //--------------------------------------------------------------------------------------------------

        //create west panel
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(4,1));
        westPanel.setBackground(Color.LIGHT_GRAY);

        //WEST panel:  a JLabel: Seat selections and 3 JRadioButtons as shown above
        JLabel seatSelect = new JLabel("Seat Selection:", SwingConstants.CENTER);
        seatGroup = new ButtonGroup();
        
        //event handler for lowerdeck radiobutton
        lowerDeck.addItemListener(new ItemListener() 
        {
            public void itemStateChanged(ItemEvent e)
            {
                if(e.getSource() == lowerDeck)
                {   
                    //set surcharge and seating values
                    surcharge = 0.0;
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
                if(e.getSource() == upperDeck)
                {
                    //set surcharge and seating values
                    surcharge = 30.0;
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
                if(e.getSource() == luxuryBox)
                {
                    //set surcharge and seating values
                    surcharge = 100.0;
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

        //add all subpanels to borderlayout
        c.add(northPanel, BorderLayout.NORTH);
        c.add(southPanel, BorderLayout.SOUTH);
        c.add(eastPanel, BorderLayout.EAST);
        c.add(westPanel, BorderLayout.WEST);
        c.add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args)
    {
        App app = new App();
        app.setSize(800,400);
        app.setVisible(true);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
