<template>
    <div>
        <b-row>
            <b-col class="text-right">Тренер:</b-col>
            <b-col class="text-left">{{coachInfoItem.Surname}} {{coachInfoItem.Name}} {{coachInfoItem.Patronymic}}</b-col>
        </b-row>
        <b-row v-if="coachInfoItem.Email">
            <b-col class="text-right">E-Mail:</b-col>
            <b-col class="text-left">{{coachInfoItem.Email}}</b-col>
        </b-row>
        <b-row v-if="coachInfoItem.Phone">
            <b-col class="text-right">Телефон:</b-col>
            <b-col class="text-left">{{coachInfoItem.Phone}}</b-col>
        </b-row>
        <b-row v-if="coachInfoItem.Viber">
            <b-col class="text-right">Viber:</b-col>
            <b-col class="text-left">{{coachInfoItem.Viber}}</b-col>
        </b-row>
        <b-row v-if="coachInfoItem.Skype">
            <b-col class="text-right">Skype:</b-col>
            <b-col class="text-left">{{coachInfoItem.Skype}}</b-col>
        </b-row>
    </div>
</template>

<script>
    export default {
        name: "coach-info-item",
        props: ['coachInfoItem'],
    }
</script>

<style scoped>

</style>