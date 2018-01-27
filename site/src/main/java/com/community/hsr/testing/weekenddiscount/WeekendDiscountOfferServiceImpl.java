package com.community.hsr.testing.weekenddiscount;

import com.community.hsr.testing.weekenddiscount.ValidatorNotYetInitializedException;
import com.community.core.testing.TestingTime;
import org.broadleafcommerce.core.offer.domain.Offer;
import org.broadleafcommerce.core.offer.service.OfferService;
import org.broadleafcommerce.core.offer.service.OfferServiceImpl;
import org.broadleafcommerce.core.order.domain.Order;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.community.hsr.testing.weekenddiscount.WeekendDiscountValidator;


@Service("weekendOfferService")
public class WeekendDiscountOfferServiceImpl extends OfferServiceImpl implements OfferService {

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    WeekendDiscountValidator validator = new WeekendDiscountValidator();

    public WeekendDiscountOfferServiceImpl() {
        validator.initializeWithWeekendNumber(4);
    }



    @Override
    public List<Offer> buildOfferListForOrder(Order order) {
        System.out.println("Hello WeekendDiscount");


        TestingTime testingTime = em.find(TestingTime.class, new Long(42));
        Date now = testingTime.getTestingTime();
        System.out.println(now);

        List<Offer> remainingOffers = new ArrayList<>();



        try {
            boolean authorizedForDiscount = validator.isAuthorizedForDiscount(now);
            System.out.println(authorizedForDiscount);

            List<Offer> offers = super.buildOfferListForOrder(order);
            if (!authorizedForDiscount) {
                for (Offer offer : offers){
                    if (!offer.getName().equals("WeekendDiscount")){
                        remainingOffers.add(offer);
                    }
                }
            } else {
                remainingOffers = offers;
            }
        } catch (ValidatorNotYetInitializedException e) {
            e.printStackTrace();
        }


        return remainingOffers;
    }
}
