package com.test.project.springbatchsideproject.core.steps.crm;


import com.test.project.springbatchsideproject.core.model.RefereeIdentifierDTO;
import com.test.project.springbatchsideproject.core.util.HashingUtil;
import com.test.project.springbatchsideproject.infra.entity.BalanceAndIndexEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class CRMUpdateWriter implements ItemWriter<BalanceAndIndexEntity> {



    @Override
    public void write(List<? extends BalanceAndIndexEntity> items) throws Exception {
        log.info("[START] CRMUpdateWriter.write: {}", items.size());

        if (!items.isEmpty()) {

            log.info("[PROCESSING] CRM Table update");

            for (BalanceAndIndexEntity refereeDetails : items) {
                RefereeIdentifierDTO refereeIdentifierDTO = mapBalanceAndIndexToRefereeDTO(refereeDetails);
                System.out.println(refereeIdentifierDTO);

            }
        }
        log.info("[END] CRMUpdateWriter.write");
    }

    private RefereeIdentifierDTO mapBalanceAndIndexToRefereeDTO(BalanceAndIndexEntity refereeDetails) {
        return RefereeIdentifierDTO.builder()
                .CIF(refereeDetails.getCIF())
                .hashedMobileNumber(HashingUtil.hashToSha256(refereeDetails.getMobileNumber()))
                .build();
    }

}
