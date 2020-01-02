import Vue from 'vue'
import VueResource from 'vue-resource'
Vue.use(VueResource)

import App from 'pages/App.vue'

import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)


new Vue({
    el: '#app',
    render: a => a(App)
})