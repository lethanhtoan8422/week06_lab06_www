import axios from "axios";
import { useEffect, useState } from "react";
import "./home.scss";
import Comment from "../comment/Comment";
import { useLocation, useNavigate } from "react-router-dom";

const Home = () => {
  const [postDatas, setPostDatas] = useState([]);
  const [postComments, setPostComments] = useState([]);
  const [txtComment, setTxtComment] = useState([]);

  let navigate = useNavigate()
  let location = useLocation()


  useEffect(() => {
    let apiGetPostDatas = async () => {
      let datas = await axios.get("http://localhost:8080/post");
      setPostDatas(datas.data.datas);
      setTxtComment(datas.data.datas.map(dt => ({postID : dt.id, value : ""})))
    };
    apiGetPostDatas();
  }, []);

  let handleClickComment = async (id) => {
    let datas = await axios.get(
      `http://localhost:8080/post-comment/by-post-${id}`
    );
    setPostComments(datas.data);
  };

  let handleClickSend = async(post) => {
    let dataID = await axios.get("http://localhost:8080/post-comment/current-post-comment-id")
    let datas = await axios.post(`http://localhost:8080/post-comment/${post.id}`,{
      id : dataID.data + 1,
      title : txtComment.find(dt => dt.postID === post.id).value.split(":")[0],
      user : location.state.user,
      published : true,
      content : txtComment.find(dt => dt.postID === post.id).value.split(":")[1]
    });
    setTxtComment(prev => {
      let current = [...prev]
      current.find(dt => dt.postID === post.id).value = ""
      return current
    })
    
  }

  return (
    <div className="container-home">
      <div className="create-post" onClick={() => navigate("/post", {state : {user : location.state.user}})}>
        <i className="fa-regular fa-square-plus icon-plus"></i>
        <span className="txt-post">Tạo Bài Post</span>
      </div>
      <div className="content-blogs">
        {postDatas
          .filter((p) => p.published === true)
          .map((post) => (
            <div key={post.id} className="content-blog">
              <div className="content-user">
                <i className="fa-solid fa-user icon-user"></i>
                <div className="content-name-public">
                  <span className="name">{`${post.author.firstName} ${post.author.middleName} ${post.author.lastName}`}</span>
                  <div>
                    <span>{`${post.publishedAt.slice(
                      11,
                      16
                    )}  ${post.publishedAt.slice(
                      8,
                      10
                    )}-${post.publishedAt.slice(5, 7)}-${post.publishedAt.slice(
                      0,
                      4
                    )}`}</span>
                    <i className="fa-solid fa-earth-americas icon-earth"></i>
                  </div>
                </div>
              </div>
              <div className="blog">
                <span className="content">Title : {post.title}</span>
                <span className="content">Meta Title : {post.metaTitle}</span>
                <span className="content">Content : {post.content}</span>
                <span className="content">Sumary : {post.summary}</span>
              </div>
              <div className="like-comments-share">
                <div className="content-feature">
                  <i className="fa-solid fa-thumbs-up icon-feature"></i>
                  <span className="txt-feature">Like</span>
                </div>
                <div
                  className="content-feature"
                  onClick={() => handleClickComment(post.id)}
                >
                  <i className="fa-solid fa-comment icon-feature"></i>
                  <span className="txt-feature">Comment</span>
                </div>
                <div className="content-feature">
                  <i className="fa-solid fa-share icon-feature"></i>
                  <span className="txt-feature">Share</span>
                </div>
              </div>

              <div className="modal-comments">
                {postComments.length > 0
                  ? postComments[0].postID === post.id
                    ? postComments.map((cmt) => (
                        <Comment key={cmt.id} cmt={cmt} viewID={cmt.id} user={location.state.user}/>
                      ))
                    : null
                  : null}
                <div className="form-input-comment">
                  <input
                    value={txtComment.find(dt => dt.postID === post.id).value}
                    className="input-comment"
                    type="text"
                    placeholder="comment..."
                    onChange={(e) => setTxtComment(prev => {
                      let current = [...prev]
                      current.find(dt => dt.postID === post.id).value = e.target.value
                      return current
                    })}
                  />
                  <i className="fa-solid fa-paper-plane icon-send" onClick={() => handleClickSend(post)}></i>
                </div>
              </div>
            </div>
          ))}
      </div>
    </div>
  );
};

export default Home;
