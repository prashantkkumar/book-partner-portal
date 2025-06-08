package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleauthorDto implements Serializable {
    private Byte auOrd;
    private Integer royaltyper;
    private String authorName;
    private String titleName;
}
