import game.Game;
import players.*;
public class Main {

    public static void main(String[] args) {      // игра до победы
        int countGame = 1;

        while(true){
            Game game = new Game(3);
            game.showFields();

      //    Human men1 = new Human('x');
      //    Human men2 = new Human('0');

            Computer men1 = new Computer('x');
            Computer men2 = new Computer('O');

            char result;
            while (true)
            {
                men1.act(game);
                result =  men1.getMark();
                if (game.checkWin(result)) break;
                if (!game.checkNone()){
                    result = 'N';
                    break;
                }
                men2.act(game);
                result =  men2.getMark();
                if (game.checkWin(result)) break;
            }
            if (result == 'N')
                System.out.println("ничья!");
            else  {
                System.out.println("Победа: " + result + "!");
                break;
            }
            System.out.println("------------------------------------ "+ countGame++);
        }
    }
}
