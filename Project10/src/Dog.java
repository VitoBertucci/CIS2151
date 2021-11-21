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
        speak(Dog -> System.out.println(getNameAndType() + " says Woof Woof"));
    }
}
