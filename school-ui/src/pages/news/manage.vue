<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>
    <MessagesManageList/>
    <PageFooter/>
  </div>
</template>

<script>
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';
  import MessagesManageList from '../../components/messages/MessagesManageList.vue';

  export default {
    components: {NavigationHeader, PageHeader, PageFooter, MessagesManageList},
    data() {
      return {
        isAdminMode: true,
        pageTitle: 'Управление новостями',
        pageDescription: 'На этой странице можно управлять новостями.',
      }
    }
  }
</script>
