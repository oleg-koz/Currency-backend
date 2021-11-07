package com.currency.ticker;

import com.currency.http.Util;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CurrencyTickerHandler {

    private final CurrencyTickerService currencyTickerService;

    @NonNull
    public Mono<ServerResponse> getCurrencies() {
        Flux<CurrencyTicker> receivedCurrencies = currencyTickerService.getCurrencyTickers();

        return Util.sendOkList(receivedCurrencies, CurrencyTicker.class);
    }

}
