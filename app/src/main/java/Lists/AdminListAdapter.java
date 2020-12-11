package Lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import Domain.MenuItems;
import Domain.Order;
import sharwarko.truckdriver.R;

/**
 * Adapter for admin order list
 */
public class AdminListAdapter extends ArrayAdapter<Order> {
    private Context mContext;
    private int mResource;
    public AdminListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Order> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int id = getItem(position).getId();
        String email = getItem(position).getLogin().getEmail();
        String foods = getItem(position).getMenuItems().toString().replaceAll("[\\p{Ps}\\p{Pe}]", "");
        String time = getItem(position).getPickUpTime();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView TVinfo = (TextView) convertView.findViewById(R.id.tvInfo);
        TextView TVtime = (TextView) convertView.findViewById(R.id.tvPickUp);
        TextView TVId = (TextView)  convertView.findViewById(R.id.tvID);

        TVinfo.setText("Order id: "+ Integer.toString(id) + "\n Email: " + email + "\n Order: " + foods);
        TVtime.setText(time);
        TVId.setText(Integer.toString(id));

        return convertView;
    }
}
