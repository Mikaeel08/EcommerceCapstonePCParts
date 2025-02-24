<template>
  <div class="shipping-container">
    <h1 class="shipping-information">Shipping Address</h1>
    <div v-if="shipping">
      <table class="table table-striped">
        <thead>
        <tr>
          <th>ShippingId</th>
          <th>Address</th>
          <th>City</th>
          <th>Province</th>
          <th>Zip Code</th>
          <th>Country</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>{{ shipping.shippingId }}</td>
          <td>{{ shipping.address }}</td>
          <td>{{ shipping.city }}</td>
          <td>{{ shipping.state }}</td>
          <td>{{ shipping.zipCode }}</td>
          <td>{{ shipping.country }}</td>
        </tr>
        </tbody>
      </table>
      <!-- Button to Update Contact Info -->
      <button class="update-contact-button" @click="navigateToUpdateContact">Update Contact Information</button>
    </div>
    <div v-else>
      <p class="no-shipping-message">No shipping information available.</p>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex';

export default {
  name: 'ShippingAddress',
  computed: {
    ...mapState({
      user: state => state.user,
    }),
    shipping() {
      return this.user ? this.user.shipping : null;
    }
  },
  mounted() {
    this.fetchUserShippingDetails();
  },
  methods: {
    fetchUserShippingDetails() {
      if (this.user.email) {
        this.$store.dispatch('fetchUserByEmail', this.user.email)
            .catch(error => {
              console.error('Error fetching user data:', error);
            });
      }
    },
    navigateToUpdateContact() {
      // Redirect to /UpdateContact page
      this.$router.push('/UpdateContact');
    }
  }
};
</script>

<style scoped>
.shipping-information{
  text-align: center;
  font-size: 2.5rem;
  color: #232f3e;
  margin: 30px 0;
  padding: 10px;
  background-color: #69feca;
  border-radius: 10px;
  text-transform: uppercase;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  letter-spacing: 1.5px;
  font-family: 'Orbitron', sans-serif;
}
.shipping-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.table th, .table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.table th {
  background-color: #007bff;
  color: white;
}

.table-striped tbody tr:nth-of-type(odd) {
  background-color: #f2f2f2;
}

.no-shipping-message {
  text-align: center;
  color: #666;
  font-size: 18px;
}

/* Button Styles */
.update-contact-button {
  display: block;
  margin: 20px auto;
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.update-contact-button:hover {
  background-color: #0056b3;
}
</style>
