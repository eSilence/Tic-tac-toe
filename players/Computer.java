package players;

import java.util.Random;
import game.Game;
import java.lang.String;


public class Computer {

    private char mark;
    private char opponentMark;
    private char defaultCharMark = Game.getDefaultChar();

    private int step;

    private String [][] path = {{"100000000", "001000000","000000100", "000000001"},
                                {"010000000", "000100000","000001000", "000000010"}};

    public Computer(char mark) {
        this.mark = mark;
        step = 0;
    }

    public char getMark()  {
        return mark;
    }

    public void act(Game game) {
        step++;
        if (isFirstStep(game)) {
            actFirst(game);
        }
        else{
            defineMarkOpponent(game);
            actNext(game);
        }
        game.showFields();
    }

    private void actFirst(Game game){
       switch (random(3)){
           case 0: strToFieldsGame(sceneFirst0(game), game); break;
           case 1: strToFieldsGame(sceneFirst1(game), game); break;
           case 2: strToFieldsGame(sceneFirst2(game), game); break;
       }
    }

    private void strToFieldsGame(String path, Game game){
        for (int i = 0; i < Game.size; i++)
            for (int j = 0; j < Game.size; j++)
                if (path.charAt(i * Game.size + j) != '0'){
                    game.setPos(i, j, mark);
                }
    }

    private String sceneFirst0(Game game){
      return path[0][random(4)];
    }

    private String sceneFirst1(Game game){
        return path[1][random(4)];
    }

    private String sceneFirst2(Game game){
        return "000010000";
    }


    private boolean actNext(Game game){ // алгоритм из https://en.wikipedia.org/wiki/Tic-tac-toe
        if (win(game)) return true;
        if (block(game)) return true;
        if (fork(game))  return true;
        if (blockingOpponentFork(game)) return true;
        if (center(game)) return true;
        if (oppositeCorner(game)) return true;
        if (emptyCorner(game)) return true;
        if (emptySide(game)) return true;
        return false;
    }

    private boolean win(Game game){

        for (int i = 0; i < Game.size; i++){

            if (setMarkLine(i, game, mark, defaultCharMark)) return true;
            if (setMarkColumn(i, game, mark, defaultCharMark)) return true;
        }
        if (setMarkDiagonal(game, mark, defaultCharMark)) return true;

        return false;
    }

    private boolean block(Game game){
        for (int i = 0; i < Game.size; i++){
            if (setMarkLine(i, game, opponentMark, defaultCharMark)) return true;
            if (setMarkColumn(i, game, opponentMark, defaultCharMark)) return true;
        }
        if (setMarkDiagonal(game, opponentMark, defaultCharMark)) return true;

        return false;
    }

    private boolean fork(Game game){
       return forkSolve(game, mark);
    }

    private boolean forkSolve(Game game, char markFind){
        char[][] fieldsTmp = new char[Game.size][Game.size];

        fillArray(fieldsTmp, 0);

        forkFindfillArray(fieldsTmp, game, markFind);

        int[] max;

        max = choosePossibleMove(fieldsTmp, game);

        if(fieldsTmp[max[0]][max[1]] > 1){
            if (max[2] == 1 ||max[2] == 2 ) {
                game.setPos(max[0], max[1], mark);
                return true;
            }
        }
        return false;
    }

    private boolean blockingOpponentFork(Game game){
        return blockingOpponentForkSolve(game, opponentMark);
    }

    private boolean blockingOpponentForkSolve(Game game, char markFind){
        char[][] fieldsTmp = new char[Game.size][Game.size];

        fillArray(fieldsTmp, 0);

        forkFindfillArray(fieldsTmp, game, markFind);

        int[] max;

        max = choosePossibleMove(fieldsTmp, game);

        if(fieldsTmp[max[0]][max[1]] > 1){
            if (max[2] == 1) {
                game.setPos(max[0], max[1], mark);
                return true;
            }
            if (max[2] == 2){
                game.setPos(1, 2, mark);
                return true;
            }
            if (max[2] > 2 && game.getPos(1, 1) == defaultCharMark){
                game.setPos(1 ,1, mark);
                return true;
            }
        }
        return false;
    }

