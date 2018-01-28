package com.community.hsr.testing.weekenddiscount;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WeekendDiscountValidatorTest {

    private WeekendDiscountValidator validator = new WeekendDiscountValidator();

    @Test
    public void isAuthorizedForDiscount() throws Exception {
        validator.initializeWithWeekendNumber(4);

        Assert.assertThat(validator.isAuthorizedForDiscount(DateFactory.createDate(2018, 1, 27, 0, 0, 0)), Is.is(true));

        assertThat(validator.isAuthorizedForDiscount(DateFactory.createDate(2018, 1, 27, 0, 0, 0))).isEqualTo(true);
    }

}