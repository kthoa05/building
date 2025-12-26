package building.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Building")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="nameBuilding")
    private String name;
    @Column(name = "floorArea")
    private int floorArea;
    @ManyToOne
    @JoinColumn(name = "district")
    private DistrictEntity district;
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentArea;
    @Column(name = "ward")
    private String ward;
    @Column(name = "street")
    private String street;
    @Column(name = "numberOfBasement")
    private int numberOfBasement;
    @Column(name = "position")
    private String position;
    @Column(name = "level")
    private String level;
    @Column(name = "managerName")
    private String managerName;
    @Column(name = "managerPhoneNumber")
    private String managerPhoneNumber;
    @Column(name = "rentPrice")
    private double rentPrice;
    @Column(name = "brokeageFee")
    private double brokeageFee;
    @Column(name = "emptyArea")
    private double emptyArea;
    @Column(name = "serviceFee")
    private String serviceFee;
}