    private boolean center(Game game){
        if (game.getPos(1, 1) == defaultCharMark){
            game.setPos(1, 1, mark);
            return true;
        }
        return false;
    }

    private boolean oppositeCorner(Game game){
        if (step == 2 && game.getPos(1,1) != defaultCharMark){
            if (oppositeCornerMove(game, 0, 0)) return true;
            if (oppositeCornerMove(game, 0 ,2)) return true;
            if (oppositeCornerMove(game, 2 ,0)) return true;
            if (oppositeCornerMove(game, 2 ,2)) return true;
        }
        return false;
    }

    private boolean oppositeCornerMove(Game game, int line, int column){
        if (game.getPos(line, column) != defaultCharMark){
            game.setPos(Game.size - line - 1, Game.size - column - 1, mark);
            return true;
        }
        return false;
    }

       private boolean emptyCorner(Game game){
        if (emptyMove(game, 0, 0)) return true;
        if (emptyMove(game, 0 ,2)) return true;
        if (emptyMove(game, 2 ,0)) return true;
        if (emptyMove(game, 2 ,2)) return true;
        return false;
    }

    private boolean emptySide(Game game){
        if (emptyMove(game, 0, 1)) return true;
        if (emptyMove(game, 1 ,0)) return true;
        if (emptyMove(game, 1 ,2)) return true;
        if (emptyMove(game, 2 ,1)) return true;
        return false;
    }

    private boolean emptyMove(Game game, int line, int column){
        if (game.getPos(line, column) == defaultCharMark){
            game.setPos(line , column, mark);
            return true;
        }
        return false;
    }

    private void forkFindfillArray(char[][] fieldsTmp, Game game, char markFind){
        for (int i = 0; i < Game.size; i++){
            for (int j = 0; j < Game.size; j++) {
                if (game.getPos(i, j) == markFind) {
                    sumLineColumn(fieldsTmp, game, i, j, markFind);
                    if (i == j){
                        sumMainDiagonal(fieldsTmp, game, i, j, markFind);
                    }
                    if (i == Game.size - i - 1)   {
                        sumSecondaryDiagonal(fieldsTmp, game, i, j, markFind) ;
                    }
                }
            }
        }
    }

    private int[] choosePossibleMove(char [][] fieldsTmp, Game game) {
        int maxLine = 0, maxColumn = 0, countMax = 1;

        for (int i = 0; i < Game.size; i++){
            for (int j = 0; j < Game.size; j++) {
                if (fieldsTmp[i][j] > fieldsTmp[maxLine][maxColumn]){
                    maxLine = i;
                    maxColumn = j;
                    countMax = 1;
                }
                else if (fieldsTmp[i][j] == fieldsTmp[maxLine][maxColumn]){
                    countMax++;
                }
            }
        }

        int [] max = new int[3];
        max[0] = maxLine;
        max[1] = maxColumn;
        max[2] = countMax;

        return max;   // вернуть 3 значения
    }

    private void sumLineColumn(char [][] fieldsTmp, Game game, int line, int column,char markFind){
        if (checkLine( line, game, defaultCharMark, markFind) == column){
            for (int i = 0; i < Game.size; i++){
                if (i != column){
                    fieldsTmp[line][i] += 1;
                }
            }
        }
        if (checkColumn( column, game, defaultCharMark, markFind) == line){
            for (int i = 0; i < Game.size; i++){
                if (i != line) {
                    fieldsTmp[i][column] += 1;
                }
            }
        }
    }
    private void sumMainDiagonal(char [][] fieldsTmp, Game game, int line, int column, char markFind){
        if (checkMainDiagonal(game, defaultCharMark, markFind) == line ){
            for (int i = 0; i < Game.size; i++){
                if (i != line){
                    fieldsTmp[i][i] += 1;
                }
            }
        }
    }

