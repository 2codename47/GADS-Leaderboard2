package com.aad.cn47.gadsleaderboard2.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aad.cn47.gadsleaderboard2.R;
import com.aad.cn47.gadsleaderboard2.models.Hour;
import com.aad.cn47.gadsleaderboard2.services.ServiceBuilder;
import com.aad.cn47.gadsleaderboard2.services.HourService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaceholderFragmentHour#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceholderFragmentHour extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private HourRecyclerAdapter mHourRecyclerAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mHourLayoutManager;
    //private TextView mTextValue;

    public PlaceholderFragmentHour() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * //@param param1 Parameter 1.
     * //@param param2 Parameter 2.
     * @return A new instance of fragment PlaceholderFragmentHour.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceholderFragmentHour newInstance(int index) {
        PlaceholderFragmentHour fragment = new PlaceholderFragmentHour();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_placeholder_hour, container, false);
        /*mTextValue = root.findViewById(R.id.text_value);
        mTextValue.setText("Hours");*/

        mRecyclerView = root.findViewById(R.id.list_items_hour);

        mHourLayoutManager = new LinearLayoutManager(null);

        mRecyclerView.setLayoutManager(mHourLayoutManager);

        HourService hourServiceObject = ServiceBuilder.buildService(HourService.class);
        Call<List<Hour>> hoursRequest = hourServiceObject.getHours();

        hoursRequest.enqueue(new Callback<List<Hour>>() {
            @Override
            public void onResponse(Call<List<Hour>> call, Response<List<Hour>> response) {
                if(response.isSuccessful()){
                    mRecyclerView.setAdapter(new HourRecyclerAdapter(response.body()));
                    Log.d("HourResponse", response.toString());
                }
                else if(response.code() == 401){
                    //Toast.makeText(context, "Your session has expired !!!", Toast.LENGTH_LONG).show();
                }
                else{
                    //Toast.makeText(context, "Failed to retrieve ideas !!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Hour>> call, Throwable t) {
                if(t instanceof IOException){
                    //Toast.makeText(context, "A connection error occurred !!!", Toast.LENGTH_LONG).show();
                }
                else{
                    //Toast.makeText(context, "Failed to retrieve ideas !!!", Toast.LENGTH_LONG).show();
                    Log.d("HourError", t.toString());
                }

            }
        });

        return root;
    }

    public class HourRecyclerAdapter
            extends RecyclerView.Adapter<HourRecyclerAdapter.ViewHolder> {

        private final List<Hour> mValues;

        public HourRecyclerAdapter(List<Hour> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_hours_list, parent, false);
            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mTextName.setText(mValues.get(position).getName());
            holder.mTextScoreCountry.setText(mValues.get(position).getHours() + " learning hours, " + mValues.get(position).getCountry());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putInt(IdeaDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
                        IdeaDetailFragment fragment = new IdeaDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.idea_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, IdeaDetailActivity.class);
                        intent.putExtra(IdeaDetailFragment.ARG_ITEM_ID, holder.mItem.getId());

                        context.startActivity(intent);
                    }*/
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mTextName;
            public final TextView mTextScoreCountry;
            public Hour mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mTextName = (TextView) view.findViewById(R.id.text_name);
                mTextScoreCountry = (TextView) view.findViewById(R.id.text_score_country);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextScoreCountry.getText() + "'";
            }
        }
    }
}
