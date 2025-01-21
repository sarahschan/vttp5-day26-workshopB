package sg.edu.nus.iss.paf_day26_workshopB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.paf_day26_workshopB.model.Song;
import sg.edu.nus.iss.paf_day26_workshopB.service.SongsService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/songs")
public class SongsController {
    
    @Autowired
    SongsService songsService;


    @GetMapping("")
    public String searchPage(Model model) {

        List<Integer> years = songsService.getYears();
        model.addAttribute("years", years);

        return "searchPage";
    }

    @GetMapping("/search")
    public String getMethodName(@RequestParam Integer year, Model model) {
        
        List<Song> songs = songsService.getSongsByYear(year);

        model.addAttribute("year", year);
        model.addAttribute("songs", songs);
        
        return "resultsPage";
    }
    
}
