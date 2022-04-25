package kiszel.daniel.temalabor.repository;


import kiszel.daniel.temalabor.models.ERole;
import kiszel.daniel.temalabor.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);

	List<Role> findAll();

}
