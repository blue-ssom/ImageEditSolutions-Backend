package ImageEditSolutions.api_server.repository;

import ImageEditSolutions.api_server.entity.Project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByUploadId(String uploadId);

}
