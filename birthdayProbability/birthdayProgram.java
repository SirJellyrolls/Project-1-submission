package sta.birthdayProbability;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

public class birthdayProgram {
	// Goal determine the probability of any 2 people sharing a birthday in the
	// class Read:count ppl here
	// requirements
	// make program felxible have user input parameter
	// make a person class with int birthday
	// can use mm/dd
	// do not have to account for leap year
	// no hardcode numbers

	public void birthdayProb(int groupSize,int trials){
		//for num of trials gen a class room fill it with group size find if there are any matches 
		//divide total number of trials that had matches by trials
		int matches=0;
		for(int curTri=0;curTri<trials;curTri++){
			//making a room full of birthdays
			ArrayList<Integer> births=new ArrayList();
			
			for (int i=0;i<groupSize;i++){
				//generate a person with random birthday and add to room
				Random r = new Random();
				Person p = new Person(((r.nextInt(365)) + 1));
				births.add(p.getBirth());
			}
			
			//check two see if any two people share a birthday in a class
			for(int i =0;i<births.size();i++){
				int temp=births.get(i);
				births.remove(i);
				if(births.contains(temp)){
					//if we find an instance of a matching birthday update matches then break and go to the next room of people
					matches=matches+1;
					break;
				}
			}	
			}
			System.out.println(matches+" matches and "+trials+" trials results in a: "+((float)matches/(float)trials)+" rate for a pair of matching birthdays when "+groupSize+" are in a room");

		}

}
