public class Cat extends Animal
{
    //constructor
    public Cat(String name)
    {
        super(name);
        type = "cat";
    }

    //cat speak method
    public void speak()
    {
        speak(Cat -> System.out.println(getNameAndType() + " says Meow"));
    }
}
