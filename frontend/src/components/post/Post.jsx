import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./post.scss";
import axios from "axios";

const Post = () => {
  const [title, setTitle] = useState("");
  const [metaTitle, setMetaTitle] = useState("");
  const [content, setContent] = useState("");
  const [sumary, setSummary] = useState("");
  const [publish, setPublish] = useState("true");

  let navigate = useNavigate();
  let location = useLocation();

  useEffect(() => {
    console.log(location.state.user);
  }, []);

  let handleClickBtnPost = async() => {
    let dataID = await axios.get("http://localhost:8080/post/current-post-id");
    let datas = await axios.post("http://localhost:8080/post",{
      id : dataID.data + 1,
      author : location.state.user,
      title : title,
      metaTitle : metaTitle,
      summary : sumary,
      published : publish === "true" ? true : false,
      content : content
    });
    navigate("/home", {state : {post : datas.data, user : location.state.user}})
  }
  return (
    <div className="container-post">
      <div className="content-posts">
        <span className="header">THÔNG TIN BÀI POST</span>
        <div className="form-content">
          <label form="publish">Publish</label>
          <select value={publish} onChange={(e) => setPublish(e.target.value)} className="select-publish">
            <option value={"true"}>Công Khai</option>
            <option value={"false"}>Riêng Tư</option>
          </select>
        </div>
        <div className="form-content">
          <label form="title">Title</label>
          <textarea
            id="title"
            onChange={(e) => setTitle(e.target.value)}
            rows={5}
            cols={90}
          >
            {title}
          </textarea>
        </div>
        <div className="form-content">
          <label form="title">Meta Title</label>
          <textarea
            id="title"
            onChange={(e) => setMetaTitle(e.target.value)}
            rows={5}
            cols={90}
          >
            {metaTitle}
          </textarea>
        </div>
        <div className="form-content">
          <label form="title">Content</label>
          <textarea
            id="title"
            onChange={(e) => setContent(e.target.value)}
            rows={8}
            cols={90}
          >
            {content}
          </textarea>
        </div>
        <div className="form-content">
          <label form="title">Sumary</label>
          <textarea
            id="title"
            onChange={(e) => setSummary(e.target.value)}
            rows={8}
            cols={90}
          >
            {sumary}
          </textarea>
        </div>
        <button className="btn-create-post" onClick={handleClickBtnPost}>Tạo Post</button>
      </div>
    </div>
  );
};

export default Post;
