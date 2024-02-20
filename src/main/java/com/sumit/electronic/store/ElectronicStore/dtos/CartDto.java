package com.sumit.electronic.store.ElectronicStore.dtos;


import com.sumit.electronic.store.ElectronicStore.entities.CartItem;
import com.sumit.electronic.store.ElectronicStore.entities.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

    private String cartId;
    private Date createdAt;
    private UserDto user;
    private List<CartItem> items = new ArrayList<>();

}
