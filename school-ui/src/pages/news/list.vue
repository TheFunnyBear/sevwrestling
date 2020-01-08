<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>
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
        pageTitle: 'Список новостей',
        pageDescription: 'На этой странице отображаются новости Школы Вольной Борбы и успехи её воспитанников.',
      }
    }
  }
</script>
