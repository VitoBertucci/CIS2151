public class App {
    public static void main(String[] args)  {
        ThreadRunner tortoise = new ThreadRunner("Turtoise", 1, 10);
        ThreadRunner hare = new ThreadRunner("Hare", 90, 100);

        System.out.println("Get set... Go!");
        tortoise.start();
        hare.start();
    }
}
