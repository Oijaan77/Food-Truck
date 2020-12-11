package sharwarko.truckdriver;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * About us activity
 */
public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TextView contentView = (TextView) findViewById(R.id.contentView);
        contentView.setText("The Food Truck service is dedicated to providing food for shelter that is " +
                "subsidised by customers.");
    }
}
