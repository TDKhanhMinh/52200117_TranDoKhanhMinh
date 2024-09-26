package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "manufacture")
@Getter
@Setter
@NoArgsConstructor
public class Manufacture {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "employee", nullable = false)
    private int employee;

    @OneToMany(mappedBy = "manufacture",cascade = CascadeType.ALL)
    private List<Phone> phones;


    public Manufacture(String id, String name, String location, int employee) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", employee=" + employee +
                ", phones=" + phones +
                '}';
    }
}
