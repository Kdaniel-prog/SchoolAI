package kiszel.daniel.temalabor.controllers;

import kiszel.daniel.temalabor.models.News;
import kiszel.daniel.temalabor.security.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class NewsController {
    @Autowired
    private NewsService service;

    @GetMapping("/news")
    public List<News> getApartments(){
        return service.getNews();
    }
    @PostMapping("/news/addnew")
    public void addApartment(@RequestBody News news){
        service.addNews(news);
    }
    @PutMapping("/news/{id}/edit")
    public void updateApartment(@PathVariable("id") Long id, @RequestBody News news){
        service.updateNews(news);
    }
    @DeleteMapping("/news/{id}/delete")
    public void deleteApartment(@PathVariable("id") Long id){
        service.deleteNews(id);
    }

}
