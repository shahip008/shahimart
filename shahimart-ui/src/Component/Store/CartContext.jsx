import { createContext, useContext, useEffect, useState } from "react";

// const initialCartContext = {
//   cart: [],
//   setCart: () => {},
//   addToCard: () => {
//     console.log("Product added to cart");
//   },
//   removeFromCart: () => {},
//   totalQuantity: 0,
// };
//step 1
export const CartContext = createContext();
export const useCart = () => useContext(CartContext);
export const CartProvider = ({ children }) => {
  // Initialize cart state from localStorage or as an empty array
  const [cart, setCart] = useState(() => {
    try {
      const storedCart = localStorage.getItem("cart");
      return storedCart ? JSON.parse(storedCart) : [];
    } catch (error) {
      console.error("failed to parse cart from localstorage ", error);
      return [];
    }
  });

  // Save cart to localStorage whenever it changes
  useEffect(() => {
    try {
      localStorage.setItem("cart", JSON.stringify(cart));
    } catch (error) {
      console.error("Failed to save cart to localStorage:", error);
    }
  }, [cart]);

  const addToCart = (product, quantity) => {
    setCart((prevCart) => {
      const existItem = prevCart.find(
        (item) => item.productId === product.productId,
      );
      if (existItem) {
        return prevCart.map((item) =>
          item.productId === product.productId
            ? { ...item, quantity: item.quantity + quantity }
            : item,
        );
      }
      return [...prevCart, { ...product, quantity }];
    });
  };

  const removeFromCart = (productId) => {
    setCart((prevCart) =>
      prevCart.filter((item) => item.productId !== productId),
    );
  };

  const totalQuantity = cart.reduce((acc, item) => acc + item.quantity, 0);

  return (
    <CartContext.Provider
      value={{ cart, setCart, addToCart, removeFromCart, totalQuantity }}
    >
      {children}
    </CartContext.Provider>
  );
};
