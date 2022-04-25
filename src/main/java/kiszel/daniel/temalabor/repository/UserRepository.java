package kiszel.daniel.temalabor.repository;


import kiszel.daniel.temalabor.models.Role;
import kiszel.daniel.temalabor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query("SELECT a FROM User a WHERE a = :user")
	User findByUser(@Param("user") User user);

	@Query("SELECT a FROM User a WHERE a.name = :user_name")
	User findByName(@Param("user_name") String user_name);

	List<User> findAll();

	@Query("SELECT a.roles FROM User a WHERE a = :user")
	Role findRole(@Param("user")User user);
}
