package game;

public class Game {

    public static int size;

    private static final char DEFAULT_CHAR = ' ';

    private static char[][] fields;

    public Game(int size) {
        this.size = size;
        fields = new char [size][size];
        init();
    }

    public void init()  {
        for (int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                fields[i][j] = DEFAULT_CHAR;
    }

    public boolean checkWin(char ch) {
        boolean result;

        for (int i = 0; i < size; i++){
            result = checkLineWin(i, ch);
            if (result) return result;
            result = checkColumnWin(i, ch);
            if (result) return result;
        }
        result = checkDiagWin(ch);
        return result;
    }

    private boolean checkLineWin(int line, char ch) {
        boolean result = true;

        for ( int i = 0; i < size && result; i++){
            result = (result && fields[line][i] == ch);
        }
        return result;
    }

    private boolean checkColumnWin(int col, char ch) {
        boolean result = true;

        for ( int i = 0; i < size && result; i++){
            result = (result && fields[i][col] == ch);
        }
        return result;
    }

    private boolean checkDiagWin(char ch) {
        boolean result = true;

        for ( int i = 0; i < size && result; i++){
            result = (result && fields[i][i] == ch);
        }
        if (result){
            return result;
        }

        result = true;

        for ( int i = 0; i < size && result; i++){
            result = (result && fields[i][size - i - 1] == ch);
        }
        return result;


    }

    public boolean checkNone()  {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (checkAct(i, j)) return true;
        return false;
    }

    public void setPos(int line, int column, char ch) {
        fields[line][column] = ch;
    }

    public boolean checkAct(int line, int column) {
        return fields[line][column]== DEFAULT_CHAR;
    }

    public void showFields() {
        for (int i = 0; i < size; i++)  {
            showLineFields(i);
            System.out.println();
        }
    }

    private void showLineFields(int numLine) {
        for (int i = 0; i < size; i++)
            showCell(numLine, i);
    }

    private void showCell(int line, int column) {
        System.out.print("["+fields[line][column]+"]");

    }

}

