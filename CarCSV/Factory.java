package sta.CarCSV;

import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://docs.oracle.com/javafx/2/charts/bar-chart.htm  <----source


public class Factory {
	private Random carGenerator;
	private ArrayList<Car> garage;

	public Factory() {
		carGenerator = new Random();
		garage = new ArrayList();
	}

	public void genCars() {
		//start with an array of cars
		String[] carTypes = new String[] { "Sedan", "Truck", "Van", "Motorcycle" };
		String[] carColors = new String[] { "red", "blue", "purple", "flamingoPink" };

		for (int i = 0; i < 1000; i++) {
			//generate 1000 cars with all random values for type year model and miles
			Car c = new Car(carTypes[carGenerator.nextInt(4)], ((carGenerator.nextInt(64)) + 1960),
					carColors[carGenerator.nextInt(4)], carGenerator.nextInt(250000));
			garage.add(c);
		}

	}

	public void writeCsv() {
		genCars(); //becuase we will always need data to write genCars for data generation
		FileWriter carInfo;
		try {
			carInfo = new FileWriter("GarageInfo.csv"); //create GarageInfo that hold intial garage of cars
			BufferedWriter writer = new BufferedWriter(carInfo); //create a writer to write data
			

			writer.write("Type, Year, Model, Mileage\n");//write header
			for (Car c : garage) {//copy data down for every car
				writer.write(c.getType() + ", " + c.getYear() + ", " + c.getColor() + ", " + c.getMiles() + "\n");
			}
			writer.close();
			System.out.println("done");
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void openOutput(){ //Extra credit possiblity 
		//NOTE: This method only works with excel installed in default location
		String sys=System.getProperty("os.name");
		//first get the current operating system
		try{
			if(sys.contains("Windows")){
			 String command="cmd /c start excel.exe GarageInfo.csv";
			 String ss="cmd /c start excel.exe ReadedTheInfo.csv";
			 //If OS is windows run cmd commands to open excel file from command line
			 //regular command line doesn't need cmd /c but it is needed for runtime to run it problery 
			 
			 //use JavaRuntime to interact with runtime enviorment and exec(ute) the command to open file
			 Runtime.getRuntime().exec(command); 
			 Runtime.getRuntime().exec(ss);
			 
			}else if(sys.contains("Mac")){
		
			 String[] command={"open","-a","Microsoft Excel","GarageInfo.csv"};
			 String[] c2={"open","-a","Microsoft Excel","ReadedTheInfo.csv"};
			 //If OS is mac we must use a String array becuase mac terminal input is typically: open -a :"Microsfot Excel" filename.csv
			 //Using the string runtime is able to run command normally
			 Runtime.getRuntime().exec(command);
			 Runtime.getRuntime().exec(c2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void readCsv() {
		//Hashmap used to store colors along with the amount of sedans of that color
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm.put("red", 0);
		hm.put("blue", 0);
		hm.put("purple", 0);
		hm.put("flamingoPink", 0);

		try (BufferedReader br = new BufferedReader(new FileReader("GarageInfo.csv"))) {
			Pattern sed = Pattern.compile("Sedan");
			Pattern red = Pattern.compile("red");
			Pattern blue = Pattern.compile("blue");
			Pattern purp = Pattern.compile("purple");
			Pattern flam = Pattern.compile("flamingoPink");
			//setup patterns for info we want to find, this case its colors of sedans
			
			String h = br.readLine();
			while (h != null) {
				Matcher m = sed.matcher(h);
				Matcher r = red.matcher(h);
				Matcher b = blue.matcher(h);
				Matcher p = purp.matcher(h);
				Matcher fl = flam.matcher(h);

				//find lines that have a match for sedan and color then update its hashMap value
				if (r.find() && m.find()) {
					int temp = hm.get("red") + 1;
					hm.replace("red", (temp + 1));
				}
				// blue
				if (b.find() && m.find()) {
					int temp = hm.get("blue") + 1;
					hm.replace("blue", temp);
				}
				// purple
				if (p.find() && m.find()) {
					int temp = hm.get("purple");
					hm.replace("purple", (temp + 1));
				}
				// pink
				if (fl.find() && m.find()) {
					int temp = hm.get("flamingoPink");
					hm.replace("flamingoPink", (temp + 1));
				}
				h = br.readLine();
			}
			//Create a new file for the GarageInfo that was read
			FileWriter finalInfo=new FileWriter("ReadedTheInfo.csv");
			BufferedWriter infoWriter= new BufferedWriter(finalInfo);
			infoWriter.write("Sedan color, #of Sedans\n");//write header to new csv
			
			for(Map.Entry<String, Integer> car:hm.entrySet()) {
				//add hashMap values of specific colored sedan and its occuerance 
				infoWriter.write(car.getKey()+", "+car.getValue()+"\n");
			}
			infoWriter.close(); //close writer
			System.out.println(hm);

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}

