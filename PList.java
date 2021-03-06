import java.util.ArrayList;
import java.util.Iterator;
/**
 * Class PList
 * 
 * @author Evan Pierce 
 * @version 2/16/16
 */
public class PList
{
    protected ArrayList<Passenger>pList;
    protected Clock clock;

    /**
     * Constructor for objects of class PList
     */
    public PList(Clock c)
    {
        clock = c;
        pList = new ArrayList<>();

    }

    /**
     * adds a passenger to the pList
     */
    public void addPassenger(Passenger p)
    {
        //
        pList.add(p);
    }

    /**
     * adds a list of passenger into the pList
     */
    public void addList(ArrayList<Passenger> other)
    {
        //
        for(Passenger p : other) {
            pList.add(p);
            //Passenger.setEntryTick(Clock.getTick()); //working...
        }
    }

    /**
     * removes all with current floor as destination
     */
    public ArrayList<Passenger> removeAtFloor(int floor)
    {
        //
        ArrayList<Passenger> myList = new ArrayList<>();
        Iterator<Passenger> it = pList.iterator();

        while(it.hasNext()) {
            Passenger p = it.next();
            if (p.getDestination() == floor) {
                myList.add(p);
                it.remove();  //sbw fix - took out parameter p
            }
        }

        return myList;
    }

    /**
     * tests display
     */
    public void display()
    {
        //
        for(Passenger p : pList) {
            System.out.println(p);
        }
    }

    /**
     * used for statistics/GUI
     */
    public int size()
    {
        return pList.size();
    }

    /**
     * picks up passengers at current floor and puts them into myList
     */
    public ArrayList<Passenger> pickUpAtFloor(int floor)
    {
        ArrayList<Passenger> myList = new ArrayList<>();
        Iterator<Passenger> it = pList.iterator();

        while(it.hasNext()) {
            Passenger p = it.next();
            if (p.getStart() == floor) {
                myList.add(p);
                it.remove(); 
            }
        }

        return myList;
    }

}
