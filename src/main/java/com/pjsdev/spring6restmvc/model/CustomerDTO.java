package com.pjsdev.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class CustomerDTO {

    private UUID id;
    private Integer version;
    private String name;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
