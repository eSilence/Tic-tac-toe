package players;

import game.Game;

public class Computer {

    private char mark;

    public Computer(char mark) {
        this.mark = mark;
    }

    public char getMark()  {
        return mark;
    }

    public void act(Game game) {
        int line= 0, column = 0;
        boolean result;
    }

}
