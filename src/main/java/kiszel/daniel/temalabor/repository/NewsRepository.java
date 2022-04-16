package kiszel.daniel.temalabor.repository;

import kiszel.daniel.temalabor.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    public List<News> findAllByOrderByCreatedDesc();
}
