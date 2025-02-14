package com.zezesheep.product_api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zezesheep.product_api.dto.ProductDTO;
import com.zezesheep.product_api.model.Product;
import com.zezesheep.product_api.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductDTO::convert).collect(Collectors.toList());
    }

    public List<ProductDTO> getByCategoryId(Long categoryId){
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return products.stream().map(ProductDTO::convert).collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier){
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        return product == null ? null : ProductDTO.convert(product);
    }

    public ProductDTO save(ProductDTO productDTO){
        Product product = productRepository.save(Product.convert(productDTO));
        return ProductDTO.convert(product);
    }

    public void delete(Long productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            productRepository.delete(productOptional.get());
        }
    }

    public ProductDTO editProduct(Long id, ProductDTO dto){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        if(dto.getNome() != null && !dto.getNome().isEmpty()){
            product.setNome(dto.getNome());
        }

        if(dto.getPreco() != null){
            product.setPreco(dto.getPreco());
        }
        return ProductDTO.convert(productRepository.save(product));
    }

    public Page<ProductDTO> getAllPage(Pageable page){
        Page<Product> products = productRepository.findAll(page);
        return products.map(ProductDTO::convert);
    }
    
}
