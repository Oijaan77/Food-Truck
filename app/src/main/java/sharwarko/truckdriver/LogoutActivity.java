package sharwarko.truckdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Service.OrderCacheImplSvc;
import Service.Session;

/**
 * Logout Activity. End session
 */
public class LogoutActivity extends AppCompatActivity {
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        session = new Session(this);

        Button logoutBtn = (Button) findViewById(R.id.lOutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogOUt();
            }
        });

        Button exitBtn = (Button) findViewById(R.id.cancel2Btn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setExitB();
            }
        });
    }

    private void setExitB() {
        finish();
    }

    private void setLogOUt() {
        session.setEmail(null);
        session.setSession(false);
        OrderCacheImplSvc orderCacheImplSvc = OrderCacheImplSvc.getInstance();
        orderCacheImplSvc.clearAll();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}