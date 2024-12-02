package com.vn.equipment_manager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "equipment_delivery")
public class EquipmentDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_type_id")
    private EquipmentType equipmentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "equipment_status")
    private Integer equipmentStatus;

    @Column(name = "status")
    private Integer status;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "updated_at")
    private Date updatedAt = new Date();

}