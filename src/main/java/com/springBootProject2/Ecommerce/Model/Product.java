package com.springBootProject2.Ecommerce.Model;

import com.springBootProject2.Ecommerce.Enum.ProductCategory;
import com.springBootProject2.Ecommerce.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productName;
    private int price;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    Item item;


}
