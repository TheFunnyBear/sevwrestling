<template>

    <b-container fluid>
        <b-row align-v="center">
            <b-col align="left" cols="8" align-self="center">
                <b-form @submit="onSubmit" @reset="onReset" v-if="show">
                    <b-card header-tag="header" footer-tag="footer">
                        <template v-slot:header>
                            <h6 class="mb-0">Редактирование новости</h6>
                        </template>

                        <!--Дата новости-->
                        <b-form-group
                                id="input-group-1"
                                label="Дата новости:"
                                label-for="input-1"
                                description="Указнная тут дата новости будет отображаться в списке новостей и при детальном просмотре новости."
                        >
                            <b-form-input
                                    id="input-1"
                                    v-model="form.publicationDate"
                                    type="date"
                                    required
                            ></b-form-input>
                        </b-form-group>

                        <!--Заголовок новости-->
                        <b-form-group
                                id="input-group-2"
                                label="Зоголовок новости:"
                                label-for="input-2"
                                description="Указнный тут заголовок новости будет отображаться в списке новостей и при детальном просмотре новости."
                        >
                            <b-form-input
                                    id="input-2"
                                    v-model="form.title"
                                    type="text"
                                    required
                                    placeholder="Укажите тут заголовок новости"
                            ></b-form-input>
                        </b-form-group>

                        <!--Описание новости-->
                        <b-form-group id="input-group-3" label="Описание новости:" label-for="input-3">
                            <b-form-textarea
                                    id="input-3"
                                    v-model="form.description"
                                    rows="5"
                                    max-rows="6"
                                    required
                                    placeholder="Укажите тут описание новости"
                            ></b-form-textarea>
                        </b-form-group>

                        <!--Файл с фотографией-->
                        <b-form-group id="input-group-4" label="Фотография для новости:" label-for="input-4">
                            <b-form-file
                                    id="input-4"
                                    v-model="form.photoFile"
                                    :state="Boolean(form.photoFile)"
                                    accept="image/jpeg, image/png, image/gif"
                                    placeholder="Выберите файл или перетащите его сюда..."
                                    drop-placeholder="Перетащите сюда файл..."
                            ></b-form-file>
                            <div v-if="form.photoFile">
                                <div class="mt-3">Выбран файл с фотографией: {{ form.photoFile ? form.photoFile.name : '' }}</div>
                            </div>
                            <div v-else>
                                <div class="mt-3">Файл с фотографией не выбран.</div>
                            </div>
                        </b-form-group>

                        <template v-slot:footer>
                            <b-row align-v="center">
                                <b-col align="right">
                                    <b-button type="submit" variant="primary">Обновить новость</b-button>
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
        name: "message-item-edit",
        data() {
            return {
                form: {
                    publicationDate: "2020-01-12",
                    title: '',
                    description: '',
                    photoFile: null,
                },
                MessageModel: {
                    /**
                     * Идентификатор записи
                     */
                    id: 0,

                    /**
                     * Дата создания сообщения
                     */
                    createdDate: null,

                    /**
                     * Дата публикации сообщения
                     */
                    publicationDate: null,

                    /**
                     * Заголовок сообщения
                     */
                    messageTitle: '',

                    /**
                     * Текст сообщения
                     */
                    messageText: '',

                    /**
                     * Идентификатор изображения
                     */
                    pictureUuid: null,

                    /**
                     * Сообщение удалено
                     */
                    isDeleted: false
                },
                show: true,
            }
        },
        created: function () {
            console.log("Create function invoked!");
            let id = this.$route.params.id;

            let url = `message/${id}/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        this.MessageModel.id = data.id;
                        this.MessageModel.createdDate = data.createdDate;
                        this.MessageModel.publicationDate = data.publicationDate;
                        this.MessageModel.messageTitle = data.messageTitle;
                        this.MessageModel.messageText = data.messageText;
                        this.MessageModel.pictureUuid = data.pictureUuid;
                        this.MessageModel.isDeleted = data.isDeleted;

                        this.form.publicationDate =  moment.utc(this.MessageModel.publicationDate).format("YYYY-MM-DD");
                        this.form.title = this.MessageModel.messageTitle;
                        this.form.description = this.MessageModel.messageText;
                    }
                )
            );

            // Trick to reset/clear native browser form validation state
            this.show = false;
            this.$nextTick(() => {
                this.show = true;
            });
        },
        watch: {
          form: function () {

          }
        },
        methods: {
            onSubmit(evt) {
                console.log("OnSubmit function invoked!");
                evt.preventDefault();

                this.MessageModel.publicationDate = moment.utc(this.form.publicationDate);
                this.MessageModel.messageTitle = this.form.title;
                this.MessageModel.messageText = this.form.description;

                let id = this.$route.params.id;
                let url = `message/update/${id}/`;
                this.$http.post(url, JSON.stringify( this.MessageModel)).then(response => {
                    console.log("Post response completed with status:", response.status);
                    this.$router.push('/news_list');

                }, response => {
                    // error callback
                });
            },

            onReset(evt) {
                console.log("OnReset function invoked!");
                evt.preventDefault();

                // Reset our form values
                let id = this.$route.params.id;

                let url = `message/${id}/`;
                this.$http.get(url).then(result =>
                    result.json().then(data => {
                        this.MessageModel.id = data.id;
                        this.MessageModel.createdDate = data.createdDate;
                        this.MessageModel.publicationDate = data.publicationDate;
                        this.MessageModel.messageTitle = data.messageTitle;
                        this.MessageModel.messageText = data.messageText;
                        this.MessageModel.pictureUuid = data.pictureUuid;
                        this.MessageModel.isDeleted = data.isDeleted;
                    })
                );

                this.form.publicationDate =  moment.utc(this.MessageModel.publicationDate).format("YYYY-MM-DD");
                this.form.title = this.MessageModel.messageTitle;
                this.form.description = this.MessageModel.messageText;


                // Trick to reset/clear native browser form validation state
                this.show = false;
                this.$nextTick(() => {
                    this.show = true;
                });
            }
        }

    }
</script>

<style scoped>

</style>