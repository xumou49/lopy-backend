package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.dto.order.OrderItemListDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.pagination.SearchPage;
import com.lopy.common.query.OrderItemQuery;
import com.lopy.common.utils.PaginationUtil;
import com.lopy.common.vo.order.OrderItemVO;
import com.lopy.dao.OrderItemDAO;
import com.lopy.entity.OrderItem;
import com.lopy.service.biz.intf.OrderItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDAO, OrderItem> implements OrderItemService {

    private OrderItemVO toOrderItemVO(OrderItem orderItem) {
        OrderItemVO orderItemVO = new OrderItemVO();
        BeanUtils.copyProperties(orderItem, orderItemVO);
        return orderItemVO;
    }

    @Override
    public List<OrderItemVO> listByQuery(OrderItemListDTO orderItemListDTO) {
        OrderItemQuery orderItemQuery = OrderItemQuery.build(orderItemListDTO);
        return baseMapper.selectByQuery(orderItemQuery).stream().map(this::toOrderItemVO).collect(Collectors.toList());
    }

    @Override
    public PageResult<OrderItemVO> pageByQuery(OrderItemListDTO orderItemListDTO) {
        OrderItemQuery orderItemQuery = OrderItemQuery.build(orderItemListDTO);
        SearchPage searchPage = orderItemListDTO.getSearchPage();
        // query with pagination
        Page<OrderItem> page = new Page<>(searchPage.getPage(), searchPage.getPageSize());
        PageResult<OrderItem> pageResult = PaginationUtil.buildPageResult(baseMapper.selectByPageAndQuery(page, orderItemQuery), searchPage);
        return PaginationUtil.changePageResult(pageResult, this::toOrderItemVO);
    }
}