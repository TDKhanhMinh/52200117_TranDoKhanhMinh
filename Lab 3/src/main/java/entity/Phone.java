package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "mobilephone")
@Getter
@Setter
@NoArgsConstructor
public class Phone {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;
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

    public Phone(String id, String name, String color, int price, String country, int quantity) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.country = country;
        this.quantity = quantity;

    }

    @Override
    public String toString() {
        return "Phone{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", country='" + country + '\'' +
                ", quantity=" + quantity +
                ", manufacture=" + manufacture +
                '}';
    }
}
