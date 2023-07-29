<template>

  <Header back="black"></Header>
  <br><br>
  <Posts></Posts>
  <Footer></Footer>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import Posts from "@/components/Posts";
export default {
  name: "PostPage",
  components: {Posts, Footer, Header}
}
</script>

<style scoped>

</style>