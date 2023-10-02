package com.api.movementsaccount.repository.repositorys.impl;

import com.api.movementsaccount.model.Movement;
import com.api.movementsaccount.repository.repositorys.ReportMovementRepository;
import com.api.movementsaccount.service.dto.PagingDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class MovementByClientAndDateAndPageImpl implements ReportMovementRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Page<Movement> getMovementByClientAndDateAndPage(LocalDate dateInit, LocalDate dateFin, PagingDto pagingDto) {
        Pageable pageable;

        String sql = "Select m from Movement m";
        String orderSql = " ORDER BY m.date DESC";
        String countSql = "Select count(m) from Movement m";
        String filter = "";

        filter = filterIdentification(pagingDto ,filter);
        filter = filterDate(dateInit, dateFin, filter);

        if (!filter.isEmpty()) {
            sql += " Where" + filter + orderSql;
            countSql += " Where" + filter;
        } else {
            sql += orderSql;
        }

        Query query = entityManager.createQuery(sql);
        Query queryCount = entityManager.createQuery(countSql);

        query.setMaxResults(pagingDto.getSize());
        query.setFirstResult((pagingDto.getPage()) * pagingDto.getSize());

        if (pagingDto.getFilter() != null && !pagingDto.getFilter().equals("")) {
            query.setParameter("xIdentification", pagingDto.getFilter());
            queryCount.setParameter("xIdentification", pagingDto.getFilter());
        }

        if (dateInit != null && dateFin != null) {
            query.setParameter("xdateS", dateInit.atStartOfDay());
            queryCount.setParameter("xdateS", dateInit.atStartOfDay());

            query.setParameter("xdateE", dateFin.plusDays(1).atStartOfDay());
            queryCount.setParameter("xdateE", dateFin.plusDays(1).atStartOfDay());
        }

        long count = (long) queryCount.getSingleResult();
        pageable = PageRequest.of(pagingDto.getPage(), pagingDto.getSize());

        return new PageImpl<Movement>(query.getResultList(), pageable, count);

    }
    private String filterIdentification(PagingDto pagingDto, String filter) {
        if (pagingDto.getFilter() != null && !pagingDto.getFilter().equals("")) {
            filter +=(validationAndWhere(filter)+" (m.account.customer.person.identification = :xIdentification)");
        }
        return filter;
    }
    private String filterDate(LocalDate dateInit, LocalDate dateFin, String filter) {
        if (dateInit != null && dateFin != null) {
            filter +=(validationAndWhere(filter)+" (m.date BETWEEN :xdateS AND :xdateE)");
        }
        return filter;
    }
    private static String validationAndWhere(String sql) {
        return sql.contains(":")?" AND":"";
    }
}
