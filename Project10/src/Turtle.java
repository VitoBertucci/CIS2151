public class Turtle extends Animal
{
    //constructor
    public Turtle(String name)
    {
        super(name);
        type = "turtle";
    }

    //dog speak method
    public void speak()
    {
        speak(Turtle -> System.out.println(getNameAndType() + " says nothing (no vocal chords)"));
    }
}
