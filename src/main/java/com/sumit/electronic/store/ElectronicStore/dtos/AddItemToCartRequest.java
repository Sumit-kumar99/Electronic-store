package com.sumit.electronic.store.ElectronicStore.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddItemToCartRequest {
    private  String productId;
    private  int quantity;

}
