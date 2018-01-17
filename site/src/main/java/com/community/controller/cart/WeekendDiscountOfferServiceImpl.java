package com.community.controller.cart;

import org.broadleafcommerce.core.offer.domain.Offer;
import org.broadleafcommerce.core.offer.service.OfferService;
import org.broadleafcommerce.core.offer.service.OfferServiceImpl;
import org.broadleafcommerce.core.order.domain.Order;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;
import java.util.List;


@Service("weekendOfferService")
public class WeekendDiscountOfferServiceImpl extends OfferServiceImpl implements OfferService {


    @Override
    public List<Offer> buildOfferListForOrder(Order order) {
        System.out.println("Hello WeekendDiscount");
        return super.buildOfferListForOrder(order);
    }
}
