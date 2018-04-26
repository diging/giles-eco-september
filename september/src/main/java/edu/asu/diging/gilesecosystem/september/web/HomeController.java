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
import edu.asu.diging.gilesecosystem.september.core.service.IMessageManager;

@Controller
public class HomeController {

    @Autowired
    private IMessageManager manager;

    @Autowired
    private IMessageDbClient dbClient;

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

    @RequestMapping(value = "/datatable")
    public @ResponseBody DataTableData doGet(@RequestParam int draw, @RequestParam int start, @RequestParam String type, @RequestParam int length)
            throws Exception {
        DataTableData dataTableData = new DataTableData();
        List<IMessage> dataTableMessages = null;
        int totalRecords = 0;
        int filteredRecords = 0;
        int offset = start / length;
        
        totalRecords = dbClient.getNumberOfMessages();
        if (type.equals("")) {
            dataTableMessages = manager.getMessages(offset);
            dataTableData.setRecordsFiltered(totalRecords);
        } else {
            dataTableMessages = manager.getMessages(offset, type);
            filteredRecords = manager.getNumberofFilteredMessages(type);
            dataTableData.setRecordsFiltered(filteredRecords);
        }

        dataTableData.setDraw(draw);
        dataTableData.setData(dataTableMessages);
        dataTableData.setRecordsTotal(totalRecords);
        return dataTableData;

    }

}
