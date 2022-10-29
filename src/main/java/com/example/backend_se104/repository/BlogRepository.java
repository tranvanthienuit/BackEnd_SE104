package spring.Repository;

import com.example.backend_se104.entity.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

    @Query("select u from Blog u where u.blogId=:blogId")
    Blog findBlogByBlogId(String blogId);
}
