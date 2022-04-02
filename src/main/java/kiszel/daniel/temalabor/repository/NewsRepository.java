package kiszel.daniel.temalabor.repository;

import kiszel.daniel.temalabor.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
