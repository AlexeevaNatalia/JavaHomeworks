package PhilosopherProblem;

public class Fork {
	    private static int nextId = 0;
	    private int id;

	    public Fork() {
	        this.id = nextId++;
	    }

	    public int compareTo(Fork rhs) {
	        return new Integer(this.id).compareTo(new Integer(rhs.id));
	    }

}
