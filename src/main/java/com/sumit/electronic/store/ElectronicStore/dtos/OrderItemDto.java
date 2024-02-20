package com.sumit.electronic.store.ElectronicStore.dtos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    private int cartItemId;
    private ProductDto product;
    private int quantity;
    private int totalPrice;
}
