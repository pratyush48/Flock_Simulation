package sample;

import flockbase.Bird;
import flockbase.Flock;
import java.util.ArrayList;

public class FlockY extends Flock {
		private ArrayList<Bird> allBirds = new ArrayList<Bird>(0);
    // private ArrayList<ArrayList<Bird>> Birds = new ArrayList<ArrayList<Bird>>(1);
    Bird lead = null;
    
    public void addBird(Bird bird) {
        allBirds.add(bird);
        bird.setFlock((Flock)this);
    }

    public void setLeader(Bird leader) {
    	if(lead != null){
      	lead.retireLead();
       }
    	lead = leader;
      lead.becomeLeader();
    }

    public ArrayList<Bird> getBirds() {
        return this.allBirds;
    }

    public Bird getLeader() {
        return this.lead;
    }

    public Flock split(int cordinates) {
        FlockY newFlock = new FlockY();
        for(int i = cordinates;i < allBirds.size();i++){
            allBirds.get(i).setFlock(newFlock);
            newFlock.addBird(allBirds.get(i));
            allBirds.remove(i);
        }
        newFlock.setLeader(newFlock.getBirds().get(0));
        newFlock.getLeader().setTarget(getLeader().getPos().getX() + 50, getLeader().getPos().getY() + 50);
        return newFlock;
    }

    public void joinFlock(Flock flock){
        for(int i = 0;i < getBirds().size();i++){
            allBirds.get(i).retireLead();
            allBirds.get(i).setFlock(flock);
            flock.getBirds().add(getBirds().get(i));
        }
    }
}
