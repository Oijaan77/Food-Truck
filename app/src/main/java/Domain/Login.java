package Domain;


import java.io.Serializable;

/**
 * Login Obj for login info
 */
public class Login implements Serializable {
    private String email = "";
    private String password = "";

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * regex check for email and password
     * @return true/false for conformity
     */
    public Boolean Validate(){
        String regex2 = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[!@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        String regex3 = "^(.+)@(.+)$";

        if (password == null || password.equals("")) return false;
        if (email == null || email.equals("")) return false;
        if (!password.matches(regex2)) return false;
        if (password.length() < 7) return false;
        if (!email.matches(regex3)) return false;

        return true;
    }
}
