package sg.edu.nus.iss.paf_day26_workshopB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class Redirect {
    
    @GetMapping()
    public String redirect(){
        return "redirect:/songs";
    }
    
}
