<template>

  <Header back="black"></Header>

  <div class="card">
    <span class="fas fa-edit edit" v-if="mode=== 'edit'" @click="switchToProfile"></span>
    <span class="fas fa-edit edit" v-else @click="switchToEdit"></span>

    <h1 v-if="mode=== 'edit'" class="card__title">Edit Profile</h1>
    <h1 v-else class="card__title">{{ userInfos.firstname }} {{userInfos.lastname}}</h1>

    <div v-if="mode==='edit'">
      <input type="text" class="form-control form-row__input" placeholder="firstname" v-model="firstname" />
      <br>
      <input type="text" class="form-control form-row__input" placeholder="lastname" v-model="lastname" />
      <br>
      <input type="text" class="form-control form-row__input" placeholder="nickname" v-model="nickname" />
      <br>
      <input type="password" class="form-control form-row__input" placeholder="password" v-model="password"/>
      <br>
      <input type="password" class="form-control form-row__input" placeholder="new password" v-model="newPassword"/>
    <br>
    </div>

    <div v-else>
      <p><span class="header">Nickname:</span> {{userInfos.nickname}}</p>
    </div>


    <h4>Authorities </h4>
    <ul>
      <li :key="index2" v-for="(auth, index2) in userInfos.grantedAuthorities">
        {{index2}} - {{auth}}
      </li>
    </ul>
    <p><span class="header">Email:</span> {{userInfos.email}}</p>
    <img :src="userInfos.image"/>

    <div class="form-row">
      <div class="form-row error" v-if="updateStatus === 'edit-error'">
        Error during Edit , check if all parameters are correct
      </div>

      <button v-if="mode==='profile'" @click="logout" class="button">
        Logout
      </button>

      <button v-else @click="editProfile" :class="{'button--disabled' : !validatedFields}" class="button">
        <span v-if="updateStatus === 'loading'">Edit in progress...</span>
        <span v-else>Edit</span>
      </button>

    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
export default {
name: "ProfilePage",
  data(){
    return {
      mode : 'profile',
      firstname : '',
      lastname: '',
      nickname: '',
      password: '',
      newPassword: '',
    }
  },
  components: {Header},
  created: function () {
    if(this.$store.state.user.id === -1){
      this.$router.push('/')
    }
    this.$store.dispatch('getUserInfos')
    this.firstname = this.userInfos.firstname
    this.lastname = this.userInfos.lastname
    this.nickname = this.userInfos.nickname
  },
  methods:{
    logout(){
      this.$store.commit('logout');
      this.$router.push("/");
    },
    switchToProfile() {
      this.mode = 'profile'
    },
    switchToEdit() {
      this.mode = 'edit'
    },
    editProfile(){
      if(this.validatedFields){
        this.$store.dispatch('editProfile' , {
          id : this.userInfos.id,
          firstname : this.firstname,
          lastname : this.lastname,
          nickname : this.nickname,
          password : this.password,
          newPassword : this.newPassword
        }).then( (response) => {
          console.log(response)
          this.firstname = ''
          this.lastname = ''
          this.nickname = ''
          this.password = ''
          this.newPassword = ''
          this.mode = 'profile'
        }).catch( (error) => {
          console.log(error)
        })
      }
    },
  },
  computed: {
    userInfos : function (){
      return this.$store.state.userInfos
    },
    isLoggedIn: function () {
      return this.$store.state.user.id !== -1
    },
    isAdmin: function () {
      return this.$store.state.userInfos.admin === true
    },
    updateStatus: function (){
      return this.$store.state.status
    },
    validatedFields: function () {
        return !!(this.firstname !== '' && this.lastname !== '' && this.nickname && this.password !== '' && this.newPassword !== '');
    },
  }
}
</script>

<style scoped>

.edit{
  font-size: 30px;
  color: #2196f3;
  position: absolute;
  right: 5%;
  top: 3%;
  cursor: pointer;
}
.header {
  font-size: 20px;
  font-weight: bold;
}
.form-row {
  display: flex;
  margin: 16px 0;
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