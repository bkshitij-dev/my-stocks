package com.thedevjournal.mystocks.dto.response;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectorResponseDto {

    private Long id;
    private String name;
}
