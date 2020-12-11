package Lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import Domain.MenuItems;
import sharwarko.truckdriver.R;

/**
 * Array adapter config for food items selector in order process
 */
public class SelectFoodListAdapter extends ArrayAdapter<MenuItems> {
    private Context mContext;
    private int mResource;

    public SelectFoodListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItems> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int image = getItem(position).getImage();
        String item = getItem(position).getItem();
        String description = getItem(position).getNotes();
        String price = getItem(position).getCost();

        MenuItems menuItems = new MenuItems(image, item, description, price);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView TVitem = (TextView) convertView.findViewById(R.id.textView2);
        TextView TVnotes = (TextView) convertView.findViewById(R.id.tvTime);
        NumberPicker numberPicker = (NumberPicker)convertView.findViewById(R.id.chkBox);

        TVitem.setText(menuItems.getItem());
        TVnotes.setText(menuItems.getNotes() + " -" + menuItems.getCost());
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(30);

        return convertView;
    }
}
