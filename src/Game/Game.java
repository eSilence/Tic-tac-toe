package Game;

/**
 * Created with IntelliJ IDEA.
 * User: elena
 * Date: 19.07.13
 * Time: 19:55
 * To change this template use File | Settings | File Templates.
 */
public class Game {
    public static final int SIZE = 3;
    private final char DEFAULT_CHAR = ' ';
    private  char[][] fields = new char[SIZE][SIZE];

    public void Init()
    {
        for (int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                fields[i][j] = DEFAULT_CHAR;
    }
    public boolean CheckWin(char ch)
    {
        boolean result;
        for (int i = 0; i < SIZE; i++){
              result = CheckLineWin(i, ch);
              if (result) return result;
              result = CheckColWin(i, ch);
              if (result) return result;
        }
              result = CheckDiagWin(ch);
              return result;
     }

    private boolean CheckLineWin(int line, char ch)
    {
        return (fields[line][0] == fields[line][1] && fields[line][1] == fields[line][2] && fields[line][2] == ch);
    }
    private boolean CheckColWin(int col, char ch)
    {
        return (fields[0][col] == fields[1][col] && fields[1][col] == fields[2][col] && fields[2][col] == ch);
    }

    private boolean CheckDiagWin(char ch)
    {
        return ((fields[0][0] == fields[1][1] && fields[1][1] == fields[2][2] && fields[2][2] == ch) ||
                (fields[0][2] == fields[1][1] && fields[1][1] == fields[2][0] && fields[2][0] == ch));
    }

    public boolean CheckNone()
    {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (CheckAct(i,j)) return true;
        return false;
    }

    public void SetPos(int line, int column, char ch)
    {
        fields[line][column] = ch;
    }
    public boolean CheckAct(int line, int column)
    {
        return fields[line][column]== DEFAULT_CHAR;
    }
    public void ShowFields()
    {
        for (int i = 0; i < SIZE; i++)  {
            ShowLineFields(i);
            System.out.println();
        }
    }
    private void ShowLineFields( int numLine)
    {
        for (int i = 0; i < SIZE; i++)
            ShowCell(numLine, i);
    }
    private void ShowCell(int line, int column)
    {
        System.out.print("["+fields[line][column]+"]");

    }
}

