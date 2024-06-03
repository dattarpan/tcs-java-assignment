package com.tcs.training.orderms.controller;

import java.net.http.HttpHeaders;
import java.util.Optional;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tcs.training.orderms.model.Book;
import com.tcs.training.orderms.model.Order;
import com.tcs.training.orderms.model.User;
import com.tcs.training.orderms.repo.OrderRepo;
import com.tcs.training.orderms.util.ResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class OrderMsController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private RestTemplate restTemplate;
    // private Logger log = LoggerFactory.getLogger(OrderMsController.class);

    @PostMapping("/order/post")
    public ResponseData<Order> addOrder(@RequestBody Order entity) {

        final String bookUrl = "http://book-ms/book/" + entity.getBookId();
        final String userUrl = "http://user-ms/user/" + entity.getUserId();

        Book book = restTemplate.getForObject(bookUrl, Book.class);
        User user = restTemplate.getForObject(userUrl, User.class);

        if (user == null)
            return new ResponseData<Order>(404, "User does not exist.", null);
        if (book == null)
            return new ResponseData<Order>(404, "Book does not exist.", null);

        if (book.getNumberOfCopy() == 0)
            return new ResponseData<Order>(401, "Book is out of stock.", null);

        Example<Order> exampleOrder = Example.of(entity);
        Optional<Order> order = orderRepo.findOne(exampleOrder);
        if (order.isPresent()) {
            return new ResponseData<Order>(409, "Order already exist", entity);
        } else {
            orderRepo.saveAndFlush(entity);

            final String bookUpdateUrl = "http://book-ms/book/patch";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject bookJson = new JSONObject();
            bookJson.put("id", book.getId());
            bookJson.put("numberOfCopy", book.getNumberOfCopy() - 1);
            HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
            Book updatedBook = restTemplate.postForObject(bookUpdateUrl, request, Book.class);

            return new ResponseData<Order>(200, "Order created successfully", entity);
        }
    }

    @GetMapping("/order/{id}")
    public ResponseData<Order> getMethodName(@PathVariable Long id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            return new ResponseData<Order>(200, "Order fetched successfully", order.get());
        } else {
            return new ResponseData<Order>(404, "Order does not exsist in the system", null);
        }
    }

    @PatchMapping("/order/patch")
    public ResponseData<Order> editOrder(@RequestBody Order entity) {
        Optional<Order> orderRow = orderRepo.findById(entity.getId());
        if (orderRow.isPresent()) {
            Order order = orderRow.get();
            order.updateOrder(entity);
            return new ResponseData<Order>(200, "Order updated successfully", orderRepo.save(order));
        } else {
            return new ResponseData<Order>(404, "Order does not exsist in the system", null);
        }
    }

    @DeleteMapping("/order/delete/{id}")
    public ResponseData<Order> deleteOrder(@PathVariable Long id) {
        Optional<Order> deletedOrder = orderRepo.findById(id);
        if (deletedOrder.isPresent()) {
            orderRepo.deleteById(id);
            return new ResponseData<Order>(200, "Order deleted successfully", deletedOrder.get());
        } else {
            return new ResponseData<Order>(404, "Order does not exsist in the system", null);
        }
    }
}
