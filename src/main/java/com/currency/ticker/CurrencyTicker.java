package com.currency.ticker;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table("currency_ticker")
public class CurrencyTicker {
    @Id
    @Column("valiutos_kodas")
    private String valiutos_kodas;
    @Column("pavadinimas")
    private String pavadinimas;
    @Column("santykis")
    private String santykis;
    @Column("data")
    private String data;
}
