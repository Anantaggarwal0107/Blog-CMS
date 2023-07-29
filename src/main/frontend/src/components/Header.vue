<template>
  <nav class="navbar navbar-expand-lg navbar-light fixed-top black"   id="mainNav">
    <div class="container">
      <a class="navbar-brand" href="/">#Michael</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <router-link to="/">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/blog">Blog</router-link>
          </li>
          <li class="nav-item">
            <router-link v-if="!isLoggedIn" to="/login">Login / Register</router-link>
          </li>
          <li class="nav-item">
            <router-link v-if="isLoggedIn" to="/profile">Profile</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/about">About</router-link>
          </li>
          <li class="nav-item">
            <router-link v-if="isLoggedIn && isAdmin" to="/admin">Admin Page</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/contact">Contact Me</router-link>
          </li>
          <li class="nav-item">
            <a v-if="isLoggedIn" href="" @click="logout">Logout</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "Header",
  props:{
    back : String
  },
  mounted: function () {
      this.$store.dispatch('getUserInfos')
  },
  computed:{
    isLoggedIn: function (){
      return this.$store.state.user.id !== -1
    },
    isAdmin: function (){
      return this.$store.state.userInfos.admin === true
    },

    isModerator: function (){
      return this.$store.state.userInfos.moderator === true
    },
  },
  methods:{
    logout(){
      this.$store.commit('logout');
      this.$router.push("/");
    }
  }
}
</script>

<style scoped>
.black{
  background: #000000 !important;
}
a{
  cursor: pointer;
}
.black a , .black button {
  color: white !important;
}
</style>