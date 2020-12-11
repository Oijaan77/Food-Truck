package sharwarko.truckdriver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Domain.MenuItems;
import Service.AdminCacheImplSvc;
import Service.OrderCacheImplSvc;
import Service.Session;

/**
 * Main activity
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private Session session;
    //admin--> admin@adsmin.com; password is Administrator77!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Entering OnCreate");

        session = new Session(this);
        //About Button func
        Button about = (Button) findViewById(R.id.AboutBtn);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAboutLink();
            }
        });

        //Menu Button Func
        Button menu = (Button) findViewById(R.id.MenuBtn);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMenuLink();
            }
        });

        //Create Order Button Func
        Button order = (Button) findViewById(R.id.OrderBtn);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCreateLink();
            }
        });

        //View Stat Button Func
        Button stats = (Button) findViewById(R.id.ShelterBtn);
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatsLink();
            }
        });

    }

    /**
     * On start to load login info and get orders
     */
    @Override
    protected void onStart() {
        super.onStart();
        //load login and get orders on start
        TextView loginName = (TextView) findViewById(R.id.loginView);
        if(session.getEmail() == ""){
            loginName.setText("Not Logged In");
        } else {
            loginName.setText(session.getEmail());
            OrderCacheImplSvc orderCacheImplSvc = OrderCacheImplSvc.getInstance();
            orderCacheImplSvc.clearAll();
            orderCacheImplSvc.updateList(session.getEmail());
            AdminCacheImplSvc adminCacheImplSvc = AdminCacheImplSvc.getInstance();
            adminCacheImplSvc.clearAll();
            adminCacheImplSvc.updateList();
        }
        supportInvalidateOptionsMenu();
    }

    private void setAboutLink() {
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }
    private void setMenuLink() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Create order must login first
     */
    private void setCreateLink() {
        if(session.getEmail() == ""){
            Toast.makeText(getApplicationContext(), "Please login before placing an order", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SelectFoodActivity.class);
            startActivity(intent);
        }
    }
    private void setStatsLink() {
        Intent intent = new Intent(this, TruckStatsActivity.class);
        startActivity(intent);
    }

    /**
     * decide which menu to display
     * @param menu
     * @return true for menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(session.getEmail() == "") {
            getMenuInflater().inflate(R.menu.appbar, menu);
        }
        else if(session.getEmail().equals("admin@admin.com")) {
            getMenuInflater().inflate(R.menu.admin, menu);
        } else {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    /**
     * Menu handler
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.login:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.logout:
                intent = new Intent(this, LogoutActivity.class);
                startActivity(intent);
                break;
            case R.id.cart:
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
            case R.id.admin:
                intent = new Intent(this, AdminActivity.class);
                startActivity(intent);
                break;
            case(android.R.id.home):
                finish();
                break;
            default:
                super.onOptionsItemSelected(item);
                break;
        }
        return true;
    }
}
