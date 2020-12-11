package Lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import Domain.FoodTruck;
import sharwarko.truckdriver.R;

/**
 * Array adapter config for food truck selector in order process
 */
public class TruckListAdapter extends ArrayAdapter<FoodTruck> {
    private Context mContext;
    private int mResource;

    public TruckListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FoodTruck> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String truck = getItem(position).getName();
        String location = getItem(position).getLocation();

        FoodTruck foodTruck = new FoodTruck(truck, location);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView TVitem = (TextView) convertView.findViewById(R.id.textView2);
        TextView TVnotes = (TextView) convertView.findViewById(R.id.tvTime);

        TVitem.setText(foodTruck.getName());
        TVnotes.setText(foodTruck.getLocation());

        return convertView;
    }
}
