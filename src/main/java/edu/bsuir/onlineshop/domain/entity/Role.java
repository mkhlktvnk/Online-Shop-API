package edu.bsuir.onlineshop.domain.entity;

import edu.bsuir.onlineshop.domain.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Entity
@Table(name = "roles")
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String authority;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authorities")
    private Collection<User> users;

    public Role(RoleType roleType) {
        this.authority = roleType.name();
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
