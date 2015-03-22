package pl.softpartner.tictactoe;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment implements View.OnClickListener, OnGameResultListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PLAYERS_NUMBER = "param1";
    public static String PLAYER_1 = "X";
    public static String PLAYER_2 = "O";
    public static List<TextView> fields = new ArrayList<>();
    public static boolean player_1 = true;
    public static int turnNumber = 1;
    private static List<Integer> fieldsIds = Arrays.asList(R.id.field1, R.id.field2, R.id.field3,
            R.id.field4, R.id.field5, R.id.field6,
            R.id.field7, R.id.field8, R.id.field9);
    // TODO: Rename and change types of parameters
    private static int mPlayersNumber;
    private static OnSymbolSetListener onSymbolSetListener;
    private static boolean endGame = false;
    private TextView resetButton, playerX, playerO;
    private int pointX = 0, pointY = 0;

    public GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param playersNumber Number of players.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(int playersNumber) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putInt(PLAYERS_NUMBER, playersNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static void setSymbol(TextView textView) {
        textView.setText(player_1 ? PLAYER_1 : PLAYER_2);
        player_1 = !player_1;
        onSymbolSetListener.onSymbolSet();
        turnNumber++;
        if (!endGame && !player_1 && mPlayersNumber == 1) {
            Bot.makeTurn();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils utils = new Utils();
        utils.setOnGameResultListener(this);
        onSymbolSetListener = (OnSymbolSetListener) utils;
        if (getArguments() != null) {
            mPlayersNumber = getArguments().getInt(PLAYERS_NUMBER, 1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resetButton = (TextView) view.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        playerX = (TextView) view.findViewById(R.id.playerX);
        playerO = (TextView) view.findViewById(R.id.playerO);
        updatePoints();
        for (int id : fieldsIds) {
            TextView tv = (TextView) view.findViewById(id);
            tv.setOnClickListener(this);
            fields.add(tv);
        }
    }

    private void resetGame() {
        for (TextView textView : fields)
            textView.setText("");
        turnNumber = 1;
        if (!endGame && !player_1 && mPlayersNumber == 1) {
            Bot.makeTurn();
        }
    }

    @Override
    public void onClick(View v) {
        if (endGame == true) {
            endGame = false;
            resetGame();
            return;
        }
        if (v == resetButton) {
            player_1 = true;
            pointX = 0;
            pointY = 0;
            updatePoints();
            resetGame();
        } else if (v instanceof TextView) {
            if (((TextView) v).getText().toString().equals("")) {
                setSymbol((TextView) v);
            }
        }
    }

    private void botTurn() {

    }

    private void updatePoints() {
        playerX.setText(String.format(getString(R.string.playerX), "" + pointX));
        playerO.setText(String.format(getString(R.string.playerO), "" + pointY));
    }

    @Override
    public void onGameEnd(int result) {
        endGame = true;
        if (result == Utils.PLAYER_1_WON) {
            pointX++;
            Toast.makeText(getActivity(), "Player X won!", Toast.LENGTH_SHORT).show();
        }
        if (result == Utils.PLAYER_2_WON) {
            pointY++;
            Toast.makeText(getActivity(), "Player O won!", Toast.LENGTH_SHORT).show();
        }
        if (result == Utils.DRAW)
            Toast.makeText(getActivity(), "Draw!", Toast.LENGTH_SHORT).show();
        updatePoints();
    }
}
