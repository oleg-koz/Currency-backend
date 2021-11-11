package com.currency.ticker;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CurrencyTickerService {

    private final CurrencyTickerRepository currencyTickerRepository;

    public Mono<CurrencyTicker> saveCurrencyTicker(CurrencyTicker currencyTicker) {
        return currencyTickerRepository.saveCurrencyTicker(currencyTicker);
    }

    public Flux<CurrencyTicker> getCurrencyTickers() {
        return currencyTickerRepository.getCurrencyTickers();
    }

    public Flux<CurrencyTicker> getCurrencyById(String kodas) {
        return currencyTickerRepository.getCurrencyById(kodas);
    }

    public Flux<CurrencyTicker> getCurrencyByDate(String datas) {
        return currencyTickerRepository.getCurrencyByDate(datas);
    }

}
