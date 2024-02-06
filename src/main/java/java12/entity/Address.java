package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "addresses")
@Setter
@Getter
@NoArgsConstructor
@ToString
@SequenceGenerator(
        name = "base_id_gen",
        sequenceName = "addresses_seq",
        allocationSize = 1
)

public class Address extends BaseEntity {
    @Column(name = "city")
    private String city;
    @Column(name = "region")
    private String region;
    @Column(name = "street",unique = true)
    private String street; // улитца
    @OneToOne(mappedBy = "addresses")
    private Agency agency;


    //////// House /////////////////////
//    @OneToOne
//    @ToString.Exclude
//    private House house;



    public Address(String city, String region, String street) {
        this.city = city;
        this.region = region;
        this.street = street;
    }
}
