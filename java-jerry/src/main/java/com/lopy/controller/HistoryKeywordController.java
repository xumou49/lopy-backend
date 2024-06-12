package com.lopy.controller;

import com.lopy.common.auth.AuthContext;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.vo.RespVO;
import com.lopy.service.biz.intf.HistoryKeywordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "History Keyword API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/history-keyword")
public class HistoryKeywordController {

    @Autowired
    private HistoryKeywordService historyKeywordService;

    @GetMapping("/latest-keywords")
    public RespVO<List<String>> listOfLatestKeyword() {
        List<String> list = historyKeywordService.latestKeywords(AuthContext.getUserId(), 10);
        return RespVO.ok(list);
    }

    @PostMapping("/save")
    public RespVO<Void> save(@RequestParam String keyword) {
        historyKeywordService.saveKeyword(AuthContext.getUserId(), keyword);
        return RespVO.ok();
    }
}
