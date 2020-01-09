<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>
    <PhotoAlbumManageList/>
    <PageFooter/>
  </div>
</template>

<script>
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';
  import PhotoAlbumManageList from '../../components/photoAlbums/PhotoAlbumManageList.vue'

  export default {
    components: {NavigationHeader, PageHeader, PageFooter, PhotoAlbumManageList},
    data() {
      return {
        isAdminMode: true,
        pageTitle: 'Управление альбомами фотографий',
        pageDescription: 'На этой странице можно управлять альбомами фотографий.',
      }
    }
  }
</script>
