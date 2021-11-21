public class Dog extends Animal 
{
    //constructor
    public Dog(String name)
    {
        super(name);
        type = "dog";
    }

    //dog speak method
    public void speak()
    {
        speak(dog -> dog.speak = (dog.getNameAndType() + " says woof"));
    }
    
}
