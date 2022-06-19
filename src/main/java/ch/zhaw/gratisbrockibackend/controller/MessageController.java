package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.domain.Message;
import ch.zhaw.gratisbrockibackend.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/messages")
@RestController
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public List<Message> getItems(){
        return messageService.getMessages();
    }

}
