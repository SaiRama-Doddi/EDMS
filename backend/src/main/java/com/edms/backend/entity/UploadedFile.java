package com.edms.backend.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "uploaded_files")
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "serial_no", nullable = false)
    private String serialNo;

    @Column(name = "drawing_no", nullable = false)
    private String drawingNo;

    @Column(name = "sheet", nullable = false)
    private String sheet;

    @Column(name = "rev", nullable = false)
    private String rev;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private StationCode station;

    @ManyToOne
    @JoinColumn(name = "station_type_id", nullable = false)
    private StationType stationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "station_type", nullable = false)
    private StationEnum stationTypeEnum; // ✅ Stores ENUM "Minor" or "Major"

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @ManyToOne
    @JoinColumn(name = "upload_type_id", nullable = false)
    private UploadType uploadType;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "uploaded_at", nullable = false, updatable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();
}
