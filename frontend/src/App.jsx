import { BrowserRouter, Route, Routes } from "react-router-dom"
import Login from "./components/login/Login"
import Home from "./components/home/Home"
import Post from "./components/post/Post"

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login/>}/>
        <Route path="/home" element={<Home/>}/>
        <Route path="/post" element={<Post/>}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
