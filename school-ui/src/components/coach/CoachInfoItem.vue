<template>
    <div v-if="dataLoaded">
        <b-row>
            <b-col class="text-right">Тренер:</b-col>
            <b-col class="text-left">{{CoachInfoModel.surname}} {{CoachInfoModel.name}} {{CoachInfoModel.patronymic}}</b-col>
        </b-row>
        <b-row v-if="CoachInfoModel.email">
            <b-col class="text-right">E-Mail:</b-col>
            <b-col class="text-left">{{CoachInfoModel.email}}</b-col>
        </b-row>
        <b-row v-if="CoachInfoModel.phone">
            <b-col class="text-right">Телефон:</b-col>
            <b-col class="text-left">{{CoachInfoModel.phone}}</b-col>
        </b-row>
        <b-row v-if="CoachInfoModel.viber">
            <b-col class="text-right">Viber:</b-col>
            <b-col class="text-left">{{CoachInfoModel.viber}}</b-col>
        </b-row>
        <b-row v-if="CoachInfoModel.skype">
            <b-col class="text-right">Skype:</b-col>
            <b-col class="text-left">{{CoachInfoModel.skype}}</b-col>
        </b-row>
    </div>
    <div v-else>
        <coach-info-not-found-alert/>
    </div>
</template>

<script>
    import CoachInfoNotFoundAlert from "./CoachInfoNotFoundAlert.vue";
    export default {
        name: "coach-info-item",
        components: {CoachInfoNotFoundAlert},
        props: ['coachInfoItem'],
        data(){
            return {
                CoachInfoModel: {
                    /**
                     * Идентификатор
                     */
                    id: "",

                    /**
                     * Имя
                     */
                    name: "",

                    /**
                     * Фамилия
                     */
                    surname: "",

                    /**
                     * Отчество
                     */
                    patronymic: "",

                    /**
                     * E-Mail
                     */
                    email: "",

                    /**
                     * Телефон
                     */
                    phone: "",

                    /**
                     * Viber
                     */
                    viber: "",

                    /**
                     * Skype
                     */
                    skype: ""
                },
                currentCoachInfo: null,
                show: true,
                dataLoaded: false
            }
        },
        created: function () {
            console.log("Create function invoked!");
            let url = `coach/last/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        this.currentCoachInfo = data;
                        this.CoachInfoModel.id = this.currentCoachInfo.id;
                        this.CoachInfoModel.name = this.currentCoachInfo.name;
                        this.CoachInfoModel.surname = this.currentCoachInfo.surname;
                        this.CoachInfoModel.patronymic = this.currentCoachInfo.patronymic;
                        this.CoachInfoModel.email = this.currentCoachInfo.email;
                        this.CoachInfoModel.phone = this.currentCoachInfo.phone;
                        this.CoachInfoModel.viber = this.currentCoachInfo.viber;
                        this.CoachInfoModel.skype = this.currentCoachInfo.skype;
                        this.dataLoaded = true;

                    }, response => {
                        // error callback
                        this.CoachInfoModel.id = "";
                        this.CoachInfoModel.name = "";
                        this.CoachInfoModel.surname = "";
                        this.CoachInfoModel.patronymic = "";
                        this.CoachInfoModel.email = "";
                        this.CoachInfoModel.phone = "";
                        this.CoachInfoModel.viber = "";
                        this.CoachInfoModel.skype = "";
                    }
                )
            )
        },
    }
</script>

<style scoped>

</style>