import { useMemo, useState } from "react";
import Dropdown from "./Dropdown";
import ProductCard from "./ProductCard";
import SearchBox from "./SearchBox";

const sortList = ["Popularity", "Price Low to High", "Price High to Low"];
export default function ProductListing({ products }) {
  const [searchText, setSearchText] = useState("");
  const [selectedSort, setselectedSort] = useState("Popularity");

  const filteredAndSortedProducts = useMemo(() => {
    if (!Array.isArray(products)) return [];

    let filteredProducts = products.filter(
      (product) =>
        product.name.toLowerCase().includes(searchText.toLowerCase()) ||
        product.description.toLowerCase().includes(searchText.toLowerCase()),
    );

    return filteredProducts.slice().sort((a, b) => {
      switch (selectedSort) {
        case "Popularity":
          return parseInt(b.popularity) - parseInt(a.popularity);
          break;
        case "Price Low to High":
          return parseInt(a.price) - parseInt(b.price);
          break;
        case "Price High to Low":
          return parseInt(b.price) - parseInt(a.price);
          break;

        default:
          filterAndSortedProducts = filterAndSortedProducts.sort(
            (a, b) => parseInt(b.popularity) - parseInt(a.popularity),
          );
          break;
      }
    });
  }, [products, searchText, selectedSort]);

  function handleSearchText(searchText, event) {
    setSearchText(searchText);
    console.log(searchText);
  }
  function handleSortChange(sortType) {
    console.log("sortType : ", sortType);
    setselectedSort(sortType);
  }

  console.log("filteredAndSortedProducts : ", filteredAndSortedProducts);
  return (
    <div className="max-w-[1152px] mx-auto">
      <div className="flex flex-col sm:flex-row justify-between items-center gap-4 pt-12">
        <SearchBox
          label="Search"
          placeholder="Search products... "
          value={searchText}
          handleSearch={(value, event) => handleSearchText(value, event)}
        />

        <Dropdown
          label="Filter"
          options={sortList}
          selectedValue={selectedSort}
          handleSort={(value) => handleSortChange(value)}
        />
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-y-8 gap-x-6 py-12">
        {filteredAndSortedProducts.length > 0 ? (
          filteredAndSortedProducts.map((product) => (
            <ProductCard key={product.productId} product={product} />
          ))
        ) : (
          <p className="text-center font-primary font-bold text-lg text-primary">
            No Products Found
          </p>
        )}
      </div>
    </div>
  );
}
