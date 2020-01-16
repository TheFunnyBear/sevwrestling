<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>
    <PictureManageList/>
    <PageFooter/>
  </div>
</template>

<script>
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';
  import PictureManageList from "../../components/picture/PictureManageList.vue";

  export default {
    components: {NavigationHeader, PageHeader, PageFooter,PictureManageList},
    data() {
      return {
        isAdminMode: true,
        pageTitle: 'Управление фотографиями',
        pageDescription: 'На этой странице можно управлять фотографиями.',
      }
    }
  }
</script>