    private void sumSecondaryDiagonal(char [][] fieldsTmp, Game game, int line, int column, char markFind){
        if (checkSecondaryDiagonal(game, defaultCharMark, markFind) == line ){
            for (int i = 0; i < Game.size; i++){
                if (i != line){
                    fieldsTmp[i][Game.size - i - 1] += 1;
                }
            }
        }
    }

    private boolean setMarkLine(int line, Game game, char occupied, char vacant){
        int column = checkLine(line, game, occupied, vacant);
        if ( column != -1) {
            game.setPos(line, column, mark);
            return true;
        }
        return false;
    }

    private boolean setMarkColumn(int column, Game game, char occupied, char vacant){
        int line = checkColumn(column, game, occupied, vacant);
        if ( line != -1) {
            game.setPos(line, column, mark);
            return true;
        }
        return false;
    }

    private boolean setMarkDiagonal(Game game, char occupied, char vacant){
        int line = checkMainDiagonal(game, occupied, vacant);
        if ( line != -1) {
            game.setPos(line, line, mark);
            return true;
        }
        line = checkSecondaryDiagonal(game, occupied, vacant);
        if ( line != -1) {
            game.setPos(line, Game.size - line -1, mark);
            return true;
        }
        return false;
    }

    private int checkLine(int line, Game game, char occupied, char vacant){
        if (game.getPos(line, 0) == vacant && game.getPos(line, 1) == occupied && game.getPos(line, 2) == occupied ){
            return 0;
        }
        if (game.getPos(line, 1) == vacant && game.getPos(line, 0) == occupied && game.getPos(line, 2) == occupied ){
            return 1;
        }
        if (game.getPos(line, 2) == vacant  && game.getPos(line, 0) == occupied && game.getPos(line, 1) == occupied ){
            return 2;
        }
        return -1;
    }

    private int checkColumn(int column, Game game, char occupied, char vacant){
        if (game.getPos(0, column) == vacant && game.getPos(1, column) == occupied && game.getPos(2, column) == occupied ){
            return 0;
        }
        if (game.getPos(1, column) == vacant && game.getPos(0, column) == occupied && game.getPos(2, column) == occupied ){
            return 1;
        }
        if (game.getPos(2, column) == vacant && game.getPos(0, column) == occupied && game.getPos(1, column) == occupied ){
            return 2;
        }
        return -1;
    }

    private int checkMainDiagonal( Game game, char occupied, char vacant){
        if (game.getPos(0, 0) == vacant && game.getPos(1, 1) == occupied && game.getPos(2, 2) == occupied ){
            return 0;
        }
        if (game.getPos(1, 1) == vacant && game.getPos(0, 0) == occupied && game.getPos(2, 2) == occupied ){
            return 1;
        }
        if (game.getPos(2, 2) == vacant  && game.getPos(0, 0) == occupied && game.getPos(1, 1) == occupied ){
            return 2;
        }
        return -1;
    }

    private int checkSecondaryDiagonal( Game game, char occupied, char vacant){
        if (game.getPos(0, 2) == vacant  && game.getPos(1, 1) == occupied && game.getPos(2, 0) == occupied ){
            return 0;
        }
        if (game.getPos(1, 1) == vacant && game.getPos(0, 2) == occupied && game.getPos(2, 0) == occupied ){
            return 1;
        }
        if (game.getPos(2, 0) == vacant && game.getPos(0, 2) == occupied && game.getPos(1, 1) == occupied ){
            return 2;
        }
        return -1;
    }

    public boolean isFirstStep(Game game){
        return game.isClear();
    }

    private void defineMarkOpponent(Game game){
        for (int i = 0; i < Game.size; i++)
            for (int j = 0; j < Game.size; j++)
                if (!(game.checkAct(i, j))){
                    if (game.getPos(i, j) != mark)
                        opponentMark = game.getPos(i, j);
                }
    }

    private static int random(int n) {
        Random random = new Random();
        return Math.abs(random.nextInt(n));
    }

    private void fillArray( char[][] array , int value){
        for (int i = 0; i < Game.size; i++){
            for (int j = 0; j < Game.size; j++) {
                array[i][j] = 0;
            }
        }
     }
}

