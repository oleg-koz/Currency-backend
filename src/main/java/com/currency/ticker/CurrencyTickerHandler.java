package com.currency.ticker;

import com.currency.http.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Component
@AllArgsConstructor
public class CurrencyTickerHandler extends CurrencyTicker {

    private final CurrencyTickerService currencyTickerService;

    @NonNull
    public Mono<ServerResponse> getCurrencies() {
        Flux<CurrencyTicker> receivedCurrencies = currencyTickerService.getCurrencyTickers();

        return Util.sendOkList(receivedCurrencies, CurrencyTicker.class);
    }

    @NonNull
    public Mono<ServerResponse> getCurrencyById() {
        Flux<CurrencyTicker> receivedCurrencies = currencyTickerService.getCurrencyById();
        return Util.sendOkList(receivedCurrencies, CurrencyTicker.class);
    }

    @NonNull
    public Mono<ServerResponse> getCurrencyByDate () {
        Flux<CurrencyTicker> receivedCurrencies = currencyTickerService.getCurrencyByDate();
        return Util.sendOkList(receivedCurrencies, CurrencyTicker.class);
    }

}
