package Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Session manager class.
 */
public class Session {
    private SharedPreferences prefs;

    public Session(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void setEmail(String email){
        prefs.edit().putString("email", email).commit();
    }
    public String getEmail(){
        String email = prefs.getString("email","");
        return email;
    }
    public void setSession(boolean session){
        prefs.edit().putBoolean("session",session);
    }
    public boolean getSession(){
        boolean session = prefs.getBoolean("session", false);
        return session;
    }
}
