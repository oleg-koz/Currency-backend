package com.currency.ticker;

import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class CurrencyTickerRepository {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public Mono<CurrencyTicker> saveCurrencyTicker(CurrencyTicker currencyTicker) {
        return r2dbcEntityTemplate.insert(currencyTicker);
    }

    public Flux<CurrencyTicker> getCurrencyTickers() {
        return r2dbcEntityTemplate.select(CurrencyTicker.class).all();
    }

}
