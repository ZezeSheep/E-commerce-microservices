package com.zezesheep.product_api.dto;

import com.zezesheep.product_api.model.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    
    @NotBlank
    private String nome;
    @NotNull
    private Float preco;
    @NotBlank
    private String descricao;
    @NotBlank
    private String productIdentifier;
    @NotNull
    private CategoryDTO categoryDTO;

    public static ProductDTO convert(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductIdentifier(product.getProductIdentifier());
        productDTO.setNome(product.getNome());
        productDTO.setPreco(product.getPreco());
        productDTO.setDescricao(product.getDescricao());
        productDTO.setCategoryDTO(CategoryDTO.convert(product.getCategory()));
        return productDTO;
    }
}
