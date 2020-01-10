<template>
    <b-container fluid>
        <b-row align-v="center">
            <b-col align="left" cols="8" align-self="center">
                <b-card header-tag="header" footer-tag="footer">
                    <template v-slot:header>
                        <h6 class="mb-0">Обновить информацию о тренере</h6>
                    </template>
                    <b-form @submit="onSubmit" @reset="onReset">
                        <!--Фамилия -->
                        <b-form-group
                                id="input-group-1"
                                label="Фамилия:"
                                label-for="input-1"
                        >
                            <b-form-input
                                    id="input-1"
                                    v-model="form.Surname"
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
                                    v-model="form.Name"
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
                                    v-model="form.Patronymic"
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
                                    v-model="form.Email"
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
                                    v-model="form.Phone"
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
                                    v-model="form.Viber"
                                    type="text"
                                    placeholder="Укажите тут номер Viber тренера, если он есть"
                            ></b-form-input>
                        </b-form-group>

                        <!--Skype -->
                        <b-form-group
                                id="input-group-7"
                                label="Viber:"
                                label-for="input-7"
                        >
                            <b-form-input
                                    id="input-7"
                                    v-model="form.Skype"
                                    type="text"
                                    placeholder="Укажите тут номер Skype тренера, если он есть"
                            ></b-form-input>
                        </b-form-group>
                    </b-form>
                    <template v-slot:footer>
                        <b-row align-v="center">
                            <b-col align="right">
                                <b-button type="submit" variant="primary">Обновить информацию о тренере</b-button>
                                <b-button type="reset" variant="danger">Очистить</b-button>
                            </b-col>
                        </b-row>
                    </template>
                </b-card>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    export default {
        name: "coach-info-item-edit",
        props: ['formData'],
        data() {
            return {
                form: {
                    id: 1,
                    Name: "Олег",
                    Surname: "Вяткин",
                    Patronymic: "Олегови",
                    Email: "oleg@sevwrestling.ru",
                    Phone: "+7(978)118-51-82",
                    Viber: "+7(978)118-51-82",
                    Skype: ""
                }
            }
        },
        created: function () {
            console.log("Create function invoked!");
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault();
                alert(JSON.stringify(this.form))
            },
            onReset(evt) {
                evt.preventDefault();
                // Reset our form values
                this.form.Name = '';
                this.form.Surname = '';
                this.form.Patronymic = '';
                this.form.Email = '';
                this.form.Phone = '';
                this.form.Viber = '';
                this.form.Skype = '';

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