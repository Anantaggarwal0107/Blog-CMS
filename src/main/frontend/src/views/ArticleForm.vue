<template>
  <Header back="black"></Header>
  <div class="card">
    <h1 class="card__title" >POST NEW ARTICLE</h1>

    <div class="form-row">
      <input v-model="title" class="form-row__input" type="text" placeholder="Title"/>
    </div>

    <div class="form-row">
      <input v-model="subtitle" class="form-row__input" type="text" placeholder="Subtitle"/>
    </div>

    <div class="form-row">
      <input v-model="image" class="form-row__input" type="text" placeholder="/img/image1.jpg"/>
    </div>

    <div class="form-row">
      <textarea v-model="text" class="form-row__input" rows="7" placeholder="text"/>
    </div>

    <div class="form-row">
      <button @click="addArticle"  :class="{'button--disabled' : !validatedFields}" class="button">
        <span>Post</span>
      </button>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";

export default {

name: "PostForm",
  components: {Header},
  data () {
    return {
      title: '',
      subtitle: '',
      image: '',
      text: '',
      author: '',
    }
  },
  computed:{
    validatedFields: function () {
        return !!(this.title !== '' && this.subtitle !== '' && this.image !== ''
            && this.text);
      },
    userInfos : function (){
      return this.$store.state.userInfos
    },
    },
  methods :{
    addArticle(){
      const self = this;
      this.$store.dispatch('createArticle' , {
        title : this.title,
        subtitle : this.subtitle,
        image : this.image,
        text : this.text,
        author : this.userInfos.firstname + ' ' + this.userInfos.lastname
      }).then( (response) => {
        console.log(response)
        self.$router.push("/blog")
      }).catch( (error) => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
.form-row {
  display: flex;
  margin: 16px 0px;
  gap:16px;
  flex-wrap: wrap;
}

.form-row__input {
  padding:8px;
  border: none;
  border-radius: 8px;
  background:#f2f2f2;
  font-weight: 500;
  font-size: 16px;
  flex:1;
  min-width: 100px;
  color: black;
}

.form-row__input::placeholder {
  color:#aaaaaa;
}

.error{
  color: red;
}

.card {
  position: relative;
  left: 50%;
  transform: translate(-50%, 30%);
  max-width: 100%;
  width: 540px;
  background:white;
  border-radius: 16px;
  padding:32px;
}

.card__title {
  text-align:center;
  font-weight: 800;
}

.card__subtitle {
  text-align: center;
  color:#666;
  font-weight: 500;
}

.button {
  background: #2196F3;
  color:white;
  border-radius: 8px;
  font-weight: 800;
  font-size: 15px;
  border: none;
  width: 100%;
  padding: 16px;
  transition: .4s background-color;
}

.card__action {
  color:#2196F3;
  text-decoration: underline;
}

.card__action:hover {
  cursor:pointer;
}

.button:hover {
  cursor:pointer;
  background: #1976D2;
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