public class FastFoodNew {
    //attributes
    private String name;
    private String foodType;
    private Double cost;


    //default constructor
    public FastFoodNew () {
        name = "";
        foodType = "";
        cost = 0.0;
    }

    //non-default constuctor 
    public FastFoodNew (String inputName, String inputFoodType, Double inputCost) {
        setName(inputName);
        setFoodType(inputFoodType);
        setCost(inputCost);
    }

    //methods

    //get methods
    public String getName () {
        return name;
    }
    public String getFoodType () {
        return foodType;
    }
    public Double getCost () {
        return cost;
    }

    //set methods
    public void setName (String inputName) {
        name = inputName;
    }
    public void setFoodType (String inputFoodType) {
        foodType = inputFoodType;
    }
    public void setCost (Double inputCost) {
        cost = inputCost;
    }

    //display method
    public void display () {
        System.out.println("**********");
        System.out.println("Name: " + name);
        System.out.println("Food Type: " + foodType);
        System.out.println("Cost: " + cost );
        System.out.println("**********");
    }
}
