package com.currency;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.currency.ticker.CurrencyTickerHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class WebFluxServer {

		@Bean
		public RouterFunction<ServerResponse> serverApi(CurrencyTickerHandler currencyTickerHandler) {

			RouterFunction<ServerResponse> currencyApi = route(
					GET("/currencies"), req -> currencyTickerHandler.getCurrencies()).andRoute(
					GET("/currencies/{kodas}"), currencyTickerHandler::getCurrencyById).andRoute(
					GET("/currencies/data/{datas}"), currencyTickerHandler::getCurrencyByDate);

			return nest(
					path("/v1"), currencyApi);
		}

		@Bean
		public CorsWebFilter corsWebFilter() {
			CorsConfiguration corsConfig = new CorsConfiguration()
					.applyPermitDefaultValues();
			corsConfig.addAllowedMethod(HttpMethod.GET);
			corsConfig.addAllowedOrigin("*");
			corsConfig.addAllowedHeader("*");
			corsConfig.addAllowedMethod("*");
			UrlBasedCorsConfigurationSource source =
					new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", corsConfig);

			return new CorsWebFilter(source);
		}



		public static void main(String[] args) {
			SpringApplication.run(WebFluxServer.class);
		}

}

