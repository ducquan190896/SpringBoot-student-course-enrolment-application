package com.quan.gradepractice.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "role", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})
})
@Entity(name = "Role")
public class Role {
    
    @Id
    @SequenceGenerator(
        name = "sequence_role",
        sequenceName = "sequence_role",
        allocationSize = 1
        
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator =  "sequence_role"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NonNull
    @NotNull(message = "role cannot be blank and must be defined as a type roleType")
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private RoleType name;

}
