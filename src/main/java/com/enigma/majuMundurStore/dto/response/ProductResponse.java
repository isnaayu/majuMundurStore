package com.enigma.majuMundurStore.dto.response;

import com.enigma.majuMundurStore.entity.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {
        private String productId;
        private String productName;
        private String ProductDesc;
        private Integer stock;
        private Long price;
}
