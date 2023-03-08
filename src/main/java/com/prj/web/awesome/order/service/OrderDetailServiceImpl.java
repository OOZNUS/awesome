package com.prj.web.awesome.order.service;

import com.prj.web.awesome.order.dto.OrderDetailDTO;
import com.prj.web.awesome.order.dto.OrderDetailItemDTO;
import com.prj.web.awesome.order.mapper.OrderDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetailItemDTO> findOrderList(int order_code) {
        return orderDetailMapper.findOrderList(order_code);
    }

    @Override
    public void saveCart(OrderDetailDTO orderDetailDTO) {
        orderDetailMapper.saveCart(orderDetailDTO);
    }
}
