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
import Domain.Order;
import sharwarko.truckdriver.R;

/**
 * Array adapter config for cart orders
 */
public class OrdersListAdapter extends ArrayAdapter<Order> {
    private Context mContext;
    private int mResource;

    public OrdersListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Order> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int id = getItem(position).getId();
        String foodTruck = getItem(position).getFoodTruck().toString();
        String cost = getItem(position).getCost();
        String pickup = getItem(position).getPickUpTime();
        Boolean complete = getItem(position).getComplete();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        String status = "";
        if (!complete){
            status = "Pending";
        } else {
            status = "Complete";
        }
        TextView TVorder = (TextView) convertView.findViewById(R.id.tvOrder);
        TextView TVfoodTruck = (TextView) convertView.findViewById(R.id.tvFoodTruck);

        TVorder.setText(Integer.toString(id));
        TVfoodTruck.setText(foodTruck +"\n Cost: $" +cost + "\n "+ pickup + "\n Status: " + status);

        return convertView;
    }
}
