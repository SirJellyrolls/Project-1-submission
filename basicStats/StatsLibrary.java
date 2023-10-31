package sta.basicStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class StatsLibrary {

	StatsLibrary() {
	}
	// mean
	public double findMean(ArrayList<Double> userIn) {
		double sum = 0;
		for (double num : userIn) {
			sum += num;
		}
		double res = sum / userIn.size();
		return res;
	}
	// median
	public double median(ArrayList<Double> userIn) {
		ArrayList<Double> sorted = new ArrayList();
		for (double num : userIn) {
			sorted.add(num);
		}
		Collections.sort(sorted);
		int size = sorted.size();
		// in the case of even amount of elements take 0 1 2 3 4
		if (sorted.size() % 2 == 0) {
			return ((sorted.get(size / 2) + sorted.get((size / 2) - 1)) / 2);
		} else {
			return (sorted.get(((size + 1) / 2) - 1));
		}

	}

	// mode
	public double mode(ArrayList<Double> userIn) {
		double finalNum = 0.0;
		int finalCount = 0;
		int secondCount =0;
		for (int cur = 0; cur < userIn.size(); cur++) {
			int counter = 1;
			for (int nxt = cur + 1; nxt < userIn.size(); nxt++) {
				boolean setFlag=false;
				if (userIn.get(cur).equals(userIn.get(nxt))) {
					counter++;
				}
				if (counter > finalCount) {
					finalCount = counter;
					finalNum = userIn.get(cur);
					setFlag=true;
				}
				if(counter>secondCount&&setFlag==false) {
					secondCount=counter;
				}
			}if(finalCount==secondCount) {
				System.out.println("No mode found in the set of data ");
				return 0.0;
			}

		}
		return finalNum;
	}
	public double variance(ArrayList<Double> userIn){
		double avg=findMean(userIn);
		double sum=0;
		for(Double num:userIn){
			sum=sum+(Math.abs(num-avg));
		}
		double finalRes=sum/userIn.size();
		return finalRes;
	}

	// standard ddeviation
	public double standDev(ArrayList<Double> userIn) {
		double average = findMean(userIn);
		double finalAnswer = 0;
		double summation = 0;
		for (double num : userIn) {
			summation += ((num - average) * (num - average));
		}
		finalAnswer = Math.sqrt(summation / (userIn.size() - 1));

		return finalAnswer;
	}

}
