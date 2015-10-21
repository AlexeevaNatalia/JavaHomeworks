package PhilosopherProblem;

public class RunnablePhilosopher extends Philosopher implements Runnable {

	   private volatile boolean stopFlag;
	    private boolean fairMode = true;
	    private RunnablePhilosopher leftNeighbour;
	    private RunnablePhilosopher rightNeighbour;

	
	    RunnablePhilosopher(
	            int position,
	            Fork left,
	            Fork right,
	            int maxWaitTime,
	            int maxEatTime) 
	    {
	            super(position, left, right, maxWaitTime, maxEatTime);//Philosopher
		        this.fairMode = true;
		        this.stopFlag = false;
		        this.leftNeighbour = null;
		        this.rightNeighbour = null;
	    }

	    public void setLeftNeighbour(RunnablePhilosopher leftNeighbour) {
	        this.leftNeighbour = leftNeighbour;
	    }

	    public void setRightNeighbour(RunnablePhilosopher rightNeighbour) {
	        this.rightNeighbour = rightNeighbour;
	    }

	 
	    private boolean beFair(RunnablePhilosopher other) {
	        if (other != null
	            && other.getWaitTime() - getWaitTime() > maxWaitTime * 2) {
	        	System.out.println("polite philosopher" +"[" + position + "]" +
	        		"gave the fork his hungry colleague" + "[" + other.position + "]");
	            tryToSleep(random.nextInt(maxWaitTime));
	            return true;
	        }

	        return false;
	    }

	    public void run() {
	        while (!stopFlag) {
	            startThinking();
	            Fork first = (left.compareTo(right) < 0) ? left : right;
	            Fork second = (left.compareTo(right) < 0) ? right : left;

	            if (fairMode && !beFair(leftNeighbour)) {
	                beFair(rightNeighbour);
	            }

	            synchronized (first) {
	                System.out.println("[" + position + "] took left fork");
	                synchronized (second) {
	                    System.out.println("[" + position + "] took right fork");
	                    startEating();
	                }
	                System.out.println("[" + position + "] put right fork");
	            }
	            System.out.println("[" + position + "] put left fork");
	        }
	    }

	    public void stop() {
	        stopFlag = true;
	    }

}
