public class User {
    String fname;
    String lname;
    String password;
    String phone;
    String address;

    public User(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public User(String fname, String lname, String phone, String address) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.address = address;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    String[] getName() {
        return new String[]{fname, lname};
    }

    String[] getDetails() {
        return new String[]{phone, address};
    }
}
