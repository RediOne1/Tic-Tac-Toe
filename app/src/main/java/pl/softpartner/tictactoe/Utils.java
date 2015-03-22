package pl.softpartner.tictactoe;

/**
 * Created by Adrian on 2015-03-20.
 */
public class Utils implements OnSymbolSetListener {

    public static int PLAYER_1_WON = -1;
    public static int PLAYER_2_WON = 1;
    public static int DRAW = 0;
    private OnGameResultListener onGameResultListener;

    public void setOnGameResultListener(Object o) {
        onGameResultListener = (OnGameResultListener) o;
    }

    @Override
    public void onSymbolSet() {
        checkGame();
    }

    private void checkGame() {
        if (getSymbol(0).equals(getSymbol(1)) && getSymbol(1).equals(getSymbol(2)) && !getSymbol(0).equals("")
                || getSymbol(3).equals(getSymbol(4)) && getSymbol(4).equals(getSymbol(5)) && !getSymbol(3).equals("")
                || getSymbol(6).equals(getSymbol(7)) && getSymbol(7).equals(getSymbol(8)) && !getSymbol(6).equals("")
                || getSymbol(0).equals(getSymbol(3)) && getSymbol(3).equals(getSymbol(6)) && !getSymbol(0).equals("")
                || getSymbol(1).equals(getSymbol(4)) && getSymbol(4).equals(getSymbol(7)) && !getSymbol(1).equals("")
                || getSymbol(2).equals(getSymbol(5)) && getSymbol(5).equals(getSymbol(8)) && !getSymbol(2).equals("")
                || getSymbol(0).equals(getSymbol(4)) && getSymbol(4).equals(getSymbol(8)) && !getSymbol(0).equals("")
                || getSymbol(2).equals(getSymbol(4)) && getSymbol(4).equals(getSymbol(6)) && !getSymbol(2).equals("")) {
            if (!GameFragment.player_1)
                onGameResultListener.onGameEnd(PLAYER_1_WON);
            else
                onGameResultListener.onGameEnd(PLAYER_2_WON);
            return;
        }
        int n = GameFragment.fields.size();
        for (int i = 0; i < n; i++)
            if (getSymbol(i).equalsIgnoreCase(""))
                return;
        onGameResultListener.onGameEnd(DRAW);
    }

    private String getSymbol(int position) {
        return GameFragment.fields.get(position).getText().toString();
    }
}
