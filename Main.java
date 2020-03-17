public class Main
{
    private static Frame frame;
    private static Core core;

    public static void main(String [] args)
    {
        frame = new Frame();
        refresh();
    }

    public static void refresh()
    {
        core = new Core();
        core.addFrame(frame);
        frame.addCore(core);
    }
}
