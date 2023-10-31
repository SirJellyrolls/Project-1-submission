package sta.Project2;

import java.util.ArrayList;

public class Proj2Tester {
    public static void main(String[] args){
        Plotter p= new Plotter();
        ArrayList<double[]> a=p.plotEquation(10,1,1,1);
        ArrayList<double[]> salt=p.salter(a);
        p.printPoints(a);
        p.printPoints(salt);
    }

  
}
