public class Field
{
    private int xCord;
    private int yCord;

    public static final int EMPTY = 0;
    public static final int FOOD = 1;
    public static final int BODY = 2;
    public static final int FEDBODY = 3;
    public static final int HEAD = 4;
    public static final int TAIL = 5;
    public static final int EATING = 6;

    private boolean [] properties = {true, false, false, false, false, false, false};

    public Field(int x, int y)
    {
        xCord = x;
        yCord = y;
    }

    public int getxCord()
    {
        return xCord;
    }

    public int getyCord()
    {
        return yCord;
    }

    public int getProperty()
    {
        for (int i=0; i<properties.length; i++)
        {
            if (properties[i])
            {
                return i;
            }
        }
        return 0;
    }

    public void setProperty(int name)
    {
        for (int i=0; i<properties.length; i++)
        {
            properties[i] = false;
        }
        properties[name] = true;
    }
}
