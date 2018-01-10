package edu.asu.diging.gilesecosystem.september.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.diging.gilesecosystem.september.core.model.IMessage;
import edu.asu.diging.gilesecosystem.september.core.service.IMessageManager;

@Controller
public class HomeController {
    
    @Autowired
    private IMessageManager manager;

    @RequestMapping(value = "/")
    public String home(Principal principal, Model model, @RequestParam(defaultValue = "0") int page) {
        if (principal == null || principal.getName() == null || principal.getName().equals("anonymousUser")) {
            return "home";
        }
        
        List<IMessage> messages = manager.getMessages(page);
        model.addAttribute("messages", messages);
        model.addAttribute("totalPages", manager.getNumberOfPages());
        model.addAttribute("currentPageValue", page);
        
        return "home";
    }
    
 public Map initForm(Model model) {
    //filterMessage member = new filterMessage();
    //model.addAttribute("member", member);
	 Map messageTypeData = new HashMap();
    List<String> messageTypes = new ArrayList<String>();
    messageTypes.add("Error");
    messageTypes.add("Info");
    messageTypes.add("Warning");
    model.addAttribute("messageTypes", messageTypes);
	return messageTypeData;
  }
    
}
