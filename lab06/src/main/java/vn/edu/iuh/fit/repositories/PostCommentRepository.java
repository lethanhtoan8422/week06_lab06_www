package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.Post;
import vn.edu.iuh.fit.models.PostComment;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    @Query(value = "SELECT *, (SELECT COUNT(*) FROM post_comment WHERE parent_id = pc.id) AS quantityCommentChildren FROM post_comment AS pc WHERE pc.post_id = ?1 AND pc.parent_id IS NULL ", nativeQuery = true)
    public List<Object[]> findByPostId(long id);

    @Query(value = "SELECT *, (SELECT COUNT(*) FROM post_comment WHERE parent_id = pc.id) AS quantityCommentChildren FROM post_comment AS pc WHERE pc.parent_id = ?1", nativeQuery = true)
    public List<Object[]> findByPostIdAndParentID(long parentID);

    @Query("select pc.id from PostComment pc order by pc.id desc limit 1")
    public long getCurrentPostCommentID();
}
