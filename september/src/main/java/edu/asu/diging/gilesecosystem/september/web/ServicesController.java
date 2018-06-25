package edu.asu.diging.gilesecosystem.september.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.diging.gilesecosystem.september.core.model.IAppGroup;
import edu.asu.diging.gilesecosystem.september.core.service.IServiceDiscoverer;

@Controller
public class ServicesController {
    
    @Autowired
    private IServiceDiscoverer discoverer;

    @RequestMapping(value="/admin/services")
    public String showServices(Model model) {
        List<IAppGroup> groups = discoverer.checkServices();
        model.addAttribute("groups", groups);
        
        return "admin/services";
    }
}
