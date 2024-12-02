package com.vn.equipment_manager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "equipment_receive")
public class EquipmentReceive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_type_id")
    private EquipmentType equipmentType;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "equipment_status")
    private Integer equipmentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    private Date createdAt;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @Column(name = "warranty_date")
    private Date warrantyDate;

    @Column(name = "updated_at")
    private Date updatedAt;

}