public class BrokenTile extends Tile
{
    private int lifeTime;

    public BrokenTile()
    {
        lifeTime = 20;
    }

    public void loseLife()
    {
        lifeTime--;
        if(lifeTime == 0)
        {
            Main.printer.functionCall("bt", "unlink");
            unlink();
            Main.printer.returnFromFunctionCall();

            Main.printer.functionCall(contains.name, "kill"); //What is name??
            ((Animal) contains).kill();
            Main.printer.returnFromFunctionCall();
        }
    }
}
