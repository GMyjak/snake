import java.util.*;

public class Clock implements Runnable
{
    private int timer;
    private int milis;
    private Thread t;
    private LinkedList<Observer> observerList;

    public Clock(int milis)
    {
        this.milis = milis;
        timer = 0;
        observerList = new LinkedList<>();
    }

    @Override
    public void run()
    {
        while (true)
        {
            timer++;
            notifyObservers();
            try
            {
                Thread.sleep(milis);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void start()
    {
        if (t == null)
        {
            t = new Thread (this);
            t.start();
        }
    }

    public int getTimer()
    {
        return timer;
    }

    public void register (Observer o)
    {
        observerList.add(o);
    }

    public void deregister(Observer o)
    {
        observerList.remove(o);
    }

    private void notifyObservers()
    {
        Iterator<Observer> it = observerList.iterator();
        while (it.hasNext())
        {
            it.next().update();
        }
    }
}
