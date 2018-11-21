package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.ActiveSubscriptionModel;

import java.util.List;

public interface ActiveSubscriptionDataService {
    List<ActiveSubscriptionModel> saveActiveSubscriptions(List<ActiveSubscriptionModel> activeSubscriptionModel);
    Iterable<ActiveSubscriptionModel> getASByCustomerId(Long id);
    ActiveSubscriptionModel getActiveSubscriptionById(Long id);
    void deleteActiveSubscriptionById(Long id);
}
