package name.chakimar.singletasktest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity implements OnClickListener {
	private Button btn1;
	private Button btn2;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }
    
	@Override
	public void onClick(View v) {
		if (v == btn1) {
			Intent intent = new Intent(this, SecondActivity.class);
			startActivity(intent);
		} else if (v == btn2) {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.co.jp"));
			startActivity(intent);
		}
	}
}