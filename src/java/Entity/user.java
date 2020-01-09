package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "user.findAll", query = "SELECT u FROM user u"),
    @NamedQuery(name = "user.Control", query = "SELECT u FROM user u WHERE u.username = :username and u.password = :password"),
    @NamedQuery(name = "user.phone", query = "SELECT u FROM user u WHERE u.username = :username and u.phone = :phone"),
    @NamedQuery(name = "user.type", query = "SELECT u FROM user u WHERE u.user_type = :type")
})
public class user {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "sex")
    private char sex;
    @Column(name = "phone")
    private String phone;
    @Column(name = "born")
    private String born;
    @Column(name = "user_type")
    private char user_type;
    @Column(name = "credit_card")
    private int credit_card;

    public user() {
        
    }

    public user(String username, String password, String name, String lastname, char sex, String phone, String born, char user_type, int credit_card) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.sex = sex;
        this.phone = phone;
        this.born = born;
        this.user_type = user_type;
        this.credit_card = credit_card;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public int getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(int credit_card) {
        this.credit_card = credit_card;
    }

    public char getUser_type() {
        return user_type;
    }

    public void setUser_type(char user_type) {
        this.user_type = user_type;
    }

}
