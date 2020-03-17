import java.util.Random;

public class Core implements Observer
{
    private Field [][] array;
    private TwoWayLinkedList<Field> snake;
    private int direction;
    private Frame gui;

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    private int lastDir;
    private int score;
    private Clock clk;

    public Core()
    {
        array = new Field[25][];
        for (int i=0; i<array.length; i++)
        {
            array[i] = new Field[35];
            for (int j=0; j<array[i].length; j++)
            {
                array[i][j] = new Field(i,j);
            }
        }

        direction = RIGHT;
        score = 0;

        snake = new TwoWayLinkedList<>();

        snake.addTail(array[12][18]);
        snake.addTail(array[12][17]);
        snake.addTail(array[12][16]);
        snake.getHead().setProperty(Field.HEAD);
        snake.getTail().setProperty(Field.TAIL);

        addFood();


        clk = new Clock(100);
        clk.register(this);
        clk.start();
    }

    public void move()
    {
        Field temp;
        if (direction == 0)
        {
            temp = array[(snake.getHead().getxCord()+array.length-1)%array.length][snake.getHead().getyCord()];
        }
        else if (direction == 1)
        {
            temp = array[(snake.getHead().getxCord()+1)%array.length][snake.getHead().getyCord()];
        }
        else if (direction == 2)
        {
            temp = array[snake.getHead().getxCord()][(snake.getHead().getyCord()+array[0].length-1)%array[0].length];
        }
        else
        {
            temp = array[snake.getHead().getxCord()][(snake.getHead().getyCord()+1)%array[0].length];
        }
        if ((temp.getProperty() == Field.BODY) || (temp.getProperty() == Field.FEDBODY))
        {
            clk.deregister(this);
            try
            {
                Thread.sleep(3000);
                Main.refresh();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            snake.addHead(temp);
            //snake.getTail().setProperty(Field.EMPTY);
            modifySnake(snake.cutTail());
        }

    }

    private void modifySnake(Field tail)
    {
        for (Field f : snake)
        {
            if (f.getProperty() == Field.EATING)
            {
                f.setProperty(Field.FEDBODY);
            }
            else if (f.getProperty() == Field.FOOD)
            {
                f.setProperty(Field.EATING);
                addFood();
                score ++;
            }
            else if (f.getProperty() == Field.FEDBODY)
            {

            }
            else
            {
                f.setProperty(Field.BODY);
            }
        }

        if (snake.getTail().getProperty() == Field.FEDBODY)
        {
            snake.getTail().setProperty(Field.BODY);
            snake.addTail(tail);
            snake.getTail().setProperty(Field.TAIL);
        }
        else
        {
            tail.setProperty(Field.EMPTY);
        }

        if (snake.getHead().getProperty() != Field.EATING)
        {
            snake.getHead().setProperty(Field.HEAD);
        }
        snake.getTail().setProperty(Field.TAIL);

        System.out.println(score);
    }

    //finished
    private void addFood()
    {
        Random rand = new Random();
        Field temp = array[rand.nextInt(25)][rand.nextInt(35)];
        if (temp.getProperty() == Field.EMPTY)
        {
            temp.setProperty(Field.FOOD);
        }
        else
        {
            addFood();
        }
    }

    //finished
    public void addFrame(Frame frame)
    {
        gui = frame;
    }

    //finished
    public void changeDirection(char c)
    {
        int temp;
        switch ((int)c)
        {
            case (int)'w':
                temp = 0;
                if (lastDir != 1) direction = temp;
                break;
            case (int)'s':
                temp = 1;
                if (lastDir != 0) direction = temp;
                break;
            case (int)'a':
                temp = 2;
                if (lastDir != 3) direction = temp;
                break;
            case (int)'d':
                temp = 3;
                if (lastDir != 2) direction = temp;
                break;
        }
    }

    @Override
    public void update()
    {
        move();
        gui.display(array);
        lastDir = direction;
    }
}
