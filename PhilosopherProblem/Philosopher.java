package PhilosopherProblem;
import java.util.Random;

public class Philosopher {
	private int eatCount;
    private volatile long waitTime;
    private long startWait;

    protected Random random;
    protected int position;
    protected Fork left;
    protected Fork right;
    protected int maxWaitTime;
    protected int maxEatTime;

 
    public Philosopher(
        int position,
        Fork left,
        Fork right,
        int maxWaitTime,
        int maxEatTime)
    	{
    	 if (left == right) {
             throw new java.lang.IllegalArgumentException(
                     "Philosopher must have 2 different forks.");
         }

         this.eatCount = 0;
         this.waitTime = 0;
         this.startWait = System.currentTimeMillis();
         this.random = new Random(1000);

         this.position = position;
         this.left = left;
         this.right = right;
         this.maxWaitTime = maxWaitTime;
         this.maxEatTime = maxEatTime;

        this.random = new Random();
    }

    public void startEating() {
        waitTime += System.currentTimeMillis() - startWait;

        System.out.println("[" + position + "] eating");
        tryToSleep(random.nextInt(maxEatTime));

        ++eatCount;
    }

    public void startThinking() {
        System.out.println("[" + position + "] thinking");
        tryToSleep(random.nextInt(maxWaitTime));

        System.out.println("[" + position + "] hungry");
        startWait = System.currentTimeMillis();
    }

    public int getPosition() {
        return position;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public int getEatCount() {
        return eatCount;
    }

    protected void tryToSleep(int mseconds) {
        try {
            Thread.sleep(mseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
