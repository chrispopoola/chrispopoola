import static org.junit.Assert.*;

import org.junit.Test;


	public class CompetitionDijkstraTest {

		@Test
	    public void testCompetitionDijkstra() {
	    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("src/tinyEWD.txt", 50, 50, 50);
	    } 
	    
	    @Test
	    public void testTimeRequiredforCompetition() {
	    	
	 //   	CompetitionDijkstra dijkstra1 = new CompetitionDijkstra(null, 55, 66, 77);
	 //   	assertEquals("Testing Dijsktra with null input", -1, dijkstra1.timeRequiredforCompetition());
	    	
	    	CompetitionDijkstra dijkstra2 = new CompetitionDijkstra("src/tinyEWD.txt", 50, 75, 100);
	    	assertEquals("Testing Dijsktra with input-tinyEWD", 38, dijkstra2.timeRequiredforCompetition());
	    	
	    	CompetitionDijkstra dijkstra3 = new CompetitionDijkstra("src/input-L.txt", 55, 66, 77);
	    	assertEquals("Testing Dijsktra with input-L", 146, dijkstra3.timeRequiredforCompetition());
	    	
	    	CompetitionDijkstra dijkstra4 = new CompetitionDijkstra("src/input-K.txt", 50, 75, 100);
	    	assertEquals("Testing Dijsktra with input-K", 320, dijkstra4.timeRequiredforCompetition());

	    	CompetitionDijkstra dijkstra5 = new CompetitionDijkstra("src/input-D.txt", 55, 66, 77);
	    	assertEquals("Testing Dijkstra with input-D", 34, dijkstra5.timeRequiredforCompetition());
	    	
	    	CompetitionDijkstra dijkstra6 = new CompetitionDijkstra("src/input-A.txt", 60, 50, 75);
	    	assertEquals("Testing Dijkstra with input-A", -1, dijkstra6.timeRequiredforCompetition());
	    }
	    

	}

