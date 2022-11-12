package spring.Repository;

import com.example.backend_se104.entity.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

    @Query("select u from Blog u where u.blogId=:blogId")
    Blog findBlogByBlogId(String blogId);
}
