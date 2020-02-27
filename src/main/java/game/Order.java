package game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name="Taco_Order")
public class Order implements Serializable {

  private static final int serialVersionUID = 1;
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;
  
  private Date placedAt;
  
  @ManyToOne
  private User user;
  
  @NotBlank(message="Delivery name is required")
  private String deliveryName;

  @NotBlank(message="Delivery mail is required")
  private String customerDeliveryMail="EXM";

  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;
  
  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
           message="Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;

  @ManyToMany(targetEntity= GameKeyShoppingForm.class)
  private List<GameKeyShoppingForm> GameKeyShoppingForm = new ArrayList<>();
  
  public void addDesign(GameKeyShoppingForm design) {
    this.GameKeyShoppingForm.add(design);
  }
  
  @PrePersist
  void placedAt() {
    this.placedAt = new Date();
  }
  
}
