import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame
{
    private FieldPanel fields;
    private Core core;

    public Frame()
    {
        super("snake");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        fields = new FieldPanel();
        add(fields);
        addKeyListener(new DirectionChanger());

        pack();
    }

    public void addCore(Core core)
    {
        this.core = core;
    }

    public void display(Field [][] f)
    {
        fields.display(f);
    }

    private class DirectionChanger implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e)
        {

        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            core.changeDirection(e.getKeyChar());
        }

        @Override
        public void keyReleased(KeyEvent e)
        {

        }
    }

}
