<template>
  <Header back="black"></Header>
  <br><br><br>
  <h3 class="title">ADMIN PANEL</h3>
  <div class="wrapper" v-for="(user,index) in allUsers" :key="index" >
    <div class="card">
      <span class="fas fa-window-close close" v-if="user.id !== userInfos.id" @click="deleteUser(user.id)"></span>
      <h1 class="card__title">Profile {{index + 1}}</h1>
      <p class="card__subtitle">So i am ...</p>
      <p><span class="header">Firstname:</span> {{ user.firstname }}</p>
      <p><span class="header">Lastname:</span> {{user.lastname}}</p>
      <p><span class="header">Nickname:</span> {{user.nickname}}</p>
      <h4>Authorities </h4>
      <ul>
        <li :key="index2" v-for="(auth, index2) in user.grantedAuthorities">
          {{index2}} - {{auth}}
        </li>
      </ul>
      <p><span class="header">Email:</span> {{user.email}}</p>
      <img :src="user.image"/>
    </div>
    <br>
  </div>

</template>

<script>
import Header from "../components/Header";
export default {
  name: "AdminPage",
  components: {Header},
  mounted: function () {
    if(this.$store.state.user.id === -1){
      this.$router.push('/')
    }
    let self = this

    this.$store.dispatch('getUserInfos').then(()=>{
      if(self.$store.state.userInfos.admin !== true){
        this.$router.push('/profile')
      }
    })
    this.$store.dispatch('getAllUsers')
  },
  computed: {
    allUsers: function (){
      return this.$store.state.users
    },
    userInfos : function (){
      return this.$store.state.userInfos
    },
  },
  methods : {
    deleteUser(userId){
      if(confirm("Do you really want to delete this user ?"))
        this.$store.dispatch('deleteUser', userId)
    }
  }
}
</script>


<style scoped>

.title{
  text-align: center;
}
.wrapper{
  position: relative;
}

p{
  margin-top: 0 !important;
}

.close{
  font-size: 30px;
  color: red;
  position: absolute;
  right: 5%;
  top: 3%;
  cursor: pointer;
}
.header {
  font-size: 20px;
  font-weight: bold;
}

.card {
  margin-top: 15px;
  position: relative;
  max-width: 100%;
  width: 450px;
  background:white;
  border-radius: 16px;
  padding:20px;
  left: 50%;
  transform: translateX(-50%);
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
</style>