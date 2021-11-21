public abstract class Animal 
{
    //each animal get a name and type
    public static String name;
    public static String type;

    //interface for lamda
    public interface Consumer<T>
    {
        void accept(T t);
    }

    //speak method for subclasses to use
    public abstract void speak();
    
    //lamda speak method
    protected void speak(Consumer<Animal> consumer)
    {
        consumer.accept(this);
    }

    //constructor
    public Animal(String name)
    {
        setName(name);
    }

    //sets and gets
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        Animal.name = name;
    }
    public String getNameAndType()
    {
        String speak = (getName() + " the " + getType());
        return speak;
    }
    public String getType()
    {
        return type;
    }
    
    
    
}
