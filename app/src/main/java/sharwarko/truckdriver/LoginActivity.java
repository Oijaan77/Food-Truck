package sharwarko.truckdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import Domain.Login;
import Service.AESCrypt;
import Service.RetrofitInterface;
import Service.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Login Activity
 */
public class LoginActivity extends AppCompatActivity {
    private final String TAG = "LoginActivity";
    private Session session;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new Session(this);

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setLoginLink();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

        Button createBtn = (Button) findViewById(R.id.createBtn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCreateLink();
            }
        });
        //Retrofit for server call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    /**
     * Create account nav
     */
    private void setCreateLink() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    /**
     * Log in checks credentials
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private void setLoginLink() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        HashMap<String, String> map = new HashMap<>();

        final EditText emailEdit = (EditText) findViewById(R.id.emailEdit);
        final EditText passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        //encrypt password before sending
        AESCrypt aesCrypt = new AESCrypt();
        String sha1 = aesCrypt.SHA1(passwordEdit.getText().toString());
        map.put("email", emailEdit.getText().toString());
        map.put("password", sha1);

        //send login to server for response
        Call<Login> call = retrofitInterface.executeLogin(map);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if (response.code() == 200) {

                    Login result = response.body();


                    session.setEmail(result.getEmail());
                    finish();


                } else if (response.code() == 404) {
                    Toast.makeText(LoginActivity.this, "Wrong Credentials",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
