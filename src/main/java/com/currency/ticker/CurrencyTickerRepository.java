package com.currency.ticker;

import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

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

    public Flux<CurrencyTicker> getCurrencyById() {
        return r2dbcEntityTemplate.select(query(where("valiutos_kodas").is("GBP")), CurrencyTicker.class);
    }

    public Flux<CurrencyTicker> getCurrencyByDate() {
        return r2dbcEntityTemplate.select(query(where("data").is("2021-11-09")), CurrencyTicker.class);
    }

}
