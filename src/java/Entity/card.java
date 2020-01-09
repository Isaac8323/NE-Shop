package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "card")
@NamedQueries({
    @NamedQuery(name="card.findAll", query = "SELECT c FROM card c"),
    @NamedQuery(name = "card.Control", query = "SELECT c FROM card c WHERE c.cvc = :cvc"),
    @NamedQuery(name = "card.sum", query = "SELECT c FROM card c WHERE c.card_number = :cn and c.cvc = :cvc")
})
public class card {
    @Id
    @Column(name="card_number")
    private int card_number;
    @Column(name="balance")
    private double balance;
    @Column(name="cvc")
    private int cvc;
    @Column(name="titular")
    private String titular;

    public card(){
        
    }
    
    public card(int card_number, double balance, int cvc, String titular) {
        this.card_number = card_number;
        this.balance = balance;
        this.cvc = cvc;
        this.titular = titular;
    }

    public int getCard_number() {
        return card_number;
    }

    public void setCard_number(int card_number) {
        this.card_number = card_number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
    
}
