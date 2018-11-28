package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.ActiveSubscription;
import com.netcracker.edu.backend.entity.Customer;
import com.netcracker.edu.backend.entity.Owner;
import com.netcracker.edu.backend.service.ActiveSubscriptionService;
import com.netcracker.edu.backend.service.BillingAccountService;
import com.netcracker.edu.backend.service.CustomerService;
import com.netcracker.edu.backend.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SubtractionServiceImpl {

    private static final int CYCLE_TIME = 10000;//В миллисекундах, 1 минута

    @Autowired
    private ActiveSubscriptionService activeSubscriptionService;
    @Autowired
    private CustomerService customerService;
//    @Autowired
//    private OwnerService ownerService;
    @Autowired
    private BillingAccountService billingAccountService;
    @Autowired
    private StatusService statusService;

    private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    @Scheduled(fixedDelay = CYCLE_TIME)
    //fixedRate выполняется каждую минуту, fixedDelay - выполняется через интервал после завершения работы прошлого треда.
    public void Subtract() {
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

                    if (customer.get().getBa().getBalance() < -100) {//Если баланс кошелька привысил какое-то кол-во денег то блокируем пользователя
                        customer.get().setStatus(statusService.getStatusById(Long.valueOf(2)).get());

                        customerService.saveEditedCustomer(customer.get());
                    } else {

                        int quantity = subscription.getQuantity() - amount;

                        Owner owner = subscription.getSubscription().getOwner();
                        Integer ownerBalance = owner.getba().getBalance();

                        if (quantity < 1) {//Если у нас сервис не запускался долгое время то кол-во дней может быть отрицательным, в таком случае надо просто вычесть все оставшиеся дни
                            Integer fullPrice = subscription.getQuantity() * subscription.getSubscription().getPrice();
                            customer.get().getBa().setBalance(customer.get().getBa().getBalance() - fullPrice);
                            log.info("Last amount: " + customer.get().getBa().getBalance().toString());

                            ownerBalance += fullPrice;
                            owner.getba().setBalance(ownerBalance);
                            billingAccountService.addAmountOnBa(owner.getba());

                            log.info("Owner amount: " + ownerBalance);

                            billingAccountService.addAmountOnBa(customer.get().getBa());
                            activeSubscriptionService.deleteActiveSubscriptionById(subscription.getId());
                        } else {
                            subscription.setQuantity(quantity);
                            subscription.setLastEditDate(newEditedDate);
                            customer.get().getBa().setBalance(balance);
                            log.info("Amount: " + customer.get().getBa().getBalance().toString());

                            ownerBalance += subtractMoney;
                            log.info(String.valueOf(subtractMoney));
                            log.info("Owner amount: " + ownerBalance);

                            owner.getba().setBalance(ownerBalance);
                            billingAccountService.addAmountOnBa(owner.getba());

                            billingAccountService.addAmountOnBa(customer.get().getBa());
                            activeSubscriptionService.saveActiveSubscription(subscription);
                        }
                    }
                } else if (customer.get().getBa().getBalance() > -100) {
                    customer.get().setStatus(statusService.getStatusById(Long.valueOf(1)).get());
                    customerService.saveEditedCustomer(customer.get());
                }
            }
        }
        log.info("Cycle is done.");
    }
}
