package sta.BasicStats;

import java.util.ArrayList;

public class SetOperations {
	// pick string or integer
	// set operation Union on two arraylist
	// write a method that intersects
	// write a method that returns complement
	// ---write method that accepts 2 parameters
	// ---an arraylist of all values in sample and one that accepts subset
	// ----ex
	public ArrayList union(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		ArrayList<Integer> unionList = new ArrayList();
		for (int val : list1) {
			if (list2.contains(val)) {
				unionList.add(val);
			}
		}

		return unionList;
	}

	public ArrayList intersect(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		ArrayList<Integer> inter = new ArrayList();
		for (int val : list1) {
			inter.add(val);
		}
		for (int val : list2) {
			if (inter.contains(val) == false) {
				inter.add(val);
			}
		}
		return inter;
	}
	
	public ArrayList complement(ArrayList<Integer> sample,ArrayList<Integer> subset) {
		ArrayList<Integer> comp= new ArrayList();
		for(int val: sample) {
			if (subset.contains(val)==false) {
				comp.add(val);
			}
		}
		
		return comp;
	}
}
