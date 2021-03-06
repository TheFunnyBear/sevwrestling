import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import IconsPlugin from 'bootstrap-vue'
import VueRouter from 'vue-router'
import VueMoment from 'vue-moment'
import VueResource from 'vue-resource'
import VueLayers from 'vuelayers'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'vuelayers/lib/style.css'

Vue.use(VueRouter);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueMoment);
Vue.use(VueResource);
Vue.use(VueLayers);

Vue.http.options.root = 'http://localhost:9000/api';

// 1. Define route components.
// These can be imported from other files
import NewsList from './pages/news/list.vue'
import NewsAdd from './pages/news/add.vue'
import NewsEdit from './pages/news/edit.vue'
import NewsManage from './pages/news/manage.vue'
import NewsShow from './pages/news/show.vue'
import PhotosList from './pages/photos/list.vue'
import PhotosAdd from './pages/photos/add.vue'
import PhotosShow from './pages/photos/show.vue'
import PhotosEdit from './pages/photos/edit.vue'
import PhotosManage from './pages/photos/manage.vue'
import PhotoAlbumsAdd from './pages/photoAlbums/add.vue'
import PhotoAlbumsEdit from './pages/photoAlbums/edit.vue'
import PhotoAlbumsManage from './pages/photoAlbums/manage.vue'
import VideoList from './pages/video/list.vue'
import VideoAdd from './pages/video/add.vue'
import TimeTableShow from './pages/timeTable/show.vue'
import TimeTableEdit from './pages/timeTable/edit.vue'
import InfoShow from './pages/info/show.vue'
import InfoEdit from './pages/info/edit.vue'

// 2. Define some routes
// Each route should map to a component. The "component" can
// either be an actual component constructor created via
// `Vue.extend()`, or just a component options object.
// We'll talk about nested routes later.
const routes = [
  { path: '/', component: NewsList },
    /* Новости */
  { path: '/news', component: NewsList },
  { path: '/news_list', component: NewsList },
  { path: '/news_add', component: NewsAdd },
  { path: '/news_edit/:id', component: NewsEdit },
  { path: '/news_manage', component: NewsManage },
  { path: '/news_show/:id', component: NewsShow },
    /* Фотографии */
  { path: '/photos', component: PhotosList },
  { path: '/photos_list', component: PhotosList },
  { path: '/photos_list/:id', component: PhotosList },
  { path: '/photos_add', component: PhotosAdd },
  { path: '/photos_show/:id', component: PhotosShow },
  { path: '/photos_edit/:id', component: PhotosEdit },
  { path: '/photos_manage', component: PhotosManage },
  { path: '/photoAlbums_add', component: PhotoAlbumsAdd },
  { path: '/photoAlbums_edit/:id', component: PhotoAlbumsEdit },
  { path: '/photoAlbums_manage', component: PhotoAlbumsManage },
    /* Видео */
  { path: '/video', component: VideoList },
  { path: '/video_list', component: VideoList },
  { path: '/video_add', component: VideoAdd },
    /* Расписание */
  { path: '/timeTable', component: TimeTableShow },
  { path: '/timeTable_show', component: TimeTableShow },
  { path: '/timeTable_edit', component: TimeTableEdit },
    /* Информация */
  { path: '/info', component: InfoShow },
  { path: '/info_show', component: InfoShow },
  { path: '/info_edit', component: InfoEdit }
];

const router = new VueRouter({routes})

const app = new Vue({
  router
}).$mount('#app')
