import { Outlet } from "react-router-dom";
import Footer from "./Component/Footer/Footer";
import Header from "./Component/Header";
import Home from "./Component/Home";

function App() {
  return (
    <>
      <Header />
      <Outlet />
      <Footer />
    </>
  );
}

export default App;
