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

import edu.asu.diging.gilesecosystem.september.core.db.ISystemMessageDbClient;
import edu.asu.diging.gilesecosystem.september.core.model.ISystemMessage;
import edu.asu.diging.gilesecosystem.september.core.service.ISystemMessageManager;

@Controller
public class HomeController {

    @Autowired
    private ISystemMessageManager messageManager;

    @Autowired
    private ISystemMessageDbClient dbClient;
    
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/")
    public String home(Principal principal, Model model, @RequestParam(defaultValue = "0") int page) {
        if (principal == null || principal.getName() == null || principal.getName().equals("anonymousUser")) {
            return "home";
        }

        List<ISystemMessage> messages = messageManager.getMessages(page);
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
        
        List<ISystemMessage> dataTableMessages = null;
        int totalRecords = dbClient.getNumberOfMessages();
        if (type.trim().isEmpty()) {
            dataTableMessages = messageManager.getMessages(offset);
            dataTableData.setRecordsFiltered(totalRecords);
        } else {
            dataTableMessages = messageManager.getMessages(offset, type);
            dataTableData.setRecordsFiltered(messageManager.getNumberOfFilteredMessages(type));
        }

        dataTableMessages.forEach(m -> m.setApplicationId(messageSource.getMessage("appname." + m.getApplicationId(), null, locale)));
        dataTableData.setDraw(draw);
        dataTableData.setSystemMessageData(dataTableMessages);
        dataTableData.setRecordsTotal(totalRecords);
        return dataTableData;

    }

}
