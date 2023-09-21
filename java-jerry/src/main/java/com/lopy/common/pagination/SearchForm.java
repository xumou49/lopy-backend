package com.lopy.common.pagination;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchForm {

    protected SearchPage searchPage = new SearchPage();
    protected String keyWord;
}
