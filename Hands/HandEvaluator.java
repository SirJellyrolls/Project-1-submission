package sta.Hands;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class HandEvaluator {
    private ArrayList<Card> hand;
    private LinkedList<Card> deck; //deck is linked list because we remove cards from random points multiple times
    private int royalFlushCount;
    private int straightFlushCount;
    private int fourCount;
    private int fullHouseCount;
    private int flushCount;
    private int straightCount;
    private int threeCount;
    private int twoPairCount;
    private int pairCount;
    private int highCardCount;
    private int handSze;
    public HandEvaluator(){
        //Constructor to initalize deck and hands. The deck is also populated with card at this point.
        hand=new ArrayList();
        deck=new LinkedList();
        //Ace=1  Jack=11 Queen=12 King=13
        int[] cardVals=new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13};
        String[] suites=new String[]{"Hearts","Spades","Diamonds","Clover"};
        //for each card type populate deck with cards ace,2,...,queen,king
        for (int type=0;type<4;type++){
        
        for(int i =0 ;i <cardVals.length;i++){
            deck.add(new Card(suites[type],cardVals[i]));
        }
    }
    handSze=0;
    royalFlushCount=0;
    straightFlushCount=0;
    fourCount=0;
    fullHouseCount=0;
    flushCount=0;
    straightCount=0;
    threeCount=0;
    twoPairCount=0;
    pairCount=0;
    highCardCount=0;
    
    }
    public void drawHand(int HandSize){
        handSze=HandSize;
        // Magician "Pick a card approach" - deck stays in order but the one drawn is chosen from a random position in the deck
        Random newCard = new Random();
        for(int i=0;i<HandSize;i++){
            int cardPostion=newCard.nextInt(deck.size());
            //Add card to hand and remove from deck
            Card drawnCard=deck.get(cardPostion);
            deck.remove(cardPostion);
            hand.add(drawnCard);
        }
    }
    public void returnHand(){
        for(int i=0;i<handSze;i++){
            deck.add(hand.get(0));
            //System.out.print(deck.size());
            hand.remove(0);
        }
        
    }
    public void printStats(int handSize, int loopTotal) throws IOException{
    //File name is based upon handSize amount of hands we check
       FileWriter stats = new FileWriter("HandSize"+handSize+"looped"+loopTotal+".csv");

       //Write down all stats to csv
	   BufferedWriter writer = new BufferedWriter(stats);
       writer.write("Royal Flushes,"+ royalFlushCount+"\n");
       writer.write("Straight Flushes,"+ straightFlushCount+"\n");
       writer.write("Four of a Kinds,"+ fourCount+"\n");
       writer.write("Full Houses,"+ fullHouseCount+"\n");
       writer.write("Flushes,"+ flushCount+"\n");
       writer.write("Straights,"+ straightCount+"\n");
       writer.write("Thee of a kinds,"+ threeCount+"\n");
       writer.write("Two Pairs,"+ twoPairCount+"\n");
       writer.write("Piars,"+ pairCount+"\n");
       writer.write("High cards,"+ highCardCount+"\n");
       writer.close();
      
    }
     public void run(int num,int loops){
        for(int i =0;i<100;i++){
        //draw the desired number of cards
        drawHand(num);
        System.out.println(hand);
        //create and display sorted hands of card 
        //This part is to confirm/make output more clear 
        ArrayList<Card> cop=(ArrayList)hand.clone();
        Collections.sort(cop);
       // System.out.println(cop);

       //Out put result the given hand.
        outputHand();
        returnHand();
        System.out.println(hand);
        
    }try {
        printStats(num,loops);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }

    public void outputHand(){
        //Method to find results of hand and update our overall types of hands
        //if else statemnts are in order of best(royal flush)-worse(high card) possible hands
        if(findRoyalFlush()){
            royalFlushCount++;
          //  System.out.println("Congrats on the ROYAL Flush!!!");
        }else if(findStraightFlush()){
            straightFlushCount++;
          //  System.out.println("Congrats on the STRAIGHT Flush!!!");
        }else if(findFour()){
            fourCount++;
         //   System.out.println("Congrats on FOUR OF A KIND!!!");
        }else if(findFullHouse()){
            fullHouseCount++;
         //   System.out.println("Congrats on A FULL HOUSE!!!");
        }else if(findFlush()){
            flushCount++;
         //   System.out.println("you got a FLUSH");
        }else if(findStraight()){
            straightCount++;
          //  System.out.println("Congrats on A STRIAGHT!!!");
        }else if(findThree()){
            threeCount++;
        //    System.out.println("Congrats on 3 OF A KIND!!!");
        }else if(findDoublePair()==true){
            twoPairCount++;
        //    System.out.println("You got 2 PAIRS good job :D");
        }else if(findPair()==true){
            pairCount++;
         //   System.out.println("Congrats on A SINGLE PAIR OF CARDS!!!");
        } else{
            highCardCount++;
           // System.out.println("You have an "+findHighCard().getValString()+" high");
        }
        
    }
    private boolean findRoyalFlush(){
        //defined by 10,jack,queen,king, ace of the same of same suite
        for(int i =0;i<hand.size()-1;i++){
            int matches=0;
            if(hand.get(i).getVal()==1||hand.get(i).getVal()==10||hand.get(i).getVal()==11||hand.get(i).getVal()==11||hand.get(i).getVal()==12||hand.get(i).getVal()==13){
                matches++;
                //see if the inital card fits within Number range of card if it is add itto match
            for(int j=i+1;j<hand.size();j++){
                //Now take card i and its suite to comapre against every other card in front of it
                String suite = hand.get(i).getFace();
                Card tempCard=hand.get(j);
                if(tempCard.getFace().equals(suite)){
                    //find out if card is eligble for suite match
                    if(tempCard.getVal()==1||tempCard.getVal()==10||tempCard.getVal()==11||tempCard.getVal()==12||tempCard.getVal()==13){
                    //if the resulting card is ace,10,jack,queen,king with the same suite as intial card increase out matches
                    matches=matches+1;
                    }
                }
            }}
            if (matches>=5){
                //if we end up finding that there are 5 valid cards then we have the royal flush
                return true;
            }
        }
        //if no royal flush return flush
        return false;
    }

    private boolean findStraightFlush(){
        //in order and same suite
        //Sort the hand so we can go through the deck easily
        ArrayList<Card> sortedHand=(ArrayList)hand.clone();
        Collections.sort(sortedHand);
        boolean flag=false;
        for(int i =0;i<sortedHand.size()-5;i++){
            //Current card will always be valid for its own match count
            int matches=1;

            //get suite and value of inital card 
            String suiteQuery=sortedHand.get(i).getFace();
            int valQuery=sortedHand.get(i).getVal();

            //check the next 4 cards to see if they are in order
            for(int j=i;j<(i+5);j++){
                if (sortedHand.get(j).getVal()==valQuery+matches&&sortedHand.get(j).getFace()==suiteQuery){
                    //Because we have a sorted hand we can use the match counter to keep track of what value we are looking for
                    //The first card should be val+1 and matches starts at 1 so val+1==val+matches for first case and will continue to increase/keep track for next 3 cases
                    matches=matches+1;
                }
            }if(matches>=5){
                //if at least 5 cards with the same suite are in order then there is a straight flush
                flag=true;
            }
        }
       //return if there is a straight flush or not
        return flag;
    }
    private boolean findStraight(){
        //Start with a sorted hand Straight is ordered and matching suites
        //A STRAIGHT IS BASED ON ONLY ORDER
        ArrayList<Card> sortedHand=(ArrayList)hand.clone();
        Collections.sort(sortedHand);
        boolean flag=false;
        for(int i =0;i<sortedHand.size()-1;i++){
            //inital card will always be a valid match for itself
            int matches=1;
            int valQuery=sortedHand.get(i).getVal();
            ArrayList<Integer> blacklist=new ArrayList();

            //blackList used to keep track of values already iterated over)
            blacklist.add(sortedHand.get(i).getVal());
            for(int j=i+1;j<sortedHand.size();j++){
                if (blacklist.contains(sortedHand.get(j).getVal())==false){ //if the card has a value that hasn't been looked at yet 
                    if(sortedHand.get(j).getVal()==valQuery+1||sortedHand.get(j).getVal()==valQuery+2||sortedHand.get(j).getVal()==valQuery+3||sortedHand.get(j).getVal()==valQuery+4){
                    //If the comapredCard is within 4 of the current's val there is a valid match
                        blacklist.add(sortedHand.get(j).getVal());
                        //add that value to blacklist so we dont have false positive EX:2,3,4,4,5=TRUE
                        matches=matches+1;}
                }
            }if(matches>=4){
                System.out.println(hand);
            //if there are five cards that can be place in order you have a straight
                flag=true;
                break;
            }
        }
       //return the output of the straight status
        return flag;
    }
    private boolean findFour(){
        //only finding four cards with same value
        boolean fourFlag=false;
        ArrayList<Card> sortedHand=(ArrayList)hand.clone();
        Collections.sort(sortedHand);
        for(int i=0;i<sortedHand.size()-1;i++){
            //every card is 1/4 of four a kind so matches=1
            int matches=1;
            for(int j=i+1;j<sortedHand.size();j++){
                if(sortedHand.get(i).getVal()==sortedHand.get(j).getVal()){
                   //update matches for every other card of the same numerical value
                    matches=matches+1;
                }
            }if(matches>=4){
                //if there are at least four matches of value update four of a kind flag
                fourFlag=true;
            }
        }
        //return four of a kind results
        return fourFlag;
    }
    private boolean findFullHouse(){
        //A full house is a pair of value X and three value Y
        boolean three=false;
        boolean pair=false;
        ArrayList<Card> sortedHand=(ArrayList)hand.clone();
        Collections.sort(sortedHand);
        //blackList value used to keep track of our three of a kind
        int blackList=0;
        //find matches of at least three cards
        for(int i=0;i<sortedHand.size()-1;i++){
            int matches=1;
            for(int j=i+1;j<sortedHand.size();j++){
                if(sortedHand.get(i).getVal()==sortedHand.get(j).getVal()){
                    matches=matches+1;
                }
            }if(matches>=3){    
                //set three of a kind flag
                three=true;
                //update blackList value to the value of the three of a kind so that there is an accidental false pair Example:(3,3,3,2,1 OUT:3=three of a kind 3=pair) is wrong
                blackList=sortedHand.get(i).getVal();
                break;
            }
        }
        //finding a pair that hasn't been found yet AKA isnt a part of the three of a kind
        for(int i=0;i<sortedHand.size()-1;i++){
            int matches=1;
            for(int j=i+1;j<sortedHand.size();j++){
                //check for value match and check that the value isn't the blackListed one
                if(sortedHand.get(i).getVal()==sortedHand.get(j).getVal()&&sortedHand.get(i).getVal()!=blackList){ 
                    matches=matches+1;
                }
            }if(matches>=2){
                //set pair flag
                pair=true;
                break;
            }
        }
        if(pair&&three){
            //if you have pair and three of a kind you have a full house
            return true;
        }else{
        return false;
    }
    }
    private boolean findFlush(){
        boolean flush=false;
        ArrayList<Card> tempHand=(ArrayList) hand.clone();
        Collections.sort(tempHand);
        //Cards are sorted by size and suite that way we only need to check next four cards
        for(int i =0;i<tempHand.size()-4;i++){
            String suite= tempHand.get(i).getFace();
            if(tempHand.get(i+1).getFace()==suite&tempHand.get(i+2).getFace()==suite&tempHand.get(i+3).getFace()==suite&tempHand.get(i+4).getFace()==suite){
                //if the current card followed by the next 4 have matching suites you have a flush update flag
                flush=true;
                break;
            }
        }return flush;
    }
    private boolean findThree(){
        boolean threeFlag=false;
        ArrayList<Card> sortedHand=(ArrayList)hand.clone();
        Collections.sort(sortedHand);
        for(int i=0;i<sortedHand.size()-1;i++){
            int matches=1;
            //for every card you have 1/3 of a kind so set matches to 1 
            for(int j=i+1;j<sortedHand.size();j++){ 
                if(sortedHand.get(i).getVal()==sortedHand.get(j).getVal()){
                //if card i and the card being compared are the same value update our matches    
                    matches=matches+1;
                }
            }if(matches>=3){
            //if there are at least 3matches AKA 3of a kind then set our flag to true
                threeFlag=true;
            }
        }
        //return final stauts 
        return threeFlag;
    }
    private boolean findDoublePair(){
        ArrayList<Integer> blackList=new ArrayList<>();//Used for pairs already found
        int matches=0;//used to find numb of pairs
        //loop through all card in hand to find matches
        for (int i=0;i<hand.size()-1;i++){
            int current=hand.get(i).getVal();
            for (int j=i+1;j<hand.size();j++){
                if (current==hand.get(j).getVal()& blackList.contains(current)==false){
                    //if there is a pair that hasn't already then we have valid match
                    //update amount of matches/pairs found
                    matches++;
                    //Add Value to blackList if cards compared are a Pair and arent already there
                    blackList.add(current);
                }
            }
        }
        if (blackList.size()>=2){
        //If blackList is at least two there are at least matching pairs so return true
            return true;
        }else{
            return false;
        }
    }
    private boolean findPair(){
        boolean pair=false;
        int matches=0;
        for(int i=0;i<hand.size()-1;i++){
            //for every card but the last check if there is another matching card
            Card temp=hand.get(i);
            for(int j=i+1;j<hand.size();j++){
                if(temp.getVal()==hand.get(j).getVal()){
                    //if we find a matching card (AKA a pair) update our total number of matches
                    matches=matches+1;
                    break;
                }
            }if(matches>=1){
                //if there is a pair we can update flag to true;
                pair=true;
                break;
            }
        }//output results
        return pair;
    }
    private Card findHighCard(){
        //searches for highest card uses
        int high=0;
        Card highestCard=hand.get(0);
        for(Card temp:hand){
            if (temp.getVal()>high&high!=1){
                //highCard will be updated if the high value is lower AND is not an ACE(1) Note:ace is highest high card
                high=temp.getVal();
                highestCard=temp;
            }
        }return highestCard;
    }

   

}
