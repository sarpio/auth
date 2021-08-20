package com.sarpio.security.model;

import com.sarpio.order.model.OrderEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@RequiredArgsConstructor
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "password")
    @NotBlank(message = "password can't be blank")
    @NotEmpty(message = "username can't be empty")
    private String password;

    @Column(name = "username")
    @Length(min = 2, max = 30, message = "Name must be between 2-30 characters. ")
    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Name is invalid.")
    private String username;

    @Email(message = "Enter a valid email address.")
    private String email;

    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roleEntities = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<OrderEntity> order;

    public UsersEntity(UsersEntity usersEntity) {
        this.active = usersEntity.getActive();
        this.roleEntities = usersEntity.getRoleEntities();
        this.username = usersEntity.getUsername();
        this.id = usersEntity.getId();
        this.password = usersEntity.getPassword();
        this.roleEntities = usersEntity.getRoleEntities();
        this.order = usersEntity.getOrder();
    }
}
