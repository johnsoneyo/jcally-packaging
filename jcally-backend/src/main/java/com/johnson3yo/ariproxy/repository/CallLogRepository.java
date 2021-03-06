/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.repository;

import com.johnson3yo.ariproxy.domainobject.CallLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author johnson3yo
 */
public interface CallLogRepository extends CrudRepository<CallLog, Integer> {
    Page<CallLog> findAll(Pageable pageable);
    CallLog  findByChannelsIgnoreCaseContaining(String channelId);
}
