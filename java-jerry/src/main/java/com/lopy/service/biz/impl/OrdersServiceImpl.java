package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.dto.order.OrderDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.pagination.SearchPage;
import com.lopy.common.query.OrderQuery;
import com.lopy.common.utils.PaginationUtil;
import com.lopy.common.vo.order.OrderVO;
import com.lopy.dao.OrderDAO;
import com.lopy.entity.Order;
import com.lopy.service.biz.intf.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrderDAO, Order> implements OrderService {

    private OrderVO toOrderVO(Order order) {
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        orderVO.setCompleteDate(order.getModifyDate());
        return orderVO;
    }

    @Override
    public List<OrderVO> listByQuery(OrderDTO orderDTO) {
        OrderQuery orderQuery = OrderQuery.build(orderDTO);
        return baseMapper.selectByQuery(orderQuery).stream().map(this::toOrderVO).collect(Collectors.toList());
    }

    @Override
    public PageResult<OrderVO> pageByQuery(OrderDTO orderDTO) {
        OrderQuery orderQuery = OrderQuery.build(orderDTO);
        SearchPage searchPage = orderDTO.getSearchPage();
        // query with pagination
        Page<Order> page = new Page<>(searchPage.getPage(), searchPage.getPageSize());
        PageResult<Order> pageResult = PaginationUtil.buildPageResult(baseMapper.selectByPageAndQuery(page, orderQuery), searchPage);
        return PaginationUtil.changePageResult(pageResult, this::toOrderVO);
    }
}