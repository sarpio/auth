package com.sarpio.security.model;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roleEntities = new HashSet<>();

    @OneToOne(mappedBy="usersEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private UserDetailEntity userDetails;

    public UsersEntity(UsersEntity usersEntity) {
        this.active = usersEntity.getActive();
        this.roleEntities = usersEntity.getRoleEntities();
        this.username = usersEntity.getUsername();
        this.id = usersEntity.getId();
        this.password = usersEntity.getPassword();
        this.userDetails = usersEntity.getUserDetails();
        this.roleEntities = usersEntity.getRoleEntities();
    }
}
