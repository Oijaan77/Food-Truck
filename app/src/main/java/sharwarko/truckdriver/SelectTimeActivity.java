package sharwarko.truckdriver;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import Domain.Order;

/**
 * Select time activity
 */
public class SelectTimeActivity extends AppCompatActivity {
    private final String TAG = "SelectTimeActivity";
    TimePicker timePicker;
    int T1hour, T1minute;
    Button choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        choose = (Button) findViewById(R.id.chooseBtn);
        choose.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                chooseTime();
            }
        });
    }

    /**
     * Config timepicker, get user pickup time, and send partially created obj to checkout activity
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void chooseTime() {
        timePicker = (TimePicker) findViewById(R.id.timePicker1);

        TimePickerDialog timePickerDialog = new TimePickerDialog(SelectTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                T1hour = hourOfDay;
                T1minute = minute;
                timePicker.setHour(T1hour);
                timePicker.setMinute(T1minute);
                timePicker.setIs24HourView(true);
                Calendar calendar = Calendar.getInstance();
                calendar.set(0,0,0, T1hour, T1minute);

            }
        }, 12, 0, false);
        Date currentTime = Calendar.getInstance().getTime();
        String date = DateFormat.getDateInstance().toString();
        String time = "Order Time: "+currentTime + "\n Pick-up Time: " +
                Integer.toString(timePicker.getHour()) + ':' + Integer.toString(timePicker.getMinute());
        Order order = (Order) getIntent().getSerializableExtra("Order");
        order.setPickUpTime(time);
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtra("Order", order);
        startActivity(intent);
    }
}
