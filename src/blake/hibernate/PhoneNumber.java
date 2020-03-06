package blake.hibernate;
/*******************************************************************
 *  PhoneNumber class
 *  Description: This is the class that correlates to my
 *  phone_number table in my database.
 *  I used ideas and layout from "Doing More With Java"
 *******************************************************************/

// Imported Libraries
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone_number")
public class PhoneNumber {

    @Id
    @GeneratedValue
    private Integer id;
    private String phone;

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
