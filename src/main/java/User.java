import jakarta.persistence.*;

@Entity
@Table(name = "usertable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fname", nullable = false)
    private String fname;

    @Column(name = "lname", nullable = false)
    private String lname;

    @Column(name = "pw", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

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

    public User(String fname, String lname, String password, String phone, String address) {
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public User() {}

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
