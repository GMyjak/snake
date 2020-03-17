
import javax.swing.*;
import java.awt.*;


public class FieldPanel extends JPanel
{
    private JButton [][] array;

    public FieldPanel()
    {
        super();
        setPreferredSize(new Dimension(700, 500));
        setLayout(new GridLayout(25, 35));

        array = new JButton[25][];
        for (int i=0; i<array.length; i++)
        {
            array[i] = new JButton[35];
            for (int j=0; j<array[i].length; j++)
            {
                array[i][j] = new JButton();
                array[i][j].setFocusable(false);
                array[i][j].setEnabled(false);
                add(array[i][j]);
            }
        }
    }

    public void display(Field [][] f)
    {
        for (int i=0; i<f.length; i++)
        {
            for (int j=0; j<f[i].length; j++)
            {
                switch (f[i][j].getProperty())
                {
                    case Field.EMPTY:
                        array[i][j].setBackground(Color.white);
                        break;
                    case Field.FOOD:
                        array[i][j].setBackground(Color.yellow);
                        break;
                    case Field.BODY:
                        array[i][j].setBackground(Color.black);
                        break;
                    case Field.FEDBODY:
                        array[i][j].setBackground(Color.red);
                        break;
                    case Field.HEAD:
                        array[i][j].setBackground(Color.gray);
                        break;
                    case Field.TAIL:
                        array[i][j].setBackground(Color.green);
                        break;
                    case Field.EATING:
                        array[i][j].setBackground(Color.blue);
                        break;

                }
            }
        }
    }
}
