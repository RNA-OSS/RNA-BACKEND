package com.dna.rna.domain.user;

import com.dna.rna.domain.CRUDPermissions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "user_role")
public class UserRole implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @ElementCollection
    @CollectionTable(name = "PERMISSIONS",
        joinColumns = @JoinColumn(name = "USER_ROLE_ID"))
    private List<CRUDPermissions> permissions = new ArrayList<>();

    @Override
    public String getAuthority() {
        return roleName;
    }
}
