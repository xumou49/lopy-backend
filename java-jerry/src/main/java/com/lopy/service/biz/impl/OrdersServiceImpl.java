package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.order.OrderDTO;
import com.lopy.common.dto.order.OrderListDTO;
import com.lopy.common.dto.payment.PaymentDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.pagination.SearchPage;
import com.lopy.common.query.OrderQuery;
import com.lopy.common.utils.PaginationUtil;
import com.lopy.common.vo.order.OrderPaymentVO;
import com.lopy.common.vo.order.OrderVO;
import com.lopy.common.vo.payment.PaymentVO;
import com.lopy.dao.OrderDAO;
import com.lopy.dao.OrderItemDAO;
import com.lopy.entity.Order;
import com.lopy.entity.OrderItem;
import com.lopy.service.biz.intf.OrderService;
import com.lopy.service.biz.intf.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrderDAO, Order> implements OrderService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderItemDAO orderItemDAO;

    private OrderVO toOrderVO(Order order) {
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        orderVO.setCompleteDate(Objects.isNull(order.getModifyDate()) ? order.getCreateDate() : order.getModifyDate());
        return orderVO;
    }

    @Override
    public List<OrderVO> listByQuery(OrderListDTO orderListDTO) {
        OrderQuery orderQuery = OrderQuery.build(orderListDTO);
        return baseMapper.selectByQuery(orderQuery).stream().map(this::toOrderVO).collect(Collectors.toList());
    }

    @Override
    public PageResult<OrderVO> pageByQuery(OrderListDTO orderListDTO) {
        OrderQuery orderQuery = OrderQuery.build(orderListDTO);
        SearchPage searchPage = orderListDTO.getSearchPage();
        // query with pagination
        Page<Order> page = new Page<>(searchPage.getPage(), searchPage.getPageSize());
        PageResult<Order> pageResult = PaginationUtil.buildPageResult(baseMapper.selectByPageAndQuery(page, orderQuery), searchPage);
        return PaginationUtil.changePageResult(pageResult, this::toOrderVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderPaymentVO createOrder(OrderDTO orderDTO) {
        // conduct the payment first
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setCardId(orderDTO.getCardId());
        paymentDTO.setUserId(orderDTO.getUserId());
        paymentDTO.setItemList(orderDTO.getItemList());
        PaymentVO paymentVO = paymentService.create(paymentDTO);

        // create the order
        Order order = new Order();
        order.setRestaurantId(orderDTO.getRestaurantId());
        order.setUid(UUID.randomUUID().toString());
        order.setPaymentIntentId(paymentVO.getId());
        order.setTotalCost(orderDTO.getTotalCost());
        order.setStatus(CommonConstant.Order.STATUS_UNPAID);
        order.setTaxes(orderDTO.getTotalCost() * CommonConstant.Order.TAX_RATE);
        order.setRestaurantId(orderDTO.getRestaurantId());
        order.setUserId(orderDTO.getUserId());
        baseMapper.insert(order);

        // bind the order item to order & save order items
        List<OrderItem> orderItems = orderDTO.getItemList().stream().map(o -> new OrderItem(order.getId(), o.getName(), o.getQuantity(), o.getPrice())).collect(Collectors.toList());
        orderItemDAO.batchInsert(orderItems);

        return new OrderPaymentVO(paymentVO.getClientSecret());
    }
}