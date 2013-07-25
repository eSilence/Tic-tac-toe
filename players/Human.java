package players;

import java.io.IOException;
import game.Game;

public class Human {

    private char mark;

    public Human(char mark) {
        this.mark = mark;
    }

    public char getMark() {
        return mark;
    }

    public void act(Game game) {
        int line= 0, column = 0;

        while(true)
        {
            System.out.println("Ваш ход: (введите строку) ");
            line = getAnswer();
            System.out.println("Ваш ход: (введите столбец) ");
            column = getAnswer();

            if (checkAnswer(line, column, game)) {
                break;
            }
            else{
                System.out.println("Ошибка ввода! Повторите еще раз.");
            }
        }

        game.setPos(line, column, mark);
        game.showFields();
    }

    int getAnswer(){
        int answer;
        try {
            answer = (int) System.in.read() - '0' ;
            System.in.skip(1);
        } catch  (IOException e) {
            System.out.println("Input ERROR");
            return 0;
        }
        return answer;
    }

    boolean checkAnswer(int line, int column, Game game){
        boolean result;

        System.out.println(line + ", "+ column);

        result = (line >= 0 && line < Game.size && column >= 0 && column < Game.size);

        if(result) {
            result = result && (game.checkAct(line, column)) ;
        }
        return result;

     }

}
