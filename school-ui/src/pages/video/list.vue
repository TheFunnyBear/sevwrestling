<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>
    <VideoList/>
    <PageFooter/>
  </div>
</template>

<script>
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';
  import VideoList from "../../components/video/VideoList.vue";

  export default {
    components: {NavigationHeader, PageHeader, PageFooter, VideoList},
    data() {
      return {
        isAdminMode: true,
        pageTitle: 'Просмотр видео',
        pageDescription: 'На этой странице просмотреть видео.',
      }
    }
  }
</script>
