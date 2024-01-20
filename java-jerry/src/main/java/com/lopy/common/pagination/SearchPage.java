package com.lopy.common.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPage {

    private String keyword;
    private int page = 1;
    private int pageSize = 5;
    private int desc;
    private String sort = "";

}
