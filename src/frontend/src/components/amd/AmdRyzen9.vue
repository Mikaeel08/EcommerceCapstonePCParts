<script setup>
import { ref, onMounted } from 'vue';

// Define the products ref
const products = ref([]);

// Fetch data when the component is mounted
const fetchData = () => {
  fetch("/api/product/search/Ryzen 9")
      .then(response => response.json())
      .then(data => {
        products.value = data; // Store fetched data
        console.log(products.value.map(p => p.productPicture));
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
};

onMounted(() => {
  fetchData();
});

// Method to format price as currency
const formatCurrency = (value) => {
  if (!value) return '';
  return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'ZAR' }).format(value);
};
</script>

<template>
  <div class="products-container">
    <h1>AMD Ryzen 9 Processors</h1>
    <p>Check out the latest Ryzen 9 products:</p>
    <ul class="products-list">
      <li v-for="product in products" :key="product.productId" class="product-item">
        <img :src="`/${product.productPicture}`" :alt="product.productName" class="product-image" />
        <div class="product-details">
          <h3>{{ product.productName }}</h3>
          <p class="price">{{ formatCurrency(product.price) }}</p>
        </div>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.products-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.products-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between; /* Adjusted for even spacing */
  list-style: none;
  padding: 0;
  margin: 0; /* Ensure no extra margin on the container */
}

.product-item {
  background-color: #f8f9fa;
  border: 3px solid #007bff; /* Blue border */
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, border-color 0.3s ease;
  flex: 0 1 calc(25% - 20px); /* Each product takes up approximately 25% of the row with spacing */
  margin: 10px; /* Reduced margin to fit more items */
  padding: 15px; /* Increased padding */
  text-align: center;
}

.product-item:hover {
  transform: translateY(-10px);
  border-color: #28a745; /* Change border to green on hover */
}

.product-image {
  width: 150px; /* Increased image width */
  height: auto;
  margin-bottom: 15px; /* Increased margin */
}

.product-details {
  padding: 15px; /* Increased padding */
}

.product-details h3 {
  font-size: 1.3rem; /* Increased font size */
  margin-bottom: 10px; /* Increased margin */
  color: #333;
}

.product-details .price {
  font-size: 1.2rem; /* Increased font size */
  color: #ff5722; /* Orange color for currency */
}

@media (max-width: 768px) {
  .product-item {
    flex: 0 1 calc(50% - 20px); /* Each product takes up approximately 50% of the row on smaller screens */
  }
}

@media (max-width: 480px) {
  .product-item {
    flex: 0 1 calc(100% - 20px); /* Each product takes up the full width of the row on mobile screens */
  }
}
</style>
