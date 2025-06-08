package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleauthorPayload {
    private TitleauthorIdDto idDto;
    private TitleauthorDto dto;
}
