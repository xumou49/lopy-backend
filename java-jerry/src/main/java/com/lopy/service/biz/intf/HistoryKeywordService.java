package com.lopy.service.biz.intf;

import java.util.List;

public interface HistoryKeywordService {

    void saveKeyword(String keyword);

    List<String> latestKeywords(Long userId, Integer size);
}
