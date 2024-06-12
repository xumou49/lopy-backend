package com.lopy.service.biz.impl;

import com.lopy.common.auth.AuthContext;
import com.lopy.common.utils.DataUtil;
import com.lopy.common.utils.StringUtil;
import com.lopy.dao.HistoryKeywordDAO;
import com.lopy.entity.HistoryKeyword;
import com.lopy.service.biz.intf.HistoryKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistoryKeywordServiceImpl implements HistoryKeywordService {

    @Autowired
    private HistoryKeywordDAO historyKeywordDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveKeyword(String keyword) {
        // no need to save keyword if user is not logged in
        long userId = DataUtil.toLong(AuthContext.getUserId());
        if (userId == 0) {
            return;
        }
        HistoryKeyword historyKeyword = new HistoryKeyword();
        historyKeyword.setUserId(userId);
        historyKeyword.setKeyword(StringUtil.trim(keyword));
        historyKeywordDAO.insert(historyKeyword);
    }

    @Override
    public List<String> latestKeywords(Long userId, Integer size) {
        return historyKeywordDAO.selectLatestKeywordByUserId(userId, size);
    }
}
