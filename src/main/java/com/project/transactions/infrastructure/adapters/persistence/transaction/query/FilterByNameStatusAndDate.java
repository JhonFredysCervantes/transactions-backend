package com.project.transactions.infrastructure.adapters.persistence.transaction.query;

import com.project.transactions.domain.model.transaction.FilterTransaction;
import com.project.transactions.infrastructure.adapters.persistence.transaction.entity.TransactionEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * Filter by name, status and creation date of the transactions
 */
public class FilterByNameStatusAndDate implements Specification<TransactionEntity> {
    private final FilterTransaction filterTransaction;

    /**
     * Constructor
     *
     * @param filterTransaction The filter parameters
     */
    public FilterByNameStatusAndDate(FilterTransaction filterTransaction) {
        this.filterTransaction = filterTransaction;
    }

    @Override
    public Predicate toPredicate(Root<TransactionEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> filtersHomologue = new ArrayList<>();

        if (!isNull(filterTransaction.getName())) {
            filtersHomologue.add(criteriaBuilder.like(root.get("name"), filterTransaction.getName().getValue()));
        }

        if (!isNull(filterTransaction.getStatus())) {
            filtersHomologue.add(criteriaBuilder.equal(root.get("status"), filterTransaction.getStatus().name()));
        }

        if (!isNull(filterTransaction.getFromDate())) {
            filtersHomologue.add(criteriaBuilder.between(root.get("createdAt"),
                    Timestamp.from(filterTransaction.getFromDate().getValue().toInstant()),
                    Timestamp.from(filterTransaction.getToDate().getValue().toInstant())));
        }
        return criteriaBuilder.and(filtersHomologue.toArray(Predicate[]::new));
    }
}
