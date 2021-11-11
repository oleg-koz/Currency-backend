package com.currency.ticker;

import com.currency.http.Util;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Controller
@AllArgsConstructor
public class CurrencyTickerHandler extends CurrencyTicker {

    private final CurrencyTickerService currencyTickerService;

    @NonNull
    public Mono<ServerResponse> getCurrencies() {
        Flux<CurrencyTicker> receivedCurrencies = currencyTickerService.getCurrencyTickers();

        return Util.sendOkList(receivedCurrencies, CurrencyTicker.class);
    }

    @NonNull
    public Mono<ServerResponse> getCurrencyById(ServerRequest req) {
        String kodas = req.pathVariable("kodas");
        Flux<CurrencyTicker> receivedCurrencies = currencyTickerService.getCurrencyById(kodas);
        return Util.sendOkList(receivedCurrencies, CurrencyTicker.class);
    }

    @NonNull
    public Mono<ServerResponse> getCurrencyByDate (ServerRequest req) {
        String datas = req.pathVariable("datas");
        Flux<CurrencyTicker> receivedCurrencies = currencyTickerService.getCurrencyByDate(datas);
        return Util.sendOkList(receivedCurrencies, CurrencyTicker.class);
    }

}
