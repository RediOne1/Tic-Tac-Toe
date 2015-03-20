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
public class GameFragment extends Fragment implements View.OnClickListener {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String PLAYERS_NUMBER = "param1";

	// TODO: Rename and change types of parameters
	private int mPlayersNumber;

	private List<Integer> fieldsIds = Arrays.asList(R.id.field1, R.id.field2, R.id.field3,
		                                                R.id.field4, R.id.field5, R.id.field6,
		                                                R.id.field7, R.id.field8, R.id.field9);
	private List<TextView> fields = new ArrayList<TextView>();

	private boolean cross = true;

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mPlayersNumber = getArguments().getInt(PLAYERS_NUMBER);
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
		if (fields.size() != fieldsIds.size())
			for (int id : fieldsIds) {
				TextView textView = (TextView) view.findViewById(id);
				textView.setOnClickListener(this);
				fields.add(textView);
			}
	}

	private void setField(TextView textView) {
		if (cross)
			textView.setText("X");
		else
			textView.setText("O");
		cross = !cross;
	}

	@Override
	public void onClick(View v) {
		if (fieldsIds.contains(v.getId())) {
			Toast.makeText(getActivity(), "" + fields.indexOf(v), Toast.LENGTH_SHORT).show();
			setField((TextView) v);
		}
	}
}
