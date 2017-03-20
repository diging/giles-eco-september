package edu.asu.diging.gilesecosystem.september.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.diging.gilesecosystem.september.core.model.IMessage;
import edu.asu.diging.gilesecosystem.september.core.service.IMessageManager;

@Controller
public class HomeController {
    
    @Autowired
    private IMessageManager manager;

    @RequestMapping(value = "/")
    public String home(Principal principal, Model model) {
        
        if (principal == null || principal.getName() == null || principal.getName().equals("anonymousUser")) {
            return "home";
        }
        
        List<IMessage> messages = manager.getAllMessages();
        model.addAttribute("messages", messages);
        
        return "home";
    }
}
