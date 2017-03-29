package com.gao.bryan.app_viewpager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bryan on 2017/3/27.
 */

public class OneFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<Date> mdate;
    private FragmentManager fm ;

    public OneFragment(List<Date> mdate, FragmentManager fm) {
        this.mdate = mdate;
        this.fm = fm;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.adapterlist,null);
        ListView listView = (ListView)v.findViewById(R.id.newlist);
        Myadapter myadapter = new Myadapter(mdate,getActivity());
        listView.setAdapter(myadapter);
        listView.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentTransaction  ftransaction = fm.beginTransaction();
        TwoFragment two = new TwoFragment(mdate);
        Bundle bundle = new Bundle();
        bundle.putInt("content",position);
        two.setArguments(bundle);

        //獲取Activity元件
        TextView tew = (TextView) getActivity().findViewById(R.id.textView3);
        tew.setText(mdate.get(position).getMessage());

        ftransaction.replace(R.id.myfragment, two);
        ftransaction.addToBackStack(null);
        ftransaction.commit();



    }
}
