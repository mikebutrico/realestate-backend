package com.michaelbutrico.RealestateBackEnd.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Rent {
    private double amount;

    @OneToOne
    private Property property;
    private Set<Attachment> attachments;
}
