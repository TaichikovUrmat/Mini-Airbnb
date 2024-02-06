package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "rent_info")
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(
        name = "base_id_gen",
        sequenceName = "rent_info_seq",
        allocationSize = 1
)
@ToString
public class Rent_info extends BaseEntity {

    private LocalDate checkin;
    private LocalDate checkOut;

    ////////  Address    /////////////
    @ManyToOne
    @ToString.Exclude
    private Agency agency;
    ///////////  Customer  ///////////
    @ManyToOne
    @ToString.Exclude
    private Customer customer;
    ////////// owner  ///////////
    @ManyToOne
    @ToString.Exclude
    private Owner owner;
    ///////// House  ////////////
    @OneToOne
    @ToString.Exclude
    private House house;


    public Rent_info(LocalDate checkin, LocalDate checkOut) {
        this.checkin = checkin;
        this.checkOut = checkOut;
    }


}

