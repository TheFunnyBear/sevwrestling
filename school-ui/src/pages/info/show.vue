<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>


    <b-card-group deck>
      <b-card bg-variant="Light" header="Тренерский состав:" class="text-center">
        <b-card-text>
          <CoachInfoList/>
        </b-card-text>
      </b-card>

    <b-card bg-variant="Light" header="Как добраться:" class="text-center">
      <b-card-text>
        Занятия вольной борьбой проводятся проходят по адресу:<br>
        г. Севастополь, ул. Частника, 87, в спортивном зале ДЮСШ№6
      </b-card-text>

      <RouteMap/>
    </b-card>
    </b-card-group>

    <PageFooter/>
  </div>
</template>

<script>
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';
  import RouteMap from "../../components/maps/RouteMap.vue";
  import CoachInfoList from "../../components/coach/CoachInfoList.vue";

  export default {
    components: {NavigationHeader, PageHeader, PageFooter, RouteMap, CoachInfoList},
    data() {
      return {
        isAdminMode: true,
        pageTitle: 'Просмотр информации',
        pageDescription: 'На этой странице отабражается информация о Школе борьбы и её тренерах.',
      }
    }
  }
</script>
