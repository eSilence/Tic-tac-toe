package Players;

import Game.Game;

/**
 * Created with IntelliJ IDEA.
 * User: elena
 * Date: 19.07.13
 * Time: 19:54
 * To change this template use File | Settings | File Templates.
 */
public class Compute {
    private char znak;

    public void SetZnak(char znak)
    {
        this.znak = znak;
    }
    public char GetZnak()
    {
        return znak;
    }
    public void Act(Game game)
    {
        int line= 0, column = 0;
        boolean result;
    }

}
