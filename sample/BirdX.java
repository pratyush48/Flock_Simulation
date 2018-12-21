package sample;
import flockbase.Bird;
import flockbase.Flock;
import flockbase.Position;
import  java.io.PrintStream;
import java.util.*;
public class BirdX extends Bird{
	private boolean leader;
	private int speed = 15;
	public BirdX(){

	}
	public String getName(){
		if(leader){
			return "Leader";
		}
		return "518";
	}
	//Update Position//
	protected void updatePos(){
		Position currentPos = getPos();
		int xCor = currentPos.getX();
		int yCor = currentPos.getY();
		double changeInX,changeInY;
		changeInX = 0.0;
		changeInY = 0.0;
		if(!leader){
			Position leaderPos = getFlock().getLeader().getPos();
			setTarget(leaderPos.getX(),leaderPos.getY());
		}
		int newXCor = getTarget().getX();
		int newYCor = getTarget().getY();
		if(newXCor == xCor && newYCor == yCor){
			this.setPos(xCor,yCor);
		}
		else if(newXCor == xCor){
			if(newYCor > yCor){
				changeInY = speed;
			}
			else{
				changeInY = -speed;
			}
		}
		else if(newYCor == yCor){
			if(newXCor > xCor){
				changeInX = speed;
			}
			else{
				changeInX = -speed;
			}
		}
		else{
			double slope = (newYCor - yCor)/(newXCor-xCor);
			if(newXCor > xCor){
				changeInX = 1.0;
			}
			else{
				changeInX = -1.0;
			}
			changeInX = changeInX*(double)speed;
			changeInY = slope*changeInX; 

			if(changeInY > 5){
				changeInY = 5;
			}
			if(changeInY < -5){
				changeInY = -5;
			}
		}
		//Collision Avoidance//
		for(int i = 0;i < getFlock().getBirds().size();i++){
			int tempChangeX = getFlock().getBirds().get(i).getPos().getX();
			int tempChangeY = getFlock().getBirds().get(i).getPos().getY();
			if(xCor + (int)changeInX >= tempChangeX - 8 && xCor + (int)changeInX <= tempChangeX + 8){
				if(yCor + (int)changeInY >= tempChangeY - 8 && yCor + (int)changeInY <= tempChangeY + 8){
					if(leader){
						getFlock().getBirds().get(i).setPos(getFlock().getBirds().get(i).getPos().getX() + 20,getFlock().getBirds().get(i).getPos().getY() + 20);
					}
					else{
						//changeInX = -changeInX;
						//changeInY = -changeInY;	
						if(changeInX < 0){
							changeInX = 3; 
						}
						else{
							changeInX = -3;
						}
						if(changeInY < 0){
							changeInY = 3;
						}
						else{
							changeInY = -3;
						}
					}
				}
			}
		}
		setPos(xCor + (int)changeInX,yCor + (int)changeInY);
	}
	public void becomeLeader(){
		leader = true;
	}
	public void retireLead(){
		leader = false;
	}
}
