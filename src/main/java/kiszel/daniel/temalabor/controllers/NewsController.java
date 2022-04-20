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
    public void addNew(@RequestBody News news){
        service.addNews(news);
    }
    @PutMapping("/news/{id}/edit")
    public void editNews(@PathVariable("id") Long id, @RequestBody News news){
        service.editNews(news);
    }
    @DeleteMapping("/news/{id}/delete")
    public void deleteNew(@PathVariable("id") Long id){
        service.deleteNews(id);
    }

}
