package com.test.project.springbatchsideproject.core.steps.crm;


import com.google.common.collect.ImmutableMap;
import com.test.project.springbatchsideproject.infra.entity.BalanceAndIndexEntity;
import com.test.project.springbatchsideproject.infra.repository.BalanceAndIndexRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.util.Arrays;
import java.util.Map;

public class CRMUpdateReader extends RepositoryItemReader<BalanceAndIndexEntity> {

    private static final String QUERY_METHOD = "findAllByAccountOpeningDate";
    private static final int ITEMS_PER_PAGE = 10;
    private static final String SORT_COLUMN = "id";
    private static final Map<String, Sort.Direction> sortOrder = ImmutableMap.of(SORT_COLUMN, Sort.Direction.ASC);

    private final BalanceAndIndexRepository balanceAndIndexRepository;
    private Date dateToday;

    public CRMUpdateReader(BalanceAndIndexRepository balanceAndIndexRepository, Date dateToday) {
        this.balanceAndIndexRepository = balanceAndIndexRepository;
        this.dateToday = dateToday;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRepository(balanceAndIndexRepository);
        setMethodName(QUERY_METHOD);
        setPageSize(ITEMS_PER_PAGE);
        setSort(sortOrder);
        setArguments(Arrays.asList(dateToday));
        super.afterPropertiesSet();
    }
}


