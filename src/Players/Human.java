package Players;
import java.io.IOException;
import Game.Game;


/**
 * Created with IntelliJ IDEA.
 * User: elena
 * Date: 19.07.13
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class Human {
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
            if(result)
                result = result && (game.CheckAct(line, column)) ;

            if (!result)
            {
                System.out.println("Ошибка ввода! Повторите еще раз.");
            }
            else
                break;
        }

        game.SetPos(line, column, znak);
        game.ShowFields();
    }


}
