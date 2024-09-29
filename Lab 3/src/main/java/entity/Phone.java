package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "mobilephone")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false, length = 128)
    private String name;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "manufacture_id")
    private Manufacture manufacture;

    public Phone(String name, String color, int price, String country, int quantity, Manufacture manufacture) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.country = country;
        this.quantity = quantity;
        this.manufacture = manufacture;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "quantity=" + quantity +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
