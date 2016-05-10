/**
 * Creates the initial objects.
 * Accesses the "Master List(s)" of all the Passengers.
 * Runs the main simulator loop and a tickCount(i.e. time).
 * It invokes the act() methods of the main object(ECar and PassengerSource) at each iteration.
 * 
 * @author
 * @February 23, 2016.
 */
public class Controller
{
    private UpList upList;
    private DownList downList;
    private SinkList sinkList;
    private InCarList incarList;
    private int maxFloor;
    private PassengerSource source;
    private Bank bank;
    private int tick;
    private Clock myClock;
    private Statistics myReport; //Connor
    //private GUI testGUI;

    /**
     * Constructor for objects of class Controller.
     */
    public Controller(int maxFloor, int numRun, int numOfECars) throws invalidFloorException //CK- Changed maxCount to numRunand made parameter
    {
        if(maxFloor < 2)
            throw new invalidFloorException();
        else
            this.maxFloor = maxFloor;

        myClock = new Clock();
        upList = new UpList(myClock);    //sbw added parameters
        downList = new DownList(myClock);
        sinkList = new SinkList(myClock);
        incarList = new InCarList(myClock);  //...sbw
        myReport = new Statistics(sinkList, incarList); //Connor - no upList and downList

        source = new PassengerSource(upList, downList, maxFloor, myClock);

        //testGUI = new GUI(maxFloor);
        //car = new ECar(upList, downList, sinkList, incarList, maxFloor, myClock, testGUI);
        bank = new Bank(numOfECars, maxFloor, upList, downList, sinkList, myClock); 

        run(numRun);
    }

    /**
     * Contains the simulation loop to invoke the act method on the objects.
     */
    public void run(int numRun)
    {
        tick = 0;

        while (tick < numRun)
        {
            source.act();
            bank.act(tick);
            tick++;

            myClock.incrementTick();

            try{
                Thread.sleep(500);
            } catch (Exception e){Thread.currentThread().interrupt();};

        }
        myReport.fullReport(); //Connor
    }

    /**
     * Displays each wait time for passengers
     */
    public void showAllWaitTimes() //CKnote - Optional view of wait times after Controller runs
    {
        myReport.listWaitTimes();
    }

    /**
     * Displays floors requested by passengers
     */
    public void showDesiredFloors() //CKnote - Floors traveled requested as
    {
        myReport.desirableFloors();
    }

    /**
     * Displays passengers that have reached destination
     */ 
    public void ControlltestID()        //Checks passengers in sinklist after run
    {
        myReport.testID();
    }

    /**
     * Displays passengers stuck in Elevator
     */
    public void leftInCar()             //Checks passengers in inCarList after run
    {
        myReport.inCarRemaining();
    }

}
