package sharwarko.truckdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import Domain.Login;
import Domain.Order;
import Service.AdminCacheImplSvc;
import Service.OrderCacheImplSvc;
import Service.Session;

/**
 * statistics activity
 */
public class TruckStatsActivity extends AppCompatActivity {
    BarChart barChart;
    Session session;
    ArrayList<Order> orders = new ArrayList<>();
    private final String TAG = "TruckStatsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_stats);

        //get orders, then calculate based on cost.
        OrderCacheImplSvc orderCacheImplSvc = OrderCacheImplSvc.getInstance();
        session = new Session(this);
        orders = orderCacheImplSvc.retrieveAll();

        //configure barchart
        float total = 0;
        for ( int i = 0; i < orders.size(); i++){
            Log.i(TAG, orders.get(i).getCost());
            total += Double.parseDouble(orders.get(i).getCost());
        }
        barChart = (BarChart) findViewById(R.id.bars);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f, total/5));
        BarDataSet barDataSet = new BarDataSet(barEntries, "People Fed by Your Orders");

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
    }
}
