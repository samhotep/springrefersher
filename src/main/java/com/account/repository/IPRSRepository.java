package com.account.repository;

import com.account.models.IPRSTable;
import org.springframework.data.repository.CrudRepository;
import java.sql.Date;

public interface IPRSRepository extends CrudRepository<IPRSTable, Long> {

    IPRSTable findByIdNumber(Integer idNumber);
    IPRSTable findByIdNumberAndFirstName(Integer idNumber, String firstName);
    IPRSTable findByIdNumberAndMiddleName(Integer idNumber, String lastName);
    IPRSTable findByIdNumberAndLastName(Integer idNumber, String middleName);
    IPRSTable findByIdNumberAndPhoneNumber(Integer idNumber, String phoneNumber);
    IPRSTable findByIdNumberAndCountry(Integer idNumber, String country);
    IPRSTable findByIdNumberAndDob(Integer idNumber, Date dob);

    IPRSTable findById(long id);
}

