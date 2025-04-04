package com.edms.backend.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "form_table")
@Getter
@Setter
public class FormEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "autoid")
    private int id;

    @Column(name = "serial_no", nullable = false)
    private int serialNo;

    @Column(name = "rev", nullable = false)
    private int rev;

    @Column(name = "drawing_no", nullable = false)
    private String drawingNo; // Contains letters, numbers, and '-'

    @Column(name = "sheet", nullable = false)
    private String sheet; // Contains numbers and letters

    @Column(name = "section_code", nullable = false)
    private int sectionCode; // Only numbers

    @Column(name = "document_type", nullable = false)
    private String documentType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "station_code_id", nullable = false)
    private StationCode stationCode;

    @ManyToOne
    @JoinColumn(name = "station_type_id", nullable = false)
    private StationType stationType;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "upload_type_id", nullable = false)
    private UploadType uploadType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}

