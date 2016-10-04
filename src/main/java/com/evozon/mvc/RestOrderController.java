package com.evozon.mvc;

import com.evozon.domain.Orders;
import com.evozon.domain.dtos.OrderRestDTO;
import com.evozon.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


//@Controller
//@RequestMapping(value = "/rest/order")
@RestController(value = "/rest/order")
public class RestOrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseBody
    public String updateOrderStatus(@RequestBody JsonObject orderInfo, HttpServletResponse response) {

        OrderRestDTO orderDTO = new Gson().fromJson(orderInfo, OrderRestDTO.class);
        Orders order =  orderService.getOrderById(orderDTO.getOrdersId());
        String status = orderDTO.getStatus();
        if(order != null){
            if(status.equals("delivered")||status.equals("rejected")||status.equals("processing")) {
                order.setStatus(orderDTO.getStatus());
                orderService.updateOrder(order);
                response.setStatus(200);
                return "Order successfully updated !";
            }
            response.setStatus(400);
            return "Invalid status ! Please select one of the following:\"processing\", \"delivered\",\"rejected\" !";

        }
            response.setStatus(404);
            return "The order with this id does not exists !";

    }

    @RequestMapping(value = "/*", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseBody
    public String p( HttpServletResponse response) {

        response.setStatus(500);
        return "An unexpected condition was encountered  !";

    }


}
