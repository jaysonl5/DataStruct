package edu.uco.jlewis30.p3jaysonl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;



public class LogActivity extends Activity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        ArrayList results;

        Intent intent = getIntent();
        results = intent.getStringArrayListExtra("results");


        TextView logTitle = (TextView) findViewById(R.id.txtLogTitle);
        TextView resTitle = (TextView) findViewById(R.id.txtResTitle);
        TextView logResults = (TextView) findViewById(R.id.txtResults);


        String resTitleStr = ("#" + "             " + "Winner" + "\n");

        resTitle.setText(resTitleStr);

        if(results.isEmpty()) {
            logTitle.setText(R.string.strNoLog);
        } else {
            for(int i = 0; i < results.size(); ++i) {
                String resultInstance = (logResults.getText().toString() + (i + 1) + "                    " + results.get(i).toString() + "\n") ;
               logResults.setText(resultInstance);
            }
        }


    }

}
