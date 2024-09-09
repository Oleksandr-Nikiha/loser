package com.project.nikiha.loser.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.project.nikiha.loser.data.CurrencyDto;
import com.project.nikiha.loser.data.Currency;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy =  NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CurrencyMapper {
    List<Currency> map(List<CurrencyDto> listCurrencyDto);
    
    @Mapping(source = "currency", target = "currencyCode")
    @Mapping(source = "saleRateNB", target = "nbuRate")    
    Currency map(CurrencyDto currencyDto);
}
