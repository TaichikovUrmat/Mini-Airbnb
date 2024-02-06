package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import java12.enums.HouseType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "houses")
@Setter
@Getter
@NoArgsConstructor
@ToString
@SequenceGenerator(name ="base_id_gen",
                    sequenceName = "houses_seq",
                    allocationSize = 1)
public class House extends BaseEntity {

    private HouseType houseType;
    @Column(name = "rating")
    private  double rating;  // рейтинг
    @Column(name = "description")
    private String description;  // описание
    @Column(name = "room")
    private  int room;   //
    @Column(name = "furniture")
    private boolean furniture;  // мебель

    ///////////  address  //////////
    @OneToOne
    @ToString.Exclude
    private Address address;

    ////// Rent_info  ////////////
  @OneToOne
   private Rent_info rentInfo;
   ///// owner  /////
    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Owner owner;

  public House(HouseType houseType, double rating, String description, int room, boolean furniture) {
    this.houseType = houseType;
    this.rating = rating;
    this.description = description;
    this.room = room;
    this.furniture = furniture;
  }
}
