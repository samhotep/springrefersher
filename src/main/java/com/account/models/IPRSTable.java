package com.account.models;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class IPRSTable extends User{

    protected IPRSTable() {}

    public IPRSTable(Integer idNumber, String firstName, String middleName, String lastName, String phoneNumber, String country, Date dob) {
        super(idNumber, firstName, middleName, lastName, phoneNumber, country, dob);
    }
}
