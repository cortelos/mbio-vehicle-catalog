import Vue from 'vue'
import VueResource from 'vue-resource'
import VueDialog from 'vuejs-dialog'
import VueToasted from 'vue-toasted'
import App from './App.vue'

Vue.use(VueResource);
Vue.use(VueDialog);
Vue.use(VueToasted, {
  duration: 3000
});

Vue.url.options.root = WEBPACK_BACKEND_URL;

export const eventBus = new Vue({
  methods: {
    updateList() {
      this.$emit('updateList');
    }
  }
});

new Vue({
  el: '#app',
  render: h => h(App)
})
