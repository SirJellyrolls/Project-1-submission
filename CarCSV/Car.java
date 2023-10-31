package sta.CarCSV;

public class Car {
	private String carType;
	private int year;
	private String color;
	private int miles;

	public Car(String userType, int userYear, String userColor, int userMiles) {
		carType=userType;
		year=userYear;
		color=userColor;
		miles=userMiles;
	}
	public String getType() {
		return carType;
	}
	public int getYear() {
		return year;
	}
	public String getColor() {
		return color;
	}
	public int getMiles() {
		return miles;
	}
}