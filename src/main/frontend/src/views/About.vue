<template>
<Header></Header>
  <!-- Page Header -->
  <header class="masthead" style="background-image: url('img/pp.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="page-heading">
            <h1>I'm Michael Mboni</h1>
            <span class="subheading">This is what I do.</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <p>I am currently studying computer science at the TU Dresden. I have basic knowledge of English,
          very good knowledge of German and French as my mother tongue. Communication is one of my strengths.
          I can deal with stress well and am capable of criticism. I can learn the tasks quickly and i am reliable
          with my work.</p>
        <p>I really love programming and have a lot of fun with it.</p>
        <p>More about me  on : <a href="https://michael-mb.github.io/Michael-CV/" target="_blank">Michael</a></p>
      </div>
    </div>
  </div>

  <hr>
<Footer></Footer>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  name: "About",
  components: {Footer, Header}
}
</script>

<style scoped>

</style>