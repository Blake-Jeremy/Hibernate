package blake.hibernate;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HibernateRunner {
    private List<User> users;
    private HibernateConfig theHibernateUtility;
    private String name;
    private String pass;
    private String name2;
    private String number;

    public HibernateRunner(){
        theHibernateUtility = new HibernateConfig();
    }

    public static void main(String[] args){
        HibernateRunner myHibernateRunner = new HibernateRunner();
        System.out.println("\n\u001B[31m" + "Adding New Users" + "\u001B[0m");
        myHibernateRunner.addNewUser("gshovald", "pass8584");
        myHibernateRunner.addNewUser("ebrikowski", "pass43443");
        System.out.println("\n\u001B[31m" + "Showing All Users" + "\u001B[0m");
        myHibernateRunner.showAllUsers();
        System.out.println("\n\u001B[31m" + "Modifying a User" + "\u001B[0m");
        myHibernateRunner.modifyUser("gshovald", "sshovald");
        System.out.println("\n\u001B[31m" + "Adding A Phone Number" + "\u001B[0m");
        myHibernateRunner.addPhoneNumber("ebrikowski","(546)222-6767");
        System.out.println("\n\u001B[31m" + "Adding A Shared Phone Number" + "\u001B[0m");
        myHibernateRunner.addSharedPhoneNumber("eblake", "sshovald", "(546)222-9898");
        System.out.println("\n\u001B[31m" + "Deleting Added Users" + "\u001B[0m");
        myHibernateRunner.deleteAddedUser("sshovald");
        myHibernateRunner.deleteAddedUser("ebrikowski");
        System.out.println("\n\u001B[31m" + "Showing All Users" + "\u001B[0m");
        myHibernateRunner.showAllUsers();
    }

    // show how to add records to the database
    public void addNewUser(String name, String pass) {
        this.name = name;
        this.pass = pass;
        Session session = theHibernateUtility.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User passedUser = new User();
        passedUser.setUname(name);
        passedUser.setPword(pass);
        session.save(passedUser);
        transaction.commit();
        System.out.println("User " + name + " with generated ID " + passedUser.getId() + " added");
    }

    // show how to get a collection of type List containing all of the records in the app_user table
    private void showAllUsers() {
        Session session = theHibernateUtility.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        // get a single User instance from the database.
        Query allUsersQuery = session.createQuery("select u from User as u order by u.id");
        users = allUsersQuery.list();
        Iterator<User> iter = users.iterator();;
        while(iter.hasNext()) {
            User element = iter.next();
            System.out.println("\u001B[32mData Entry:\u001B[0m " + element.toString() + " \u001B[32m# of Phone Numbers:\u001B[0m " + element.getPhoneNumbers().size());
        }
        System.out.println("\u001B[33mTotal Users in Database:\u001B[0m "+ users.size());
        transaction.commit();
    }

    // show how to modify a database record
    private void modifyUser(String name, String name2) {
        this.name = name;
        this.name2 = name2;
        Session session = theHibernateUtility.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        // get a single User instance from the database.
        Query singleUserQuery = session.createQuery("select u from User as u where u.uname='" + name + "'");
        User passedUser = (User)singleUserQuery.uniqueResult();
        passedUser.setUname(name2);
        session.merge(passedUser);
        transaction.commit();
        System.out.println("User " + name + " changed to " + name2);
        showAllUsers();
    }

    // add a phone number to a name
    private void addPhoneNumber(String name, String number) {
        this.name = name;
        this.number = number;
        Session session = theHibernateUtility.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        // get a single User instance from the database.
        Query myQuery = session.createQuery("select u from User as u where u.uname='" + name + "'");
        User passedName = (User) myQuery.uniqueResult();
        PhoneNumber myPhoneNumber = new PhoneNumber();
        myPhoneNumber.setPhone(number);
        Set<PhoneNumber> userPhoneNumber = passedName.getPhoneNumbers();
        userPhoneNumber.add(myPhoneNumber);
        session.save(myPhoneNumber);
        session.merge(passedName);
        transaction.commit();
        System.out.println("Phone number " + number + " added to " + name);
        showAllUsers();
    }

    // add a shared phone number to two names
    private void addSharedPhoneNumber(String name, String name2, String number) {
        this.name = name;
        this.name2 = name2;
        this.number = number;
        Session session = theHibernateUtility.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        // get two User instances from the database.
        Query query1 = session.createQuery("select u from User as u where u.uname='" + name + "'");
        User passedName1 = (User) query1.uniqueResult();
        Query query2 = session.createQuery("select u from User as u where u.uname='" + name2 + "'");
        User passedName2 = (User) query2.uniqueResult();
        PhoneNumber sharedPhoneNumber = new PhoneNumber();
        sharedPhoneNumber.setPhone(number);
        Set<PhoneNumber> user1PhoneNumbers = passedName1.getPhoneNumbers();
        user1PhoneNumbers.add(sharedPhoneNumber);
        Set<PhoneNumber> user2PhoneNumbers = passedName2.getPhoneNumbers();
        user2PhoneNumbers.add(sharedPhoneNumber);
        session.save(sharedPhoneNumber);
        session.merge(passedName1);
        session.merge(passedName2);
        transaction.commit();
        System.out.println("Shared phone number " + number + " added to " + name + " and " + name2);
        showAllUsers();
    }

    // delete a user
    private void deleteAddedUser(String name) {
        this.name = name;
        Session session = theHibernateUtility.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query singleUserQuery = session.createQuery("select u from User as u where u.uname='" + name + "'");
        User passedUser = (User)singleUserQuery.uniqueResult();
        session.delete(passedUser);
        transaction.commit();
        System.out.println("Deleted user " + name);
    }
}

