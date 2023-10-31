package sta.basicStats;

import java.util.ArrayList;

public class statsTest {

	public static void main(String[] args) {
		StatsFormulas hg=new StatsFormulas();
		
		System.out.println(hg.hyperGeoVary(20,6,8));
		System.out.println(hg.chebyTheo(20, 2, 10));
	}
}
