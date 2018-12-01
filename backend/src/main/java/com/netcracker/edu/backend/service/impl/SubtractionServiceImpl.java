package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.constants.Constants;
import com.netcracker.edu.backend.entity.ActiveSubscription;
import com.netcracker.edu.backend.entity.Customer;
import com.netcracker.edu.backend.entity.Owner;
import com.netcracker.edu.backend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SubtractionServiceImpl implements SubtractionService {

    private static final int CYCLE_TIME = 10000;//В миллисекундах, 1 минута
    private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    @Autowired
    private ActiveSubscriptionService activeSubscriptionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BillingAccountService billingAccountService;
    @Autowired
    private StatusService statusService;

    @Override
    @Scheduled(fixedDelay = CYCLE_TIME)
    //fixedRate выполняется каждую минуту, fixedDelay - выполняется через интервал после завершения работы прошлого треда.
    public void subtract() {
        Iterable<ActiveSubscription> activeSubscriptions = activeSubscriptionService.getAllActiveSubscriptions();
        for (ActiveSubscription subscription : activeSubscriptions) {//Цикл переборки всех активных подписок
            long newEditedDate = System.currentTimeMillis();
            long deltaTime = newEditedDate - subscription.getLastEditDate();//Находится разница во времени
            int amount = (int) deltaTime / CYCLE_TIME; //Находится количество условных единиц которые мы должны вычесть с quantity и умножить на price и вычесть с кошелька.
            log.info("Разница во времени: " + ((Long) deltaTime).toString());
            if (amount > 0) {
                Optional<Customer> customer = customerService.getCustomerById(subscription.getCustomerId());

                if (customer.get().getStatus().getName().equals("valid")) {//Если аккаунт не заблокирован
                    int subtractMoney = amount * subscription.getSubscription().getPrice();
                    Integer balance = customer.get().getBa().getBalance() - subtractMoney;

                        int quantity = subscription.getQuantity() - amount;
                        Owner owner = subscription.getSubscription().getOwner();
                        Integer ownerBalance = owner.getba().getBalance();

                        if (quantity < 0) {//Если у нас сервис не запускался долгое время то кол-во дней может быть отрицательным, в таком случае мы просто меняем дату так как проблема на стороне сервера.
                            subscription.setLastEditDate(newEditedDate);
                            activeSubscriptionService.saveActiveSubscription(subscription);
                        }//Если у нас сервис не запускался долгое время то кол-во дней может быть отрицательным, в таком случае мы просто меняем дату так как проблема на стороне сервера.
                        else if (quantity == 0) {
                            customer.get().getBa().setBalance(balance);
                            log.info("Amount: " + customer.get().getBa().getBalance().toString());

                            ownerBalance += subtractMoney;
                            log.info("Owner amount: " + ownerBalance);

                            owner.getba().setBalance(ownerBalance);
                            billingAccountService.addAmountOnBa(owner.getba());
                            billingAccountService.addAmountOnBa(customer.get().getBa());

                            activeSubscriptionService.deleteActiveSubscriptionById(subscription.getId());
                        } else {
                            subscription.setQuantity(quantity);
                            subscription.setLastEditDate(newEditedDate);
                            customer.get().getBa().setBalance(balance);
                            log.info("Amount: " + customer.get().getBa().getBalance().toString());

                            ownerBalance += subtractMoney;
                            log.info("Owner amount: " + ownerBalance);

                            owner.getba().setBalance(ownerBalance);
                            billingAccountService.addAmountOnBa(owner.getba());

                            billingAccountService.addAmountOnBa(customer.get().getBa());
                            activeSubscriptionService.saveActiveSubscription(subscription);
                        }
                    if (customer.get().getBa().getBalance() < Constants.THRESHOLD) {//Если баланс кошелька привысил какое-то кол-во денег то блокируем пользователя
                        customer.get().setStatus(statusService.getStatusById(Long.valueOf(2)).get());
                        customerService.saveEditedCustomer(customer.get());
                    }
                } else {
                    subscription.setLastEditDate(newEditedDate);
                    activeSubscriptionService.saveActiveSubscription(subscription);
                }

            }
            log.info("------------------------------------");
        }
        log.info("Cycle is done.");
        log.info("------------------------------------");

    }

}
