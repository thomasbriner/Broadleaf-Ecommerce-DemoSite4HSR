package com.community.controller.cart;

import com.community.core.testing.TestingTime;
import org.broadleafcommerce.core.offer.domain.Offer;
import org.broadleafcommerce.core.offer.service.OfferService;
import org.broadleafcommerce.core.offer.service.OfferServiceImpl;
import org.broadleafcommerce.core.order.domain.Order;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service("weekendOfferService")
public class WeekendDiscountOfferServiceImpl extends OfferServiceImpl implements OfferService {

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;


    @Override
    public List<Offer> buildOfferListForOrder(Order order) {
        System.out.println("Hello WeekendDiscount");


        TestingTime testingTime = em.find(TestingTime.class, new Long(42));
        System.out.println(testingTime.getTestingTime());

        return super.buildOfferListForOrder(order);
    }
}
