package PhilosopherProblem;

public class Philosopher—ooperation {
	    private int count;
	    private int dinnerTimeInSeconds;
	    private int maxWaitTime;
	    private int maxEatTime;
	    
	    public Philosopher—ooperation(
	            int count,
	            int dinnerTimeInSeconds,
	            int maxWaitTime,
	            int maxEatTime)
	    {
	        this.count = count;
	        this.dinnerTimeInSeconds = dinnerTimeInSeconds;
	        this.maxWaitTime = maxWaitTime;
	        this.maxEatTime = maxEatTime;
	    }

	    public void begin() {
	        RunnablePhilosopher[] phils = createPhilosophers();

	        try {
	            dinner(phils);
	        } catch (java.lang.InterruptedException e) {
	            e.printStackTrace();
	        }

	        printSummary(phils);
	    }

	    private RunnablePhilosopher[] createPhilosophers() {
	        RunnablePhilosopher[] phils = new RunnablePhilosopher[count];

	        Fork last = new Fork();
	        Fork left = last;
	        for (int i = 0; i < count; ++i) {
	            Fork right = (i == count - 1) ? last : new Fork();
	            phils[i] = new RunnablePhilosopher(i,
	                    left,
	                    right,
	                    maxWaitTime,
	                    maxEatTime);
	      	            left = right;
	        }

	        for (int i = 0; i < count; ++i) { //round
	            phils[i].setRightNeighbour(
	                    (i != count - 1) ? phils[i + 1] : phils[0]);
	            phils[i].setLeftNeighbour(
	                    (i != 0) ? phils[i - 1] : phils[count - 1]);
	        }
	        return phils;
	    }

	    private void dinner(RunnablePhilosopher[] phils)throws java.lang.InterruptedException {
	        Thread[] threads = new Thread[count];

	        for (int i = 0; i < count; ++i) {
	            threads[i] = new Thread(phils[i]);
	            threads[i].start();
	        }

	        Thread.sleep(dinnerTimeInSeconds * 10);

	        for (RunnablePhilosopher phil : phils) {
	            phil.stop();
	        }

	        for (Thread thread : threads) {
	            thread.join();
	        }
	    }

	    private void printSummary(RunnablePhilosopher[] phils) {
	        for (RunnablePhilosopher phil : phils) {
	            /*System.out.println("[" + phil.getPosition()
	                    + "] " + phil.getEatCount()
	                    + " " + phil.getWaitTime());
	            */ 
	        }

	        
	        System.out.println("-------------- Summary ---------------");
	        System.out.println("Mean wait time: " + calculateMean(phils) + " ms.");
	        
	    }

	    private double calculateMean(Philosopher[] phils) {
	        double mean = 0;
	        for (Philosopher phil : phils) {
	            mean += phil.getWaitTime() / phils.length;
	        }

	        return mean;
	    }

	    public static void main(String[] args) {
	        int philsNumber = 5;
	        int time = 10000; //dinner time
	        int thinkingTime = 5000;
	        int eatingTime = 1000;
	        
	        new Philosopher—ooperation(philsNumber, time, thinkingTime,
	                eatingTime)
	                .begin();
	    }

}
