package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import java12.config.DataBaseConfig;
import java12.enums.FamilyStatus;
import java12.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@SequenceGenerator(
        name = "base_id_gen",
        sequenceName = "customers_seg",
        allocationSize = 1
)
@ToString
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Column(name = "firs_name")
    private String firs_Name;
    @Column(name = "last_name")
    private String last_Name;
    @Column(name = "email")
    private String email;
    @Column(name = "data_of_Birth")
    private LocalDate data_of_Birth;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "nationality")
    private  String nationality;  // nationality
    @Column(name = "family_status")
    private FamilyStatus familyStatus;



    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Rent_info> rentInfo;

    public Customer(String firs_Name, String last_Name, String email, LocalDate data_of_Birth, Gender gender, String nationality, FamilyStatus familyStatus) {
        this.firs_Name = firs_Name;
        this.last_Name = last_Name;
        this.email = email;
        this.data_of_Birth = data_of_Birth;
        this.gender = gender;
        this.nationality = nationality;
        this.familyStatus = familyStatus;
    }
}
