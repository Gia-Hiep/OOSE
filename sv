package com.app.service;

import com.app.dao.CustomerDAO;
import com.app.dto.Customer;

import java.util.List;

public class T002Service {

    public static final int PAGE_SIZE = 15;

    private final CustomerDAO customerDAO;

    public T002Service(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    // Search theo điều kiện input trên màn hình (Customer Name, Sex, Birthday From/To)
    public SearchResult search(String loginUserName,
                               String customerName,
                               String sex,
                               String birthdayFrom,
                               String birthdayTo,
                               int page) {

        // Null-safe + trim (đúng kiểu training)
        customerName = (customerName == null) ? "" : customerName.trim();
        sex = (sex == null) ? "" : sex.trim(); // 0/1 theo spec
        birthdayFrom = (birthdayFrom == null) ? "" : birthdayFrom.trim();
        birthdayTo = (birthdayTo == null) ? "" : birthdayTo.trim();

        // Page normalize
        if (page <= 0) page = 1;

        // 1) Count tổng record theo điều kiện
        int total = customerDAO.countSearch(customerName, sex, birthdayFrom, birthdayTo);

        // 2) Tính lastPage
        int lastPage = (total == 0) ? 1 : (int) Math.ceil(total * 1.0 / PAGE_SIZE);

        // 3) Clamp page
        if (page > lastPage) page = lastPage;

        // 4) Lấy data theo trang (max 15)
        int offset = (page - 1) * PAGE_SIZE;
        List<Customer> rows = customerDAO.search(customerName, sex, birthdayFrom, birthdayTo, offset, PAGE_SIZE);

        // 5) Disable buttons theo spec 4.1/4.2
        boolean disFirst;
        boolean disPrev;
        boolean disNext;
        boolean disLast;
        boolean disDelete;

        if (total == 0) {
            disFirst = true; disPrev = true; disNext = true; disLast = true;
            disDelete = true;
        } else if (total <= PAGE_SIZE) {
            disFirst = true; disPrev = true; disNext = true; disLast = true;
            disDelete = false; // rule tick checkbox xử lý sau
        } else {
            disFirst = (page == 1);
            disPrev  = (page == 1);
            disNext  = (page == lastPage);
            disLast  = (page == lastPage);
            disDelete = false;
        }

        // 6) Return SearchResult
        SearchResult r = new SearchResult();
        r.setLoginUserName(loginUserName);

        // reflect conditions back to 화면
        r.setCustomerName(customerName);
        r.setSex(sex);
        r.setBirthdayFrom(birthdayFrom);
        r.setBirthdayTo(birthdayTo);

        // list data
        r.setRows(rows);

        // paging
        r.setTotal(total);
        r.setPage(page);
        r.setLastPage(lastPage);

        // disable flags
        r.setDisFirst(disFirst);
        r.setDisPrev(disPrev);
        r.setDisNext(disNext);
        r.setDisLast(disLast);
        r.setDisDelete(disDelete);

        return r;
    }
}
