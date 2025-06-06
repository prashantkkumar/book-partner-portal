package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Store;

import java.io.Serializable;

/**
 * DTO for {@link Store}
 */
@Value
public class StoreDto implements Serializable {
    String storId;
    String storName;
    String storAddress;
    String city;
    String state;
    String zip;
}