package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@ToString
@SequenceGenerator(
        name = "base_id_gen",
        sequenceName = "agency_seq",
        allocationSize = 1
)
@NoArgsConstructor
public class Agency extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private int phoneNumber;
//////////  Address  /////////////
    @OneToOne(cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    private Address addresses;

    /////////// owner  /////////////
    @ManyToMany

    private List<Owner> owners;

    @OneToMany(mappedBy = "agency")
    @ToString.Exclude
    private List<Rent_info> rentInfo;


    public Agency(String name, int phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
