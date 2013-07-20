package Players;
// INFO: this is ONLY fast comments
// don't use uppercase in package name! (players, not Players)
// many things is in C# style;)
import java.io.IOException;
// after rrefactoring packages import must be game.Game 
import Game.Game;

// try don't use default comments. It's hard, i know=)
/**
 * Created with IntelliJ IDEA.
 * User: elena
 * Date: 19.07.13
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class Human {
    // new line here
    private char znak;

    public void SetZnak(char znak) { // symbol '{' must be on the same line with method name (don't use C# or C++ style in Java)
        this.znak = znak;
    }
    // new line after methods
    public char GetZnak() { // methods name must start wit lower case char: getZnak (NOT: GetZnak)
        return znak;
    }
    // too many lines in method, need to be refactored
    public void Act(Game game) {
        int line= 0, column = 0;
        boolean result;

        while(true)
        {
            System.out.println("Ваш ход: (введите строку) ");
            try {
                line = (int) System.in.read() - '0' ;
                System.in.skip(1);
            } catch  (IOException e) {
                System.out.println("Input ERROR");
            }
            System.out.println("Ваш ход: (введите столбец) ");
            try {
                column = (int) System.in.read() - '0';
                System.in.skip(1);
            } catch  (IOException e) {
                System.out.println("Input ERROR");
            }
            System.out.println(line + ", "+ column);

            result = (line >= 0 && line < Game.SIZE && column >= 0 && column < Game.SIZE);
            if(result) {
                result = result && (game.CheckAct(line, column)) ;
            }

            if (!result) {
                System.out.println("Ошибка ввода! Повторите еще раз.");
            }
            else
                break;
        }

        game.SetPos(line, column, znak);
        game.ShowFields();
    }


}
