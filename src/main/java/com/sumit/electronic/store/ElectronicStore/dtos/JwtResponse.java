package com.sumit.electronic.store.ElectronicStore.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class JwtResponse {
    private String jwtToken;
    private UserDto user;
}
