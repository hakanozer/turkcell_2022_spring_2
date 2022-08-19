package com.works.props;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Currency {

    private String currencyName;
    private String forexBuying;
    private String forexSelling;

}
