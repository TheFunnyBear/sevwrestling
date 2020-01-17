<template>
    <b-container fluid>
        <b-row align-v="center">
            <b-col align="left" cols="8" align-self="center" v-if="show">
                <b-form @submit="onSubmit" @reset="onReset">
                    <b-card header-tag="header" footer-tag="footer">
                        <template v-slot:header>
                            <h6 class="mb-0">Обновить информацию о тренере</h6>
                        </template>
                        <!--Фамилия -->
                        <b-form-group
                                id="input-group-1"
                                label="Фамилия:"
                                label-for="input-1"
                        >
                            <b-form-input
                                    id="input-1"
                                    v-model="CoachInfoModel.surname"
                                    type="text"
                                    required
                                    placeholder="Укажите тут фамилию тренера"
                            ></b-form-input>
                        </b-form-group>

                        <!--Имя -->
                        <b-form-group
                                id="input-group-2"
                                label="Имя:"
                                label-for="input-2"
                        >
                            <b-form-input
                                    id="input-2"
                                    v-model="CoachInfoModel.name"
                                    type="text"
                                    required
                                    placeholder="Укажите тут имя тренера"
                            ></b-form-input>
                        </b-form-group>

                        <!--Отчество -->
                        <b-form-group
                                id="input-group-3"
                                label="Отчество:"
                                label-for="input-3"
                        >
                            <b-form-input
                                    id="input-3"
                                    v-model="CoachInfoModel.patronymic"
                                    type="text"
                                    required
                                    placeholder="Укажите тут отчество тренера"
                            ></b-form-input>
                        </b-form-group>

                        <!--E-Mail -->
                        <b-form-group
                                id="input-group-4"
                                label="E-Mail:"
                                label-for="input-4"
                        >
                            <b-form-input
                                    id="input-4"
                                    v-model="CoachInfoModel.email"
                                    type="text"
                                    placeholder="Укажите тут E-Mail тренера, если он есть"
                            ></b-form-input>
                        </b-form-group>

                        <!--Телефон -->
                        <b-form-group
                                id="input-group-5"
                                label="Телефон:"
                                label-for="input-5"
                        >
                            <b-form-input
                                    id="input-5"
                                    v-model="CoachInfoModel.phone"
                                    type="text"
                                    placeholder="Укажите тут телефон тренера, если он есть"
                            ></b-form-input>
                        </b-form-group>

                        <!--Viber -->
                        <b-form-group
                                id="input-group-6"
                                label="Viber:"
                                label-for="input-6"
                        >
                            <b-form-input
                                    id="input-6"
                                    v-model="CoachInfoModel.viber"
                                    type="text"
                                    placeholder="Укажите тут номер Viber тренера, если он есть"
                            ></b-form-input>
                        </b-form-group>

                        <!--Skype -->
                        <b-form-group
                                id="input-group-7"
                                label="Skype:"
                                label-for="input-7"
                        >
                            <b-form-input
                                    id="input-7"
                                    v-model="CoachInfoModel.skype"
                                    type="text"
                                    placeholder="Укажите тут номер Skype тренера, если он есть"
                            ></b-form-input>
                        </b-form-group>
                        <template v-slot:footer>
                            <b-row align-v="center">
                                <b-col align="right">
                                    <b-button type="submit" variant="primary">Обновить информацию о тренере</b-button>
                                    <b-button type="reset" variant="danger">Очистить</b-button>
                                </b-col>
                            </b-row>
                        </template>
                    </b-card>
                </b-form>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    import moment from "moment";

    export default {
        name: "coach-info-item-edit",
        props: ['formData'],
        data() {
            return {
                CoachInfoModel: {
                    /**
                     * Идентификатор
                     */
                    id: 0,

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
                show: true
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

                    }, response => {
                        // error callback
                        this.CoachInfoModel.id = 0;
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

        methods: {
            onSubmit(evt) {
                console.log("OnSubmit function invoked!");
                evt.preventDefault();

                let url = `coach/create/`;
                this.$http.post(url, JSON.stringify( this.CoachInfoModel)).then(response => {
                    console.log("Post response completed with status:", response.status);
                    this.$router.push('/info_show');

                }, response => {
                    // error callback
                });
            },
            onReset(evt) {
                evt.preventDefault();
                // Reset our form values
                this.CoachInfoModel.id = this.currentCoachInfo.id;
                this.CoachInfoModel.name = this.currentCoachInfo.name;
                this.CoachInfoModel.surname = this.currentCoachInfo.surname;
                this.CoachInfoModel.patronymic = this.currentCoachInfo.patronymic;
                this.CoachInfoModel.email = this.currentCoachInfo.email;
                this.CoachInfoModel.phone = this.currentCoachInfo.phone;
                this.CoachInfoModel.viber = this.currentCoachInfo.viber;
                this.CoachInfoModel.skype = this.currentCoachInfo.skype;

                // Trick to reset/clear native browser form validation state
                this.show = false;
                this.$nextTick(() => {
                    this.show = true
                })
            }
        }

    }
</script>

<style scoped>

</style>