package com.springBootProject2.Ecommerce.ResponseDTO;

import com.springBootProject2.Ecommerce.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCardResponseDto {

    private String cardNo;
    private CardType cardType;
}
