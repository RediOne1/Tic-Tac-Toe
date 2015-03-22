package pl.softpartner.tictactoe;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Adrian on 2015-03-22.
 */
public class Bot {

    private static int lastPosition;

    public static void makeTurn() {
        switch (GameFragment.turnNumber) {
            case 1:
                setRandomEmptyCorner();
                break;
            case 2:
                if (getSymbol(4).equals(""))
                    setSymbol(4);
                else
                    setFirstEmptyField();
                break;
            case 3:
                if (getSymbol(4).equals(""))
                    setRandomEmptyCorner();
                else
                    switch (lastPosition) {
                        case 0:
                            setSymbol(8);
                            break;
                        case 2:
                            setSymbol(6);
                            break;
                        case 6:
                            setSymbol(2);
                            break;
                        case 8:
                            setSymbol(0);
                            break;
                    }
                break;
            default:
                blockOrFinish();
        }
    }

    private static void setRandomEmptyCorner() {
        List<Integer> corners = new ArrayList<>();
        if (getSymbol(0).equals(""))
            corners.add(0);
        if (getSymbol(2).equals(""))
            corners.add(2);
        if (getSymbol(6).equals(""))
            corners.add(6);
        if (getSymbol(8).equals(""))
            corners.add(8);

        if (corners.size() > 0) {
            int position = corners.get(new Random().nextInt(corners.size()));
            setSymbol(position);
        } else
            setFirstEmptyField();
    }

    private static void setFirstEmptyField() {
        for (TextView textView : GameFragment.fields) {
            if (textView.getText().toString().equals("")) {
                GameFragment.setSymbol(textView);
                lastPosition = GameFragment.fields.indexOf(textView);
                break;
            }
        }
    }

    private static void blockOrFinish() {
        if (!tryBlockFinish(0, 1, 2) &&
                !tryBlockFinish(3, 4, 5) &&
                !tryBlockFinish(6, 7, 8) &&
                !tryBlockFinish(0, 3, 6) &&
                !tryBlockFinish(1, 4, 7) &&
                !tryBlockFinish(2, 5, 8) &&
                !tryBlockFinish(0, 4, 8) &&
                !tryBlockFinish(2, 4, 6)
                ) {
            setFirstEmptyField();
        }
    }

    private static boolean tryBlockFinish(int position1, int position2, int position3) {
        List<String> positions = new LinkedList<String>(Arrays.asList("" + position1, "" + position2, "" + position3));
        int emptyPositionsCount = 0;
        if (getSymbol(position1).equals("")) {
            emptyPositionsCount++;
            positions.remove("" + position1);
        }
        if (getSymbol(position2).equals("")) {
            emptyPositionsCount++;
            positions.remove("" + position2);
        }
        if (getSymbol(position3).equals("")) {
            emptyPositionsCount++;
            positions.remove("" + position3);
        }

        if (emptyPositionsCount != 1)
            return false;
        if (getSymbol(Integer.parseInt(positions.get(0))).equals(getSymbol(Integer.parseInt(positions.get(1))))) {
            setSymbol(emptyPositionsCount);
        }
        return true;
    }

    private static String getSymbol(int position) {
        return GameFragment.fields.get(position).getText().toString();
    }

    private static void setSymbol(int position) {
        lastPosition = position;
        GameFragment.setSymbol(GameFragment.fields.get(position));
    }
}
