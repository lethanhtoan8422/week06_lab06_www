package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p order by p.publishedAt desc")
    public List<Post> findAllOrderByPublishedAtDesc();
    @Query("select p.id from Post p order by p.id desc limit 1")
    public long getCurrentPostID();
}
