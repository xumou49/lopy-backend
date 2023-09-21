package com.lopy.common.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResult<T>  {

    /**
     * 当前第几页
     */
    private int number;

    /**
     * 当前页大小
     */
    private int size;

    /**
     * totalPages 总页数
     */
    private int totalPages;

    /**
     * 当前页条数
     */
    private int numberOfElements;

    /**
     * 总数量
     */
    private long totalElements;

    /**
     * 返回当前页数据
     */
    private List<T> content = new LinkedList<>();
}
