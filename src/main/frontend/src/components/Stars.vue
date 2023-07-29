<template>
  <div class="wrapper">
    <span class="notation">Notation</span> :
    <span class="fa fa-star"
          v-for="i in 5" :key="i" :class="{ 'checked' : i <= note}" @click="updateNote(i)">
    </span>
  </div>
</template>

<script>
export default {
  name: "Stars",
  props: {
    note: Number
  },
  methods: {
    updateNote(i){
      this.$emit("addNote", i)
    }
  }
}
</script>

<style scoped>
.wrapper{
  position: relative;
  padding: 10px;
  text-align: center;
}
.notation{
  font-weight: bold;
}
.checked{
  color: yellow;
  border: #212529;
}
.fa-star{
  transition: 0.4s;
}
.fa-star:hover{
  font-size: 25px;
}
</style>