package Domain;

/**
 * User class for signup. fName and lName fields unused (for later implementation)
 */
public class User {
    private int id = -1;
    private String fName = "";
    private String lName = "";
    private int zip = 0;
    private Login login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    /**
     * Regex to validate zip code
     * @return
     */
    public Boolean Validate(){
        String regex = "^[a-zA-Z]+$";

        if (fName == null || fName.equals("")) return false;
        if (lName == null || lName.equals("")) return false;
        if (!fName.matches(regex)) return false;
        if (!lName.matches(regex)) return false;

        return true;
    }

}
