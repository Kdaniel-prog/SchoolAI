package kiszel.daniel.temalabor.controllers;

import kiszel.daniel.temalabor.models.News;
import kiszel.daniel.temalabor.payload.response.MessageResponse;
import kiszel.daniel.temalabor.repository.NewsRepository;
import kiszel.daniel.temalabor.security.services.NewsService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class NewsController {
    @Autowired
    private NewsService service;
    @Autowired
    NewsRepository newsRepository;

    @GetMapping("/news")
    public List<News> getApartments(){
        return service.getNews();
    }

    @PostMapping("/add_news")
    public ResponseEntity<?> addNews(@Valid @RequestBody JSONObject param){
        JSONObject params = new JSONObject(param);
        News news = new News();
        news.setUser_id(Long.parseLong(String.valueOf(params.get("user_id"))));
        news.setText((String) params.get("text"));
        news.setCreated(LocalDateTime.now());
        news.setUser_name((String) params.get("user_name"));
        newsRepository.save(news);
        return ResponseEntity.ok(new MessageResponse("News created successfully!"));
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
