package sta.Project2;
import java.util.ArrayList;
import java.util.Random;
public class Plotter {
    

    public ArrayList plotEquation(int numOfPts,int a,int b,int c){
        //y=x^2+x+c
        ArrayList<Double> points=new ArrayList();
        ArrayList<double[]> xy=new ArrayList<>();
        for(double i=0;i<numOfPts;i=i+.5){
            double xSq=a*Math.pow(i, 2);
            double xSing=b*i;
            double y=xSq+xSing+c;
            points.add(y);
            double[] pnt={i,y};
            xy.add(pnt);
            //System.out.println(y);
        }
        System.out.println(xy.toString());
        return xy;
        
    }
    public ArrayList salter(ArrayList<double[]> points){
        ArrayList<double[]> salted=new ArrayList();
        Random r = new Random();
        for(double[] point:points){
            int neg=r.nextInt(2);//0 add 1 subtract
            if(neg==0){
            double saltX=point[0]+r.nextInt(10);
            double saltY=point[1]+r.nextInt(10);
            double[] saltedPair={saltX,saltY};
            salted.add(saltedPair);
            }
            if(neg==1){
            double saltX=point[0]-r.nextInt(10);
            double saltY=point[1]-r.nextInt(10);
            double[] saltedPair={saltX,saltY};
            salted.add(saltedPair);
            }
           
        }
        System.out.println(salted);
        return salted;
    }
    public void removeSalt(int saltVal){

    }
    public void printPoints(ArrayList<double[]> points){
        for(double[] point:points){
            System.out.print("("+point[0] +","+point[1]+") ");
        }
    }
    public void plotCircle(int numOfPts,double horiShift,double vertShift){
        //(x-h)^2+(y-k)^2=r^2
        for(int i =0; i <numOfPts;i++){
            double x=Math.pow((i-horiShift),2.0);
            double y=Math.pow((i-vertShift),2.0);
            double r =Math.pow((x*y),.5);
            System.out.println(r);
        }

    }
}
