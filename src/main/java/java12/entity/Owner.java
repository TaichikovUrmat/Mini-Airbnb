package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import java12.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table( name = "owners")
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen",
                   sequenceName = "owners_seq",
                    allocationSize = 1
 )
@ToString
public class Owner extends BaseEntity {

    private String firs_Name;
    private String last_Name;
    @Column(unique = true)
    private String email;
    private LocalDate data_of_Birth;
    private Gender gender;

    ////// rent_info  ///////////
    @OneToMany(mappedBy = "owner")
    @ToString.Exclude
    private List<Rent_info> rentInfos;

    /////// House  /////////
    @OneToMany(mappedBy = "owner",cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<House> houses;

    ////////// Agency  //////////
    @ManyToMany(mappedBy = "owners")
   @ToString.Exclude
    private List<Agency> agencies;

    public Owner(String firs_Name, String last_Name, String email, LocalDate data_of_Birth, Gender gender) {
        this.firs_Name = firs_Name;
        this.last_Name = last_Name;
        this.email = email;
        this.data_of_Birth = data_of_Birth;
        this.gender = gender;

    }
}
