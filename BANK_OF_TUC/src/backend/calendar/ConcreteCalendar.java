package backend.calendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


public class ConcreteCalendar extends CalendarObservable {
    
	private Calendar curDate;
	private static ConcreteCalendar instance;
	private ArrayList<CalendarObserver> observers; 
	
    public ConcreteCalendar() {
        this.curDate = Calendar.getInstance();
        this.observers = new ArrayList<CalendarObserver>();
    }
    
    public static ConcreteCalendar getCalendar() {
    	if(instance == null)
    		instance = new ConcreteCalendar();
    	return instance;
    }
    
    public static int[] convertDateToArray(String date) {
    	String[] values = date.split("-");
    	int[] ymd = {0,0,0};
    	for(int i=0; i<=2; i++) {
    		ymd[i] = Integer.parseInt(values[i]);
    	}
    	return ymd;
    }
    
    @Override
    public void register(CalendarObserver observer) {
         this.observers.add(observer);
    }
    @Override
    public void unregister(CalendarObserver observer) {
        this.observers.remove(observer);
    }
    @Override
    public void notification() {
        for(int i = 0; i < this.observers.size(); i++) {
//            this.observers.get(i).update();
        }
    }
    
    public boolean dateCompare(String givenDate) {
    	if(givenDate.equals(curDateString()))
    		return true;
    	return false;
    }
    
//    public String calculateDifference(String futureDate) {
//    	int[] formattedFutureDate = convertDateToArray(futureDate);
//    	
//    }
    
    public String curDateString() {
    	return getCurDate().get(Calendar.YEAR)+"-"+(getCurDate().get(Calendar.MONTH)+1)+"-"+getCurDate().get(Calendar.DATE);
    }
    
    public String curTimeString() {
    	return getCurDate().get(Calendar.HOUR_OF_DAY)+":"+getCurDate().get(Calendar.MINUTE);
    }
    
    public Calendar getCurDate() {
		return curDate;
	}


	public void passDay() {
        this.curDate.add(Calendar.DATE, 1);
        //notification();
    }

    public void fastForward(int type, int amount) {
        this.curDate.add(type, amount);
        notification();
    }
    
    public void loadDate() {
    	try (BufferedReader br = new BufferedReader(new FileReader("savedDate.txt"))) {
		    String date;
		    date = br.readLine();
		    int[] convertedDate = convertDateToArray(date);
		    this.curDate.clear();
		    this.curDate.set(convertedDate[0], convertedDate[1]-1, convertedDate[2]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void saveDate() throws IOException {
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter("savedDate.txt"))){
    		bw.write(curDate.get(Calendar.YEAR)+"-"+(curDate.get(Calendar.MONTH)+1)+"-"+curDate.get(Calendar.DAY_OF_MONTH));
    	}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
    }

    
