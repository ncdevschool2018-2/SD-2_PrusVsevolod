package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.ActiveSubscription;
import com.netcracker.edu.backend.entity.Customer;
import com.netcracker.edu.backend.service.ActiveSubscriptionService;
import com.netcracker.edu.backend.service.BillingAccountService;
import com.netcracker.edu.backend.service.CustomerService;
import com.netcracker.edu.backend.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class SubtractionServiceImpl {

    private static final int CYCLE_TIME = 60000*4;//В миллисекундах, 1 минута

    @Autowired
    private ActiveSubscriptionService activeSubscriptionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BillingAccountService billingAccountService;
    @Autowired
    private StatusService statusService;

    private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    @Scheduled(fixedRate = CYCLE_TIME)
    //Выполняется каждую минуту, fixedDelay - выполняется через интервал после завершения работы прошлого треда.
    public void Subtract() {
        Iterable<ActiveSubscription> activeSubscriptions = activeSubscriptionService.getAllActiveSubscriptions();
        for (ActiveSubscription subscription : activeSubscriptions) {//Цикл переборки всех активных подписок
            long deltaTime = System.currentTimeMillis()+1000 - subscription.getActivationDate().getTime();//Находится разница во времени (1000 - погрешность, почему-то иногда время между циклами бывает и 19500 ms)
            int amount = (int) deltaTime / CYCLE_TIME; //Находится количество условных единиц которые мы должны вычесть с quantity и умножить на price и вычесть с кошелька.
            log.info("Разница во времени: " + ((Long)deltaTime).toString());
            if (amount > 0) {
                Optional<Customer> customer = customerService.getCustomerById(subscription.getCustomerId());
                if (customer.get().getStatus().getStatus().equals("valid")) {//Если аккаунт не заблокирован
                    int subtractMoney = amount * subscription.getSubscription().getPrice();
                    Integer balance = customer.get().getBa().getBalance() - subtractMoney;

                    if (customer.get().getBa().getBalance() < -100) {//Если баланс кошелька привысил какое-то кол-во денег то блокируем пользователя
                        customer.get().setStatus(statusService.getStatusById(Long.valueOf(2)).get());

                        customerService.saveEditedCustomer(customer.get());
                    } else {

                        Integer quantity = subscription.getQuantity() - amount;

                        if (quantity < 1) {//Если у нас сервис не запускался долгое время то кол-во дней может быть отрицательным, в таком случае надо просто вычесть все оставшиеся дни
                            customer.get().getBa().setBalance(customer.get().getBa().getBalance() - subscription.getQuantity() * subscription.getSubscription().getPrice());
                            log.info(customer.get().getBa().getBalance().toString());

                            billingAccountService.addAmountOnBa(customer.get().getBa());
                            activeSubscriptionService.deleteActiveSubscriptionById(subscription.getId());
                        } else {
                            subscription.setQuantity(quantity);
                            subscription.setActivationDate(new Date());

                            customer.get().getBa().setBalance(balance);
                            log.info("Amount: " + customer.get().getBa().getBalance().toString());
                            billingAccountService.addAmountOnBa(customer.get().getBa());
                            activeSubscriptionService.saveActiveSubscription(subscription);
                        }
                    }
                } else if(customer.get().getBa().getBalance() > -100){
                    customer.get().setStatus(statusService.getStatusById(Long.valueOf(1)).get());
                    customerService.saveEditedCustomer(customer.get());
                }
            }
        }
        log.info("Cycle is done.");
    }
}
