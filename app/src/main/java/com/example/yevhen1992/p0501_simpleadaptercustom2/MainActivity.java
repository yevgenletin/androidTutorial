package com.example.yevhen1992.p0501_simpleadaptercustom2;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Context context;
    //atribute for map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PROGRESSBAR = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";

    ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int load[] = {41, 48, 22, 35, 30, 67, 51, 88};

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String,Object>>();

        HashMap<String, Object> m;

        for (int i = 0; i< load.length; i++){
            m = new HashMap();
            m.put(ATTRIBUTE_NAME_TEXT, "Day"+(i+1)+".Load"+load[i]+"%");
            m.put(ATTRIBUTE_NAME_PROGRESSBAR, load[i]);
            m.put(ATTRIBUTE_NAME_LL, load[i]);
            data.add(m);
        }

        String[] from = {
                ATTRIBUTE_NAME_TEXT,
                ATTRIBUTE_NAME_PROGRESSBAR,
                ATTRIBUTE_NAME_LL
        };
        int[] to={
                R.id.tvLoad,
                R.id.pbLoad,
                R.id.llLoad
        };

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.item,
                from,
                to
        );

        adapter.setViewBinder(new MyViewBinder());

        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(adapter);
    }

    class MyViewBinder implements SimpleAdapter.ViewBinder{

        //Colors
        //int red = getResources().getColor(R.color.Red);
        int red = getResources().getColor(R.color.Red);
        int green = getResources().getColor(R.color.Green);
        int orange = getResources().getColor(R.color.Orange);



        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            int i = 0;
            switch(view.getId()) {
                case R.id.llLoad:
                    i = ((Integer) data).intValue();
                    if (i < 40) view.setBackgroundColor(red);
                    else if (i < 70) view.setBackgroundColor(orange);
                    else
                        view.setBackgroundColor(green);
                    return true;
                case R.id.pbLoad:
                    i = ((Integer) data).intValue();
                    ((ProgressBar) view).setProgress(i);
                    return true;
            }
            return false;
        }
    }
}
