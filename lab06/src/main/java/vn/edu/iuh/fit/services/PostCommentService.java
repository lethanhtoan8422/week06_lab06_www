package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.iuh.fit.models.PostComment;
import vn.edu.iuh.fit.models.User;
import vn.edu.iuh.fit.repositories.PostCommentRepository;
import vn.edu.iuh.fit.repositories.UserRepository;
import vn.edu.iuh.fit.response.ResponsePostComments;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<PostComment> findByID(long id){
        return postCommentRepository.findById(id);
    }
    public List<ResponsePostComments> getPostCommentsByPostID(long id){
        List<ResponsePostComments> responsePostComments = new ArrayList<>();
        for(Object[] o : postCommentRepository.findByPostId(id)){
            ResponsePostComments responsePostComment = new ResponsePostComments((Boolean) o[0], o[1]+"", (Long) o[2], (Long) o[3] == null ? 0 : (Long) o[3],(Long)o[4],o[5]+"",
            userRepository.findById((Long)o[6]).get(),(String)o[7],(String)o[8],(Long) o[9]);
            responsePostComments.add(responsePostComment);
        }
        return responsePostComments;
    }

    public List<ResponsePostComments> getPostCommentsByPostIDAndParentID(long parentID){
        List<ResponsePostComments> responsePostComments = new ArrayList<>();
        for(Object[] o : postCommentRepository.findByPostIdAndParentID(parentID)){
            ResponsePostComments responsePostComment = new ResponsePostComments((Boolean) o[0], o[1]+"", (Long) o[2], (Long) o[3] == null ? 0 : (Long) o[3],(Long)o[4],o[5]+"",
                    userRepository.findById((Long)o[6]).get(),(String)o[7],(String)o[8],(Long) o[9]);
            responsePostComments.add(responsePostComment);
        }
        return responsePostComments;
    }

    public long getCurrentPostCommentID(){
        return postCommentRepository.getCurrentPostCommentID();
    }

    public boolean createPostComment(PostComment postComment){
        postCommentRepository.save(postComment);
        return true;
    }
}
