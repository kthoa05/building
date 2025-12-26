package building.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "status")
    private int status;
    @Column(name = "createDate")
    private LocalDateTime createDate;

    //thu cong
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<UserRole> user;

    /**
     tu dong, xai @ManyToMany
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UserRole", /** tên table join giữa 2 bảng */
                joinColumns = @JoinColumn(name = "user_id"), /** khoá ngoại trỏ đến class hiện tại (User) */
                inverseJoinColumns = @JoinColumn(name = "role_id")) /** khoá ngoại trỏ đến class còn lại (Role) */
    private List<Role> roles;
}
