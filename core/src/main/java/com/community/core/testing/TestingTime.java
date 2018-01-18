package com.community.core.testing;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BLC_TESTING_TIME")
public class TestingTime {

    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    protected Long id;

    @Column(name = "TESTING_TIME")
    protected Date testingTime;

    public Date getTestingTime() {
        return testingTime;
    }
}
