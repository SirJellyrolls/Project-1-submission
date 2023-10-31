package sta.basicStats;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class StatsFormulas {
    
    public double binoDistri(int nNumTrials,int yNumSuc,double pSucP,double qFailP ){
        //(n choose y)*p^y*q^(n-y)
        double bioCombo=combo(nNumTrials, yNumSuc).doubleValue();
        double pVal=Math.pow(pSucP, yNumSuc);
        double qVal=Math.pow(qFailP, nNumTrials-yNumSuc);
        double finalVal=bioCombo*pVal*qVal;
        return finalVal;
    }
    public double binoExpect(int nSucc,double pSuccP){
        return(nSucc*pSuccP);
    }
    public double binoVary(int nSucc,double pSuccP,double qFailP){
        return(nSucc*pSuccP*qFailP);
    }
    public double geometricDistri(int yEventSucc,double pSuccP,double qFailP){
        double newQ=Math.pow(qFailP, yEventSucc-1);
        return(newQ*pSuccP);
    }
    public double geometricExpect(double pSuccP){
        return(1/pSuccP);
    }
    public double geometricVary(double pSuccP){
        double q=1-pSuccP;
        double p2=Math.pow(pSuccP, 2);
        return(q/p2);
    }
    public float hyperGeo(int bigNTotalPeople,int littleNSpotsRemain, int ySelectDesired, int rTotalDesired){
        BigInteger rChooseY=combo(rTotalDesired, ySelectDesired);        // r choose y
        BigInteger subtractCombo=combo(bigNTotalPeople-rTotalDesired,littleNSpotsRemain-ySelectDesired);  //this is the N-r choose n-y
        BigInteger numerator=rChooseY.multiply(subtractCombo); //creates numerator 
        BigInteger denominator=combo(bigNTotalPeople, littleNSpotsRemain);    //N choose n which is denomiantor 
        float probability=numerator.floatValue()/denominator.floatValue();  //divide numerator by denominator 
        return probability;
    }
    public double hyperGeoExpect(int bigNTotalPeople,int littleNSpotsRemain,int rTotalDesired){
        double nr=littleNSpotsRemain*rTotalDesired;
        return(nr/bigNTotalPeople);
    }
    public double hyperGeoVary(int bigNTotalPeople,int littleNSpotsRemain,int rTotalDesired){
        double rOverN=(double)rTotalDesired/bigNTotalPeople;
        double NminR=((double)bigNTotalPeople-rTotalDesired)/(bigNTotalPeople);
        double Nminn=((double)bigNTotalPeople-littleNSpotsRemain)/(bigNTotalPeople-1);
        return ((double)littleNSpotsRemain*rOverN*NminR*Nminn);
    } 
    public float negBio(int y,int r,double p,double q){
        int newY=y-1;
        int newR =r-1;
        BigInteger negCombo=combo(newY, newR);
        double probP= Math.pow(p, r);
        double probQ= Math.pow(q, (y-r));
        float overallProb=(float) (negCombo.floatValue()*probP*probQ);
        return overallProb ;
     }
    public double poissonDis(double lambda,double y){
        double lambExpo= Math.pow(lambda, y);//lambda^y
        double oil=Math.pow(Math.E, 0-lambda);//e^-lambda
        double yFact=factorial((int)y).doubleValue();//y!
        double finalVal=(lambExpo*oil);
        finalVal=finalVal/yFact;
       
        return (finalVal);
    }

    //add chebyTheo
    public double chebyTheo(double mean,double stDevi,double inValue){
        //k= abs(in-mean)/stDevi 
         double k=((Math.abs(inValue-mean))/stDevi);
        //1-(1/k^2)
        double percent=1.0-(1/Math.pow(k, 2));
        return percent;
    }
    
    public BigInteger combo(int total,int part){
        BigInteger whole=factorial(total);  //N!
        BigInteger portion=factorial(part);  //y!
        BigInteger subPort=factorial(total-part);  //part of denominator that is (N-y)!
        BigInteger bd=whole.divide((portion.multiply(subPort)));  //divide numerator by denominator N/((y!)*((N-y)!))
        return(bd);
    }
    
    public BigInteger permutation(int total,int partSelected){
        BigInteger numerator=factorial(total);//n!
        BigInteger denom=factorial(total-partSelected);//(n-r)!
        BigInteger finalResult=numerator.divide(denom);// n!/((n-r)!)
        return finalResult;
    }
    public BigInteger factorial(int number){
        if(number==0){
            return BigInteger.ONE;
        }
        BigInteger fact=BigInteger.ONE;
        for(int i =1;i<number+1;i++){
            fact=fact.multiply(BigInteger.valueOf(i));
        }
        if (number==0){
            fact=BigInteger.ONE;
        }
        return fact;
        
    }
    
}
