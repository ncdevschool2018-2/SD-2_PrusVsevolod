package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.Content;
import com.netcracker.edu.fapi.model.SubscriptionViewModel;

import java.util.List;

public interface SubscriptionDataService {

    Content<SubscriptionViewModel> findAll(int page, int size);
    Content<SubscriptionViewModel> findByNameLike(String name, int page, int size);
    Content<SubscriptionViewModel> findByCategoryId(Long id, int page, int size);
    List<SubscriptionViewModel> getAll();
    SubscriptionViewModel getSubscriptionById(Long id);
    List<SubscriptionViewModel>findByOwnerId(Long id);
    SubscriptionViewModel saveSubscription(SubscriptionViewModel subscription);
    void deleteSubscription(Long id);
}
