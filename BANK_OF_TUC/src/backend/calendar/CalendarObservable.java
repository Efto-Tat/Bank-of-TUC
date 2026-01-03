package backend.calendar;
import java.util.ArrayList;
import java.util.Calendar;

import backend.Interaction;

public abstract class CalendarObservable {
    protected Calendar curDate;
    protected ArrayList<CalendarObserver> observers;
    
    
    public abstract void register(CalendarObserver observer);
    
    public abstract void unregister(CalendarObserver observer);
    
    public abstract void notification();
    
    
}