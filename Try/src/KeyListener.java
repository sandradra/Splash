import java.util.ArrayList;
import java.util.Iterator;

public class KeyListener{
	
	public KeyListener(){		
	}
	
	public int upKey(Whale whale, ArrayList<Rubbish> rubbish, ArrayList<Seaweed> seaweed, int score) {
		int count = 0;
		whale.move(0,-20);
		whale.updateUI();
		for(Iterator<Rubbish> it = rubbish.iterator(); it.hasNext(); ) {
		    Rubbish r = it.next();
		    if (whale.collidesWith(r)) {
				r.removeFromLayer();
		        it.remove();
				score += 50;	
				count++;
			}else{
				whale.dx = 0;
			}
		}
		return count;		
	}
	
	public int leftKey(Whale whale, ArrayList<Rubbish> rubbish, int score) {
		whale.move(-20,0);
		whale.updateUI();
		for(Iterator<Rubbish> it = rubbish.iterator(); it.hasNext(); ) {
		    Rubbish r = it.next();
		    if (whale.collidesWith(r)) {
				r.removeFromLayer();
		        it.remove();
				score += 50;			
			}else{whale.dx = 0;
			}	
//			for (Seaweed i:seaweed) {
//			if (!(whale.collidesWith(i))) {
//				System.out.println("GameOver");
//			}
//		}
		}
		return score;
	}
	
	public int rightKey(Whale whale, ArrayList<Rubbish> rubbish, int score) {
		whale.move(20,0);
		whale.updateUI();
		for(Iterator<Rubbish> it = rubbish.iterator(); it.hasNext(); ) {
		    Rubbish r = it.next();
		    if (whale.collidesWith(r)) {
				r.removeFromLayer();
		        it.remove();
				score += 50;			
			}else{whale.dx = 0;
			}	
		}
		return score;
	}
	
	public void spaceKey() {
		
	}
	

	
}