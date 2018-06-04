package book.lab.com.app_jsonfile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

import book.lab.com.app_jsonfile.vo.Exam;
import book.lab.com.app_jsonfile.vo.Person;

public class MainActivity extends AppCompatActivity {
	
	private Context context;
	private TextView textView1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        textView1 = (TextView)findViewById(R.id.textView1);
    }
    
    public void onClick(View view) {
    	switch(view.getId()) {
			case R.id.button1:
				// 取得 json 字串
				String json = getRowData(context, R.raw.score);
				
				// json to pojo
				Gson gson = new GsonBuilder().create();
	            Person p = gson.fromJson(json, Person.class);
				int sum = 0;
	            for(Exam e : p.getExam()) {
	            	sum += e.getScore();
				}
	            textView1.setText(json + "\n" + 
								p.getName() + " 總分:" + sum + "分");
				break;
    	}
	}
    
    private String getRowData(Context context, int res_id) {
    	
    	InputStream is = null;
    	InputStreamReader reader = null;
    	StringBuilder sb = new StringBuilder();
    	
    	try {
    		is = context.getResources().openRawResource(res_id);
    		reader = new InputStreamReader(is, "UTF-8");
    		char[] buffer = new char[1];
    		while(reader.read(buffer) != -1) {
    			sb.append(new String(buffer));
    		}
    		
    	} catch(Exception e) {
    		
    	} finally {
    		try {
    			if(is != null) is.close();
    			
    		} catch(Exception e) {}
    		
    	}
    	return sb.toString();
    }
}