package kiszel.daniel.temalabor.security.services;

import kiszel.daniel.temalabor.models.News;
import kiszel.daniel.temalabor.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public List<News> getNews(){
        return newsRepository.findAllByOrderByCreatedAsc();
    }
    public void addNews(News news){
        newsRepository.save(news);
    }
    public void editNews(News news){
        newsRepository.save(news);
    }
    public void deleteNews(Long id){
        newsRepository.deleteById(id);
    }
}
