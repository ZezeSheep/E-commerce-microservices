package com.zezesheep.product_api.converter;

import com.zezesheep.product_api.model.Category;
import com.zezesheep.product_api.model.Product;
import com.zezesheep.shopping_client.dto.CategoryDTO;
import com.zezesheep.shopping_client.dto.ProductDTO;

public class DTOConverter {

    public static CategoryDTO convert(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNome(category.getNome());
        return categoryDTO;
    }

    public static ProductDTO convert(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductIdentifier(product.getProductIdentifier());
        productDTO.setNome(product.getNome());
        productDTO.setPreco(product.getPreco());
        productDTO.setDescricao(product.getDescricao());
        productDTO.setCategory(DTOConverter.convert(product.getCategory()));
        return productDTO;
    }
    
}
