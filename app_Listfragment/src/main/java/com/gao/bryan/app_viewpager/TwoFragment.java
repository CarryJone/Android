package com.gao.bryan.app_viewpager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bryan on 2017/3/27.
 */

public class TwoFragment extends Fragment {
    private List<Date> list ;
    public TwoFragment() {
    }

    public TwoFragment(List<Date> list) {
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twofragment,container,false);
        final TextView textView = (TextView) view.findViewById(R.id.textView2);
        textView.setText(list.get(getArguments().getInt("content")).getMessage());
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = list.get(getArguments().getInt("content"));
                date.setMessage(editText.getText().toString());
                textView.setText(editText.getText().toString());
                TextView activity  = (TextView) getActivity().findViewById(R.id.textView3);
                activity.setText(editText.getText().toString());
            }
        });


        return view;
    }
}
