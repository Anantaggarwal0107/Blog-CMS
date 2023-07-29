<template>
  <Header back="back"></Header>
  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <div class="add-icon-container">
          <router-link to="/addArticle" v-if="isLoggedIn && isModerator">
            <i class="fas fa-plus-circle add-icon"></i>
          </router-link>
        </div>
        <Post v-for="post  in articles" :key="post.id" :postId="post.id" :author="post.author" :subtitle="post.subtitle" :date="post.date" :text="post.text" :title="post.title"></Post>
      </div>
    </div>
  </div>
</template>

<script>
import Post from "@/components/Post";
import Header from "./Header";
export default {
  name: "Posts",
  components: {Header, Post},
  data(){
    return{
      posts: []
    }
  },
  computed: {
    isLoggedIn: function () {
      return this.$store.state.user.id !== -1
    },
    isModerator: function () {
      return this.$store.state.userInfos.moderator === true
    },
    articles: function (){
      return this.$store.state.articles
    },
  },
  mounted() {
    this.$store.dispatch("getArticles")
  }
}
</script>

<style scoped>
.add-icon{
  font-size: 35px;
  color: #7dd47d;
  transition: 0.4s;
  cursor: pointer;

}
.add-icon:hover{
  font-size: 40px;
  color: #4fb34f;

}

.add-icon-container{
  padding-top: 30px;
  text-align: center;
}
</style>