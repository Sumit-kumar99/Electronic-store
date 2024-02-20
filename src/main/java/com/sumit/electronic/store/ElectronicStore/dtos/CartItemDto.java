package com.sumit.electronic.store.ElectronicStore.dtos;

import com.sumit.electronic.store.ElectronicStore.entities.Cart;
import com.sumit.electronic.store.ElectronicStore.entities.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {

    private int cartItemId;
    private ProductDto product;
    private  int quantity;
    private  int totalPrice;
}
