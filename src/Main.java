import Game.*;
import Players.Human;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.Init();
        game.ShowFields();
        Human men1 = new Human();
        men1.SetZnak('x');
        Human men2 = new Human();
        men2.SetZnak('0');
        char result;
        while (true)
        {
            men1.Act(game);
            result =  men1.GetZnak();
            if (game.CheckWin(result)) break;
            if (!game.CheckNone()){
                result = '0';
                break;
            }
            men2.Act(game);
            result =  men2.GetZnak();
            if (game.CheckWin(result)) break;
        }
        if (result == '0')
            System.out.println("ничья!");
        else
            System.out.println("Победа: " + result + "!");
    }
}
