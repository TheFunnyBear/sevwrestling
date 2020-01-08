<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>
    <div>
      <b-embed
              type="iframe"
              aspect="16by9"
              src="https://www.sevwrestling.ru/VideoFileUpload/WrestlingDaySimferopolSound848_480.mp4"
              allowfullscreen
      ></b-embed>
    </div>
    <PageFooter/>
  </div>
</template>

<script>
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';

  export default {
    components: {NavigationHeader, PageHeader, PageFooter},
    data() {
      return {
        isAdminMode: true,
        pageTitle: 'Просмотр видео',
        pageDescription: 'На этой странице просмотреть видео.',
      }
    }
  }
</script>
