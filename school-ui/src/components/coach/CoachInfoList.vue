<template>
    <b-container>
        <CoachInfoItem v-for="item in coachInfoItems" v-bind:key="item.id" :coachInfoItem="item"/>
    </b-container>
</template>

<script>
    import CoachInfoItem from "./CoachInfoItem.vue";

    export default {
        name: "coach-info-list",
        components: {CoachInfoItem},
        data()
        {
            return {
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

<style scoped>

</style>