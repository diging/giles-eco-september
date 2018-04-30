package edu.asu.diging.gilesecosystem.september.web;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
    private IMessageManager messageManager;

    @Autowired
    private IMessageDbClient dbClient;
    
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/")
    public String home(Principal principal, Model model, @RequestParam(defaultValue = "0") int page) {
        if (principal == null || principal.getName() == null || principal.getName().equals("anonymousUser")) {
            return "home";
        }

        List<IMessage> messages = messageManager.getMessages(page);
        model.addAttribute("messages", messages);
        model.addAttribute("totalPages", messageManager.getNumberOfPages());
        model.addAttribute("currentPageValue", page);
        model.addAttribute("pageSize", messageManager.getDefaultPageSize());
        return "home";
    }

    @RequestMapping(value = "/admin/messages")
    public @ResponseBody DataTableData doGet(@RequestParam int draw, @RequestParam int start, @RequestParam String type, Locale locale)
            throws Exception {
        DataTableData dataTableData = new DataTableData();
        dataTableData.setPageSize(messageManager.getDefaultPageSize());
        int offset = start / messageManager.getDefaultPageSize();
        
        List<IMessage> dataTableMessages = null;
        int totalRecords = dbClient.getNumberOfMessages();
        if (type.trim().isEmpty()) {
            dataTableMessages = messageManager.getMessages(offset);
            dataTableData.setRecordsFiltered(totalRecords);
        } else {
            dataTableMessages = messageManager.getMessages(offset, type);
            dataTableData.setRecordsFiltered(messageManager.getNumberofFilteredMessages(type));
        }

        dataTableMessages.forEach(m -> m.setApplicationId(messageSource.getMessage("appname." + m.getApplicationId(), null, locale)));
        dataTableData.setDraw(draw);
        dataTableData.setData(dataTableMessages);
        dataTableData.setRecordsTotal(totalRecords);
        return dataTableData;

    }

}
