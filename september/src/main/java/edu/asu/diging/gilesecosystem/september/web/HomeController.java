package edu.asu.diging.gilesecosystem.september.web;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    
	@RequestMapping(value = "/datatable", method = RequestMethod.POST)
	public List<IMessage> dataTable(Principal principal, Model model, @RequestParam(defaultValue = "0") int page) {
		List<IMessage> messages = manager.getMessages(page);
		
		
		return messages;
	}
    
}
