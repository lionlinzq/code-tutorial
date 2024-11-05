package pers.lionlinzq.excel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "role_permissions")
public class RolePermission {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private pers.lionlinzq.excel.entity.Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private pers.lionlinzq.excel.entity.Permission permission;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}