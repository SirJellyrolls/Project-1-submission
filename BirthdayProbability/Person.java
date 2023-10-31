package sta.BirthdayProbability;
import java.util.Random;
public class Person {
	private int birthday;
	
	/*public Person() {
		Random birth=new Random();
		int born=birth.nextInt(365);
		birthday=born+1;
	}*/
	public Person(int born){
		birthday=born;
	}

	public int getBirth(){
		return birthday;
	}
	public String toString(){
		return(Integer.toString(birthday));
	}
	public boolean compare(Person user){
		if(birthday==user.getBirth()){
			return true;
		}else{
			return false;
		}
	
	} 
	public boolean equals(Object o) {
		if(birthday==((Person) o).getBirth()){
			return true;
		}else{
			return false;
		}
	}
	

}
