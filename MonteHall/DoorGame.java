package sta.MonteHall;

import java.util.ArrayList;
import java.util.Random;

public class DoorGame {
	private int numOfPlays;

	public DoorGame(int plays) {
		numOfPlays = plays;
	}
	
	public boolean genGame(boolean changeDoors) {
		//this method displays the results of a single month hall game
		
		//create a winning door and a chosen door
		boolean gameFlag = false;
		Random r = new Random();
		int winningDoor = r.nextInt(3);
		int selected = r.nextInt(3);

		//if the doors are meant to change this loop will run
		//it removes the first non-userSelected losing door and then swaps the chosen door to the other one
		//Example. doors 1 and 3 are losers you choose door 1 then door 3 will open
		if (changeDoors == true) {
			ArrayList<Integer> choice = new ArrayList();
			choice.add(0);
			choice.add(1);
			choice.add(2);
			boolean removeFlag = false;
			for (int i = 0; i < 2; i++) {
				if (i != winningDoor && i != selected && removeFlag == false) {
					//if door ISNT the winning one and isn't the user selected remove door
					choice.remove(i);
					removeFlag = true;
					break;
				}
			}
			for (int num : choice) {
				//check remaining doors 
				if (num != selected) {
					//update slected choice to the other remaing door
					selected = num;
					break;
				}
			}
		}
		//check if new door chosen is a winner or not
		//The above loop will be skipped if we run a simutation where we do NOT switch doors
		if (winningDoor == selected) {
			gameFlag = true;
		}
		return gameFlag;
	}
	public void test(int val) {
		//Method used to find the win rate of game when swapping v.s not swapping
		//runs a defined user defined amount of time
		// this section computes win rate when there IS NOT a swap

		//start with 0 wins
		double winCount = 0; 
		for (int i = 0; i < val; i++) {
			boolean res = genGame(false);
			if (res == true) {
				//for every win without a changing doors increase winCount
				winCount++;
			}
		}
		double winRate = (winCount / val) * 100; //set winRate for no swap

		// this section calculates win rate when swapping
		// start wins with -
		double winSwap = 0;
		for (int i = 0; i < val; i++) {
			if (genGame(true) == true) {
				//increase if there is a win when doors are switched
				winSwap++;
			}
		}
		double swapRate = (winSwap / val) * 100;//calculate win rate when swapping doors

		System.out.println("Win rate when you DONT switch is:" + winRate + "%");
		System.out.println("Win rate when you DO switch is:" + swapRate + "%");

	}

}
