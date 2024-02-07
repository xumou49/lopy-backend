package com.lopy.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.pagination.SearchPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class PaginationUtil {

    private PaginationUtil() {}

    /**
     * @description construct page result instance by IPage
     * @author Dex
     * @date 2023/09/21
     */
    public static <S> PageResult<S> buildPageResult(IPage<S> page, SearchPage searchPage) {
        PageResult<S> pageResult = new PageResult<>();
        pageResult.setContent(page.getRecords());
        pageResult.setNumber((int) page.getCurrent() - 1);
        pageResult.setNumberOfElements(page.getRecords().size());
        pageResult.setSize(searchPage.getPageSize());
        pageResult.setTotalElements(page.getTotal());
        pageResult.setTotalPages((int) page.getPages());
        return pageResult;
    }

    /**
     * @description change page result content type
     * @author Dex
     * @date 2023/09/21
     */
    public static <T, S> PageResult<S> changePageResult(PageResult<T> data, Function<T, S> converterFunc) {
        PageResult<S> result = new PageResult<>();
        BeanUtils.copyProperties(data, result, "content");
        List<S> content = new LinkedList<>();
        data.getContent().forEach(item -> {
            try {
                content.add(converterFunc.apply(item));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
        result.setContent(content);
        return result;
    }
}
