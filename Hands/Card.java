package sta.Hands;

public class Card implements Comparable<Card> {
    private String face;
    private int value;
    private int faceVal;

    public Card(String userFace,int userVal){
        face=userFace;
        value=userVal;
        
        //The faceValue is used only in sorting
        if (userFace.equals("Hearts")){
            faceVal=1;
        }if (userFace.equals("Spades")){
            faceVal=2;
        }if (userFace.equals("Diamonds")){
            faceVal=3;
        }if (userFace.equals("Clover")){
            faceVal=4;
        }
    }

    //Getters
    public String getFace(){
        return face;
    }
    public int getVal(){
        return value;
    }
    private int getFaceVal(){
        return faceVal;
    }
    public String getValString(){
        //Used to display just the number as speical value(Jack,Heart,Queen,Ace)
        //only really used for highCard output
        String faceValue=Integer.toString(value);
        if(value==1){
            faceValue="Ace";
        }
        if(value==11){
            faceValue="Jack";
        }
        if(value==12){
            faceValue="Queen";
        }
        if(value==13){
            faceValue="King";
        }
        return (faceValue);
    }
    @Override
    public String toString(){
        //Used to display the card based upong if its Ace,jack,queen,king or not
        String faceValue=Integer.toString(value);
        if(value==1){
            faceValue="Ace";
        }
        if(value==11){
            faceValue="Jack";
        }
        if(value==12){
            faceValue="Queen";
        }
        if(value==13){
            faceValue="King";
        }
        return(face+": "+faceValue);
    }
    @Override
    public int compareTo(Card userCard) {
    //Used for sorting with collections.sort
    //Comapres by number on card if they have the same suite
    if(face.equals(userCard.getFace())==true){ 
        if(value>userCard.getVal()){
            return 1;
        }else if(value<userCard.getVal()){
            return -1;
        }else{
            return 0;
        }
    }else{//if the card have different suites compare them by suites
        if(faceVal>userCard.getFaceVal()){
            return 1;
        }else if(faceVal<userCard.getFaceVal()){
            return -1;
        }else{
            return 0;
        }

    }
    }
    

}
