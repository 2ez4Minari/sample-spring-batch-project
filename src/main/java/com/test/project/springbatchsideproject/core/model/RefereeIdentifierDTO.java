package com.test.project.springbatchsideproject.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefereeIdentifierDTO {

    private String hashedMobileNumber;
    private String CIF;

}
