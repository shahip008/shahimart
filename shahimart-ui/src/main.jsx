import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from "react-router-dom";
import Contact, { contactAction } from "./Component/Contact.jsx";
import About from "./Component/About.jsx";
import Login, { loginAction } from "./Component/Login.jsx";
import Cart from "./Component/Cart.jsx";
import Home from "./Component/Home.jsx";
import ErrorPage from "./Component/ErrorPage.jsx";
import ProductDetails from "./Component/ProductDetails.jsx";
import { CartContext, CartProvider } from "./Component/Store/CartContext.jsx";
import { ToastContainer, Bounce } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { AuthProvider } from "./Component/Store/auth-context.jsx";
import CheckoutForm from "./Component/CheckoutForm.jsx";
import ProtectedRoute from "./Component/ProtectedRoute.jsx";
import Profile from "./Component/Profile.jsx";
import Orders from "./Component/Orders.jsx";
import AdminOrders from "./Component/Admin/AdminOrders.jsx";
import Messages from "./Component/Admin/Messages.jsx";
import Register, { registerAction } from "./Component/Register.jsx";

const routeDefinition = createRoutesFromElements(
  <Route path="/" element={<App />} errorElement={<ErrorPage />}>
    <Route index element={<Home />} />
    <Route path="/home" element={<Home />} />
    <Route path="/about" element={<About />} />
    <Route path="/contact" element={<Contact />} action={contactAction} />
    <Route path="/login" element={<Login />} action={loginAction} />
    <Route path="/register" element={<Register />} action={registerAction} />
    <Route path="/cart" element={<Cart />} />
    <Route path="/products/:productId" element={<ProductDetails />} />
    <Route element={<ProtectedRoute />}>
      <Route path="/checkout" element={<CheckoutForm />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/orders" element={<Orders />} />
      <Route path="/admin/orders" element={<AdminOrders />} />
      <Route path="/admin/messages" element={<Messages />} />
    </Route>
  </Route>,
);
const appRouter = createBrowserRouter(routeDefinition);
// const appRouter = createBrowserRouter([
//   {
//     path: "/",
//     element: <App />,
//     errorElement: <ErrorPage />,
//     children: [
//       {
//         index: true,
//         element: <Home />,
//       },
//       {
//         path: "/home",
//         element: <Home />,
//       },
//       {
//         path: "/about",
//         element: <About />,
//       },
//       {
//         path: "/contact",
//         element: <Contact />,
//       },
//       {
//         path: "/login",
//         element: <Login />,
//       },
//       {
//         path: "/cart",
//         element: <Cat />,
//       },
//     ],
//   },
// ]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <AuthProvider>
      <CartProvider>
        <RouterProvider router={appRouter} />
      </CartProvider>
    </AuthProvider>
    <ToastContainer
      position="top-center"
      autoClose={3000}
      hideProgressBar={false}
      newestOnTop={false}
      draggable
      pauseOnHover
      theme={localStorage.getItem("theme") === "dark" ? "dark" : "light"}
      transition={Bounce}
    />
  </StrictMode>,
);
