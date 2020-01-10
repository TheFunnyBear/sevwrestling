<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>

    <CoachInfoItemEdit v-for="item in coachInfoItems" v-bind:key="item.id" :formData="item"/>

    <PageFooter/>
  </div>
</template>

<script>
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';
  import CoachInfoItemEdit from '../../components/coach/CoachInfoItemEdit.vue';

  export default {
    components: {NavigationHeader, PageHeader, PageFooter, CoachInfoItemEdit},
    data() {
      return {
        isAdminMode: true,
        pageTitle: 'Редактировать информацию',
        pageDescription: 'На этой странице можно редактировать информацию о школе и её тренерах.',
        show: true,

        coachInfoItems: [
          {
            id: 1,
            Name: "Олег",
            Surname: "Вяткин",
            Patronymic: "Олегови",
            Email: "oleg@sevwrestling.ru",
            Phone: "+7(978)118-51-82",
            Viber: "+7(978)118-51-82",
            Skype: ""
          }
        ]
      }
    }
  }
</script>
