<template>
  <Header></Header>
  <!-- Page Header -->
  <header class="masthead" :style="articleImage">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="post-heading">
            <h1>{{article.title}}</h1>
            <h2 class="subheading">{{article.subtitle}}</h2>
            <span class="meta">Posted by
              <a href="#">{{article.author}}</a>
              on {{article.date}}</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Post Content -->
  <article>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <p>{{article.text}}</p>
          <Stars @addNote="addNote" :note="article.note"></Stars>
          <p>Placeholder text by
            <a href="#">{{article.author}}</a>.
          </p>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <h2>Comments</h2>
          <div v-for="comment in article.comments" :key="comment.id" class="comment-box"
               :class="{'comment-owner' : (comment.authorName === userInfos.nickname)}">
            <i class="fas fa-window-close close" v-if="(isLoggedIn && isModerator)
            || (comment.authorName === userInfos.nickname)" @click="deleteComment(comment.id)"
            ></i>
            <span class="author">{{comment.authorName}}</span>
            <p class="text">{{comment.comment}}</p>
          </div>

          <div v-if="isLoggedIn" class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>Message</label>
              <textarea v-model="comment" rows="5" class="form-control" placeholder="Message" id="message" required data-validation-required-message="Please enter a message."></textarea>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <br>
          <button v-if="isLoggedIn" :class="{'button--disabled' : validatedFields}" @click="addComment" class="btn btn-primary" id="sendMessageButton">Send</button>
        </div>
      </div>
    </div>
  </article>

  <hr>

  <Footer></Footer>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import Stars from "@/components/Stars";
export default {
  name: "PostPage",
  props:['id'],
  data () {
    return {
      comment: ''
    }
  },
  components: {Stars, Footer, Header},
  computed: {
    isLoggedIn: function () {
      return this.$store.state.user.id !== -1
    },
    isModerator: function () {
      return this.$store.state.userInfos.moderator === true
    },
    article: function (){
      return this.$store.state.article
    },
    userInfos : function (){
      return this.$store.state.userInfos
    },
    articleImage: function (){
      return {
        "background-image" : 'url('+this.article.image+')'
      }
    },
    validatedFields: function (){
      return this.comment === ''
    }
  },
  mounted() {
    if(!this.isLoggedIn){
      this.$router.push('/login')
    }
    this.$store.dispatch('getArticle' , this.id)
  },
  methods:{
    addComment(){
      if(!this.validatedFields){
        this.$store.dispatch('addComment' , {
          comment : this.comment,
          articleId : this.article.id,
          authorName : this.userInfos.nickname,
        }).then( (response) => {
          console.log(response)
          this.comment = ''
        }).catch( (error) => {
          console.log(error)
        })
      }
    },
    deleteComment(commentId){
      if(confirm("do you really want to delete your comment ?")){
        this.$store.dispatch('deleteComment' , {
          commentId : commentId,
        })
      }
    },
    addNote(i){
      this.$store.dispatch('addNote' , {
        articleId : this.article.id,
        mail : this.userInfos.email,
        note : i
      })
    }
  }
}
</script>

<style scoped>
.close{
  color: red;
  cursor: pointer;
}
.author{
  font-size: 16px;
  font-weight: bold;
  text-transform: capitalize;
}
.text{
  margin-top: 15px;
  margin-bottom: 15px ;
}
.comment-box{
  min-width: 500px;
  color: white;
  height: auto;
  font-size: 17px;
  border: 1px solid #CCC;
  background-color: #4c4c4c;
  margin-top: 15px;
  padding: 6px 8px;
  border-radius: 15px;
  z-index: 500;
}
.comment-owner{
  color: black;
  background: #d0d0d0;
}
.button--disabled {
  background:#cecece;
  color:#ececec
}
.button--disabled:hover {
  cursor:not-allowed;
  background:#cecece;
}
</style>