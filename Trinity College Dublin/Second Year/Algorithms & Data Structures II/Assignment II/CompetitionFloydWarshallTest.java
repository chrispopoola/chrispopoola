import static org.junit.Assert.*;

import org.junit.Test;

public class CompetitionFloydWarshallTest {

    @Test
    public void testCompetitionFloydWarshallTest() {
    	CompetitionFloydWarshall floydwarshall = new CompetitionFloydWarshall("src/tinyEWD.txt", 50, 50, 50);
    }  

    @Test
    public void testTimeRequiredforCompetition() {
  //  	CompetitionFloydWarshall floydwarshall1 = new CompetitionFloydWarshall(null, 63, 77, 95);
  //  	assertEquals("FW with null input", NullPointerException.class, floydwarshall1.timeRequiredforCompetition());
    	
    	CompetitionFloydWarshall floydwarshall2 = new CompetitionFloydWarshall("src/tinyEWD.txt", 50, 75, 100);
    	assertEquals("Floyd Warshall with input-tinyEWD", 38, floydwarshall2.timeRequiredforCompetition());

    	CompetitionFloydWarshall floydwarshall3 = new CompetitionFloydWarshall("src/input-L.txt", 55, 66, 77);
    	assertEquals("Floyd Warshall with input-L", 146, floydwarshall3.timeRequiredforCompetition());

    	CompetitionFloydWarshall floydwarshall4 = new CompetitionFloydWarshall("src/input-K.txt", 50, 75, 100);
    	assertEquals("Floyd Warshall with input-K", 320, floydwarshall4.timeRequiredforCompetition());

    	CompetitionFloydWarshall floydwarshall5 = new CompetitionFloydWarshall("src/input-C.txt", 55, 66, 77);
    	assertEquals("Floyd Warshall with input-C", -1, floydwarshall5.timeRequiredforCompetition());
    	
    	CompetitionFloydWarshall floydwarshall6 = new CompetitionFloydWarshall("src/input-A.txt", 50, 50, 50);
    	assertEquals("Floyd Warshall with input-A", -1, floydwarshall6.timeRequiredforCompetition());
    	
    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall("input-D.txt", 50, 60, 80);
		int time = floydWarshall.timeRequiredforCompetition();
		assertEquals("FW with input-D", 38, time);
    }
}
