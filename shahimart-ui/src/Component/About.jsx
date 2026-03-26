import React from "react";
import PageTitle from "./PageTitle";

export default function About() {
  const h3Style = "text-lg font-semibold text-primary dark:text-light mb-2";
  const pStyle = "text-gray-600 dark:text-lighter";
  return (
    <div className="max-w-[1152px] min-h-[852px] mx-auto px-6 py-8 font-primary">
      <PageTitle title="About Us" />
      {/* About Us Content */}
      <p className="leading-6 mb-8 text-gray-600 dark:text-lighter">
        <span className="text-lg font-semibold text-primary dark:text-light">
          Shahi Mart
        </span>{" "}
        is your trusted online shopping destination, created to provide a
        seamless and reliable shopping experience. Our goal is to offer
        high-quality products at affordable prices, ensuring customer
        satisfaction with every purchase.{" "}
      </p>

      {/* Why Choose Us Section */}
      <h2 className="text-2xl leading-[32px] font-bold text-primary dark:text-light mb-6">
        Why Choose Us?
      </h2>

      {/* Features */}
      <div className="space-y-8">
        {/* Feature 1 */}
        <div>
          <h3 className={h3Style}>Wide Range of Products</h3>
          <p className={pStyle}>
            ShahiMart offers a variety of products including electronics,
            fashion, home essentials, and more, all in one place to make your
            shopping easy and convenient.
          </p>
        </div>

        {/* Feature 2 */}
        <div>
          <h3 className={h3Style}>Best Quality Products</h3>
          <p className={pStyle}>
            We focus on delivering high-quality and genuine products. Every
            product is carefully selected to ensure durability, performance, and
            value for money.
          </p>
        </div>

        {/* Feature 3 */}
        <div>
          <h3 className={h3Style}>Affordable Prices</h3>
          <p className={pStyle}>
            Our platform provides competitive pricing so you can shop your
            favorite products without worrying about high costs.
          </p>
        </div>

        {/* Feature 4 */}
        <div>
          <h3 className={h3Style}>Customer Satisfaction</h3>
          <p className={pStyle}>
            Customer satisfaction is our top priority. We are committed to
            providing secure payments, fast delivery, and reliable support to
            make your shopping experience smooth and enjoyable.
          </p>
        </div>

        {/* Feature 5 */}
        <div>
          <h3 className={h3Style}>Easy & Secure Shopping</h3>
          <p className={pStyle}>
            Our website is designed to be simple, fast, and secure, allowing you
            to browse, select, and purchase products easily from anywhere.
          </p>
        </div>
      </div>
    </div>
  );
}
