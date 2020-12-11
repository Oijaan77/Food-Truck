package sharwarko.truckdriver;

import androidx.appcompat.app.AppCompatActivity;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create account Activity
 */
public class CreateAccountActivity extends AppCompatActivity {
    private final String TAG = "CreateAccountActivity";
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //Retrofit for server call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Button submit = (Button) findViewById(R.id.subBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setSubLink();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
        Button cancel = (Button) findViewById(R.id.canBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCancelLink();
            }
        });

    }

    private void setCancelLink() {
        finish();
    }

    /**
     * Submit information to post request
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private void setSubLink() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        HashMap<String, String> map = new HashMap<>();

        final EditText emailEdit = (EditText) findViewById(R.id.emailFld);
        final EditText phoneEdit = (EditText) findViewById(R.id.editTextPhone);
        final EditText streetEdit = (EditText) findViewById(R.id.streetFld);
        final EditText zipEdit = (EditText) findViewById(R.id.zipFld);
        final EditText passwordEdit = (EditText) findViewById(R.id.passwordFld);
        final EditText passwordConf = (EditText) findViewById(R.id.passwordConfirm);
        //encrypt password
        AESCrypt aesCrypt = new AESCrypt();
        String sha1 = aesCrypt.SHA1(passwordEdit.getText().toString());

        map.put("email", emailEdit.getText().toString());
        map.put("phone", phoneEdit.getText().toString());
        map.put("street", streetEdit.getText().toString());
        map.put("zip", zipEdit.getText().toString());
        map.put("password", sha1);

        Login login = new Login();
        login.setEmail(emailEdit.getText().toString());
        login.setPassword(passwordEdit.getText().toString());
        Boolean result = login.Validate();
        //error checking and post call
        if (!result) {
            Toast.makeText(this,
                    "Check email format. Passwords must contain at least 7 characters, one special character, and one uppercase letter."
                    , Toast.LENGTH_SHORT).show();
        } else if (!passwordEdit.getText().toString().matches(passwordConf.getText().toString())){
            Toast.makeText(this,
                    "Passwords must match."
                    , Toast.LENGTH_SHORT).show();
        } else {

            Call<Void> call = retrofitInterface.executeSignup(map);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                    if (response.code() == 200) {
                        Toast.makeText(CreateAccountActivity.this,
                                "Signed up successfully", Toast.LENGTH_LONG).show();
                        finish();

                    } else if (response.code() == 400) {
                        Toast.makeText(CreateAccountActivity.this,
                                "Already registered", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CreateAccountActivity.this, t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}