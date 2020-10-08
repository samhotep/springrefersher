package com.account.repository;

import com.account.models.IPRSUser;
import org.springframework.data.repository.CrudRepository;
import java.sql.Date;

public interface IPRSRepository extends CrudRepository<IPRSUser, Long> {

    IPRSUser findByIdNumber(Integer idNumber);
    IPRSUser findByIdNumberAndFirstName(Integer idNumber, String firstName);
    IPRSUser findByIdNumberAndMiddleName(Integer idNumber, String lastName);
    IPRSUser findByIdNumberAndLastName(Integer idNumber, String middleName);
    IPRSUser findByIdNumberAndPhoneNumber(Integer idNumber, String phoneNumber);
    IPRSUser findByIdNumberAndCountry(Integer idNumber, String country);
    IPRSUser findByIdNumberAndDob(Integer idNumber, Date dob);

    IPRSUser findById(long id);
}

