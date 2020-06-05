package location.controller;

import location.mq.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @Autowired
    Publisher publisher;

    @RequestMapping("/send")
    public String sendMessage(@RequestParam("msg") String msg){
        System.out.println("*****"+msg);
        for(int i =0; i<25;i++){
            publisher.produceMsg(msg);
        }
        return "Successfully Msg Sent";
    }
}
