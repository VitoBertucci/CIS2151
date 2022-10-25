public class ThreadRunner extends Thread{
    private String name; //name of runner
    private int restRate; //value 1-100 chance that the runner will rest (large value = slow)
    private int speed; //speed of runner
    private int position; //position of runner

    //constructor
    ThreadRunner(String name, int restRate, int speed)
    {
        this.name = name;
        this.restRate = restRate;
        this.speed = speed;
    }

    //run method
    public void run()
    {
        while(position < 1000)
        {

            //make random number 1-100
            int rand = ((int) (Math.random() * 99)) + 1;

            //if number is greater than rest rate, add speed to position
            if(rand > restRate)
            {
                //add speed to position and display if runner moves
                position += speed;
                System.out.println(this.name + ": " + this.position);
            }
            
            //thread sleeps 100 milliseconds each iteration
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
