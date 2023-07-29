<template>
  <!-- Navigation -->
  <Header></Header>

  <!-- Page Header -->
  <header class="masthead" style="background-image: url('/img/home-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="site-heading">
            <h1>#Michael Blog</h1>
            <span class="subheading">My Personal Blog , more about my life</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <Posts></Posts>

  <hr>

  <Footer></Footer>
</template>


<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";
import Posts from "@/components/Posts";
export default {
name: "LandingPage",
  components: {Posts, Header, Footer}
}
</script>

<style scoped>

</style>