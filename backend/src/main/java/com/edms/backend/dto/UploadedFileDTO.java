package com.edms.backend.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadedFileDTO {
    private String serialNo;
    private String drawingNo;
    private String sheet;
    private String rev;
    private Integer stationId;
    private Integer stationTypeId;
    private Integer categoryId;
    private Integer regionId;
    private Integer uploadTypeId;
    private Integer userId;
    private MultipartFile file;
}

