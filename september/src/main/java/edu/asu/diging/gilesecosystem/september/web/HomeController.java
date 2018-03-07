package edu.asu.diging.gilesecosystem.september.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.asu.diging.gilesecosystem.september.core.db.IMessageDbClient;
import edu.asu.diging.gilesecosystem.september.core.model.IMessage;
import edu.asu.diging.gilesecosystem.september.core.model.impl.DataTableData;
import edu.asu.diging.gilesecosystem.september.core.service.IMessageManager;

@Controller
public class HomeController {
    
    @Autowired
    private IMessageManager manager;
    
    @Autowired
    private IMessageDbClient client;

    @RequestMapping(value = "/")
    public String home(Principal principal, Model model, @RequestParam(defaultValue = "0") int page) {
        if (principal == null || principal.getName() == null || principal.getName().equals("anonymousUser")) {
            return "home";
        }
        
        List<IMessage> messages = manager.getMessages(page);
        model.addAttribute("messages", messages);
        model.addAttribute("totalPages", manager.getNumberOfPages());
        model.addAttribute("currentPageValue", page);
        System.out.println("Messages Size: "+messages.size());
        return "home";
    }
   
    @RequestMapping(value = "/datatable")
	public @ResponseBody DataTableData  doGet(@RequestParam int draw,
			@RequestParam int start, @RequestParam String type
            ) throws Exception {
    		System.out.println("Type: "+type);
    		DataTableData dataTableData = new DataTableData();
    		List<IMessage> dataTableMessages = null;
    		int totalRecords = 0;
    		int filteredRecords = 0;
    		int offset=start/10;
    		if(type.equals(""))
    		{
			dataTableMessages = manager.getMessages(offset);
			totalRecords = client.getNumberOfMessages();
			dataTableData.setrecordsFiltered(totalRecords);
    		}
    	else
    		{
    			dataTableMessages = manager.getMessages(offset, type);
    			totalRecords = client.getNumberOfMessages();
    			filteredRecords= client.getNumberOfFilteredMessages(type);
    			dataTableData.setrecordsFiltered(filteredRecords);
    		} 
    		
    		dataTableData.setdraw(draw);
		dataTableData.setdata(dataTableMessages);
		dataTableData.setrecordsTotal(totalRecords);
		return dataTableData;
		
	}
    
}
