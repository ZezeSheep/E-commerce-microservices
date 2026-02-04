package com.zezesheep.shopping_api.repository;

import java.time.LocalDate;
import java.util.List;

import com.zezesheep.shopping_api.dto.ShopReportDTO;
import com.zezesheep.shopping_api.model.Shop;

public interface ReportRepository {

    public List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo);

    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim);
    
}
