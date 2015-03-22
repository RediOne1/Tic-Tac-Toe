package pl.softpartner.tictactoe;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class StartFragment extends Fragment implements View.OnClickListener {

    Spinner players_number;
    private TextView start;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        players_number = (Spinner) view.findViewById(R.id.player_number);
        start = (TextView) view.findViewById(R.id.start);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == start) {
            int players = Integer.parseInt(players_number.getSelectedItem().toString());
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, GameFragment.newInstance(players))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
